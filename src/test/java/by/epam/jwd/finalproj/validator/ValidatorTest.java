package by.epam.jwd.finalproj.validator;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    public void isValidLogin() {
        String[] logins = new String[]{
                "",
                " ",
                "test",
                "s123",
                "sample123+",
                "epam_guest",
                "qwertyuiop[]asdfghjkl;'zxcvbnm,./1234567890"
        };
        int wrongLogins = 0;
        for(String login : logins){
            if(!Validator.isValidLogin(login)){
                wrongLogins++;
            }
        }
        assertEquals(wrongLogins, logins.length);
    }

    @Test
    public void isValidPassword() {
        String[] passwords = new String[]{
                "",
                " ",
                "test1",
                "sample 123+",
                "epam_guest()",
                "qwertyuiop[]asdfghjkl;'zxcvbnm,./123456dfgdfsg7890"
        };
        int wrongPasswords = 0;
        for(String password : passwords){
            if(!Validator.isValidPassword(password)){
                wrongPasswords++;
            }
        }
        assertEquals(wrongPasswords, passwords.length);
    }

    @Test
    public void isValidEmail() {
        String[] emails = new String[]{
                "",
                " ",
                "@",
                "samplemail#mail.ru",
                "qwertyuiop123456asddfgsdgfsdgsgsdfgsdfghjkcvncvncxvnv55474lzxcvbnm@mail.ru"
        };
        int wrongEmails = 0;
        for(String email : emails){
            if(!Validator.isValidEmail(email)){
                wrongEmails++;
            }
        }
        assertEquals(wrongEmails, emails.length);
        assertTrue(Validator.isValidEmail("zahar.shishkin.2001@mail.ru"));
    }

    @Test
    public void isValidUserName() {
        String[] usernames = new String[]{
                "@",
                "DJ_Arbuz",
                "ValeriyAlbertovichZhmyshenko"
        };
        int wrongUsernames = 0;
        for(String username : usernames){
            if(!Validator.isValidUserName(username)){
                wrongUsernames++;
            }
        }
        assertEquals(wrongUsernames, usernames.length);
    }

    @Test
    public void isValidUserSurname() {
        String[] userSurnames = new String[]{
                "@",
                "DJ_Arbuz",
                "ValeriyAlbertovichZhmyshenko"
        };
        int wrongUserSurnames = 0;
        for(String userSurname : userSurnames){
            if(!Validator.isValidUserSurname(userSurname)){
                wrongUserSurnames++;
            }
        }
        assertEquals(wrongUserSurnames, userSurnames.length);
    }

    @Test
    public void isValidPeriodicalName() {
        String[] periodicalNames = new String[]{
                "",
                " ",
                "@",
                "NewYorkTimes@4",
                "qwertyuiopasdfghjklzxcvbnmqwertyuiasdfghjzxcvbn34567"
        };
        int wrongPeriodicalNames = 0;
        for(String periodicalName : periodicalNames){
            if(!Validator.isValidPeriodicalName(periodicalName)){
                wrongPeriodicalNames++;
            }
        }
        assertEquals(wrongPeriodicalNames, periodicalNames.length);
    }

    @Test
    public void isValidPeriodicalAuthor() {
        String[] authors = new String[]{
                "",
                " ",
                "@",
                "Zakhar_Shishkin",
                "ValeriyAlbertovichZhmyshenkoqwertyuioasdfghjklzxcvbnmqwertyuiasdfg"
        };
        int wrongAuthors = 0;
        for(String author : authors){
            if(!Validator.isValidPeriodicalAuthor(author)){
                wrongAuthors++;
            }
        }
        assertEquals(wrongAuthors, authors.length);
    }

    @Test
    public void isValidPeriodicalCost() {
        String[] costs = new String[]{
                "",
                " ",
                "1,2",
                "9999999.56",
                "34,5678",
                "5.",
                "sampletext"
        };
        int wrongCosts = 0;
        for(String cost : costs){
            if(!Validator.isValidPeriodicalCost(cost)){
                wrongCosts++;
            }
        }
        assertEquals(wrongCosts, costs.length);
        assertTrue(Validator.isValidPeriodicalCost("35.67"));
    }

    @Test
    public void isValidPeriodicalPublisher() {
        String[] publishers = new String[]{
                "",
                "@",
                " ",
                "Minsk_Official_Publishment",
                "ValeriyAlbertovichZhmyshenko12345678qwertyui234567werty456"
        };
        int wrongPublishers = 0;
        for(String publisher : publishers){
            if(!Validator.isValidPeriodicalPublisher(publisher)){
                wrongPublishers++;
            }
        }
        assertEquals(wrongPublishers, publishers.length);
    }
}