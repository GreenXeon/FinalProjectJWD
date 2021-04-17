package by.epam.jwd.finalproj.command.action.user;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowErrorPageCommand;
import by.epam.jwd.finalproj.command.page.ShowMainPageCommand;
import by.epam.jwd.finalproj.command.page.ShowSuccessPageCommand;
import by.epam.jwd.finalproj.command.page.user.ShowSubscribeCommand;
import by.epam.jwd.finalproj.model.user.UserDto;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalDto;
import by.epam.jwd.finalproj.model.subscription.SubscriptionDto;
import by.epam.jwd.finalproj.service.impl.PeriodicalService;
import by.epam.jwd.finalproj.service.impl.SubscriptionService;
import by.epam.jwd.finalproj.service.impl.UserService;
import by.epam.jwd.finalproj.strategy.CommonStrategy;
import by.epam.jwd.finalproj.strategy.StrategyManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import static by.epam.jwd.finalproj.util.ParameterNames.*;

public enum SubscribeCommand implements Command {
    INSTANCE;

    private CommonStrategy paymentStrategy;
    private SubscriptionService subscriptionService;
    private PeriodicalService periodicalService;
    private UserService userService;

    private final String PROPERTY_FILE = "email.properties";

    private Properties emailProperties = new Properties();
    private final String LOGIN_PROPERTY_NAME = "login";
    private final String PASSWORD_PROPERTY_NAME = "password";
    private final String PORT_PROPERTY_NAME = "port";
    private final String FROM_PROPERTY_NAME = "from";
    private final String SMTP_PROPERTY_NAME = "smtp";
    private String login;
    private String password;
    private String port;
    private String from;
    private String smtp;

    public void setPaymentStrategy(CommonStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    SubscribeCommand(){
        this.subscriptionService = SubscriptionService.INSTANCE;
        this.userService = UserService.INSTANCE;
        this.periodicalService = PeriodicalService.INSTANCE;
    }

    private final Logger logger = LogManager.getLogger(SubscribeCommand.class);

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        if(request.getParameter(PAYMENT_TYPE) == null){
            return ShowSubscribeCommand.INSTANCE.execute(request, response);
        }
        final String strategyName = request.getParameter(PAYMENT_TYPE);

        final CommonStrategy chosenStrategy = StrategyManager.findStrategyByName(strategyName);
        this.setPaymentStrategy(chosenStrategy);

        final String paymentId = createPaymentId();
        final int userId = (int) request.getSessionAttribute(USER_ID);
        final Timestamp paymentTime = new Timestamp(System.currentTimeMillis());
        final UserDto currentUser = userService.findById(userId).get();
        final BigDecimal paymentCost = (BigDecimal) request.getSessionAttribute(TOTAL_COST);
        if(strategyName.equalsIgnoreCase("online") && paymentCost.compareTo(currentUser.getCash()) == 1){
            logger.warn("Attempt to subscribe online without money");
            return ShowSubscribeCommand.INSTANCE.execute(request, response);
        }
        List<PeriodicalDto> periodicalsToSubscribe = (List<PeriodicalDto>) request.getSessionAttribute("subscribePeriodicals");
        for (PeriodicalDto periodical : periodicalsToSubscribe){
            if (!periodicalService.findById(periodical.getId()).isPresent()){
                request.setAttribute(ERROR, "Periodical is not available");
                return ShowMainPageCommand.INSTANCE.execute(request, response);
            }
        }
        final boolean payment = paymentStrategy.submitPayment(paymentId, userId, paymentTime, paymentCost);
        String email = currentUser.getEmail();
        if (!payment){
            return ShowErrorPageCommand.INSTANCE.execute(request, response);
        }
        SubscriptionDto subscriptionDto;
        StringBuilder messageText = new StringBuilder("You are subscribed on");
        for (PeriodicalDto periodical : periodicalsToSubscribe){
            subscriptionDto = new SubscriptionDto.Builder()
                    .withUserId(userId)
                    .withPaymentId(paymentId)
                    .withSubscriptionDate(paymentTime)
                    .withSubscriptionCost(periodical.getSubCost())
                    .withPeriodicalId(periodical.getId())
                    .build();
            subscriptionService.save(subscriptionDto);
            if(periodicalsToSubscribe.indexOf(periodical) != 0){
                messageText.append(",");
            }
            messageText.append(" ").append(periodical.getName());
        }
        messageText.append("\nPayment id: ").append(paymentId);
        messageText.append("\nPayment cost: ").append(paymentCost);
        messageText.append("\nPayment type: ").append(StrategyManager.findNameByStrategy(chosenStrategy));
        boolean billResult = sendCheckToEmail(email, String.valueOf(messageText));
        if (!billResult){
            logger.error("Bill is not send to user's email");
            return ShowErrorPageCommand.INSTANCE.execute(request, response);
        }
        paymentStrategy.lowerUserBalance(userId, paymentCost);
        request.setAttribute("successMessage", "Your order is successfully processed!");
        return ShowSuccessPageCommand.INSTANCE.execute(request, response);
    }

    public String createPaymentId(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    private boolean sendCheckToEmail(String email, String messageText){
        initProperties();
        String from = INSTANCE.from;

        Properties props = new Properties();
        props.put("mail.smtp.host", smtp);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        props.put("mail.smtp.user", login);
        props.put("mail.smtp.password", password);

        Session session = Session.getDefaultInstance(props);

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(email));
            message.setSubject("Payment");
            message.setText(messageText);

            Transport transport = session.getTransport();
            transport.connect(smtp, Integer.parseInt(port), login, password);
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));

            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
    }

    private void initProperties(){
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE)) {
            emailProperties.load(inputStream);
            if (emailProperties.isEmpty()) {
                throw new Exception("Email properties has not been loaded");
            }
            login = emailProperties.getProperty(LOGIN_PROPERTY_NAME);
            password = emailProperties.getProperty(PASSWORD_PROPERTY_NAME);
            port = emailProperties.getProperty(PORT_PROPERTY_NAME);
            smtp = emailProperties.getProperty(SMTP_PROPERTY_NAME);
            from = emailProperties.getProperty(FROM_PROPERTY_NAME);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
