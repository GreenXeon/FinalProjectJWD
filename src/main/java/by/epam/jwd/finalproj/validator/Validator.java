package by.epam.jwd.finalproj.validator;

public class Validator {
    private static final String LOGIN_PATTERN = "^[a-zA-Z0-9]{6,50}$";
    private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9#№.,-=:;!?&]{6,50}$";
    private static final String EMAIL_PATTERN = "^((.+)@(.+)){6,45}$";
    private static final String NAME_PATTERN = "^[a-zA-Z-]{1,20}$";
    private static final String SURNAME_PATTERN = "^[a-zA-Z-]{1,20}$";
    private static final String PERIODICAL_NAME_PATTERN = "^[a-zA-Z0-9#№.,-=:;!?& ]{1,50}$";
    private static final String PERIODICAL_AUTHOR_PATTERN = "^[a-zA-Z ]{1,50}$";
    private static final String PERIODICAL_COST_PATTERN = "\\d{1,4}(\\.\\d{1,2})?";
    private static final String PERIODICAL_PUBLISHER_PATTERN = "^[a-zA-Z0-9 ]{1,50}$";

    public static boolean isValidLogin(String login){
        return !login.isEmpty() && login.matches(LOGIN_PATTERN);
    }

    public static boolean isValidPassword(String password){
        return !password.trim().isEmpty() && password.matches(PASSWORD_PATTERN);
    }

    public static boolean isValidEmail(String email){
        return !email.trim().isEmpty() && email.matches(EMAIL_PATTERN);
    }

    public static boolean isValidUserName(String username){
        return !username.trim().isEmpty() && username.matches(NAME_PATTERN);
    }

    public static boolean isValidUserSurname(String surname){
        return !surname.trim().isEmpty() && surname.matches(SURNAME_PATTERN);
    }

    public static boolean isValidPeriodicalName(String periodicalName){
        return !periodicalName.trim().isEmpty() && periodicalName.matches(PERIODICAL_NAME_PATTERN);
    }

    public static boolean isValidPeriodicalAuthor(String periodicalAuthor){
        return !periodicalAuthor.trim().isEmpty() && periodicalAuthor.matches(PERIODICAL_AUTHOR_PATTERN);
    }

    public static boolean isValidPeriodicalCost(String periodicalCost){
        return !periodicalCost.trim().isEmpty() && periodicalCost.matches(PERIODICAL_COST_PATTERN);
    }

    public static boolean isValidPeriodicalPublisher(String periodicalPublisher){
        return !periodicalPublisher.trim().isEmpty() && periodicalPublisher.matches(PERIODICAL_PUBLISHER_PATTERN);
    }
}
