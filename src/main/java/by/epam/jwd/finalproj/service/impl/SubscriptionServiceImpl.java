package by.epam.jwd.finalproj.service.impl;

import by.epam.jwd.finalproj.dao.impl.SubscriptionDaoImpl;
import by.epam.jwd.finalproj.model.subscription.Subscription;
import by.epam.jwd.finalproj.model.subscription.SubscriptionDto;
import by.epam.jwd.finalproj.service.SubscriptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum SubscriptionServiceImpl implements SubscriptionService {
    INSTANCE;

    private final SubscriptionDaoImpl subscriptionDao;

    SubscriptionServiceImpl(){
        this.subscriptionDao = new SubscriptionDaoImpl();
    }

    private final Logger logger = LogManager.getLogger(SubscriptionServiceImpl.class);

    @Override
    public Optional<List<SubscriptionDto>> findByUserId(int userId){
        Optional<List<Subscription>> subscriptionsByUserId = subscriptionDao.findByUserId(userId);
        return subscriptionsByUserId.map(subscriptions -> subscriptions
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));
    }

    @Override
    public List<SubscriptionDto> findByPhrase(int userId, String phrase){
        List<SubscriptionDto> subscriptions = this.findByUserId(userId).orElse(Collections.emptyList());
        if(subscriptions.isEmpty()){
            logger.error("Periodicals are not read");
        }
        List<SubscriptionDto> foundSubscriptions = new ArrayList<>();
        for (SubscriptionDto subscription : subscriptions){
           if(subscription.getPeriodicalName().toLowerCase().contains(phrase.toLowerCase())){
                foundSubscriptions.add(subscription);
            }
        }
        return foundSubscriptions;
    }
    
    @Override
    public Optional<SubscriptionDto> save(SubscriptionDto dto) {
        return Optional.of(convertToDto(subscriptionDao.save(convertToEntity(dto)).get()));
    }

    @Override
    public Subscription convertToEntity(SubscriptionDto dto){
        return new Subscription.Builder()
            .withUserId(dto.getUserId())
            .withSubscriptionCost(dto.getSubscriptionCost())
            .withSubscriptionDate(dto.getSubscriptionDate())
            .withPeriodicalName(dto.getPeriodicalName())
            .withPaymentId(dto.getPaymentId())
            .withPeriodicalId(dto.getPeriodicalId())
            .build();
    }

    @Override
    public SubscriptionDto convertToDto(Subscription entity){
        return new SubscriptionDto.Builder()
                .withId(entity.getId())
                .withUserId(entity.getUserId())
                .withSubscriptionCost(entity.getSubscriptionCost())
                .withSubscriptionDate(entity.getSubscriptionDate())
                .withPeriodicalName(entity.getPeriodicalName())
                .withPaymentId(entity.getPaymentId())
                .withPeriodicalId(entity.getPeriodicalId())
                .build();
    }
}
