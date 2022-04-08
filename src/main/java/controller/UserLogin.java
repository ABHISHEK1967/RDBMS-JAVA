package controller;


import model.questions.Questions;
import view.printer.Printer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class UserLogin {
    private Scanner scanner;
    private static Printer print;
    final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
    public UserLogin(Printer print, Scanner scanner) throws NoSuchAlgorithmException {
        this.print = print;
        this.scanner = scanner;
    }

    public User performLogin() {
        System.out.println("Enter username");
        final String user = scanner.nextLine();

        System.out.println("Enter password");
        final String password = scanner.nextLine();

        final Questions securityQuestion = Questions.getInstance();
        final String randomSecurityQuestion = securityQuestion.getRandomSecurityQuestion();
        final int randonSecurityQuestionIndex = securityQuestion
                .getRandomSecurityQuestionIndex(randomSecurityQuestion);

        this.print.printString(randomSecurityQuestion);
        final String securityAnswer = scanner.nextLine();
        String userDetails;
        boolean userExists = false;
        String userName=null;
        String Password= null;
        String securityQ1Ans= null;
        String securityQ2Ans= null;
        String securityQ3Ans= null;
        String securityQ4Ans = null;
        boolean isSameUserName = false;
        boolean isSamePassword = false;
        try (final BufferedReader usersFileReader = new BufferedReader(
                new FileReader("./src/main/java/Model/users/users.txt"))) {


            while ((userDetails = usersFileReader.readLine()) != null) {
                final String[] userDetailsArr = userDetails.split(" ");
                isSameUserName = userDetailsArr[1].equalsIgnoreCase(user);
                isSamePassword =
                            String.format("%064x", new BigInteger(1,
                                    messageDigest.digest(password
                                            .getBytes(StandardCharsets.UTF_8)))).equals(userDetailsArr[2]);

                userExists = userDetailsArr[3+randonSecurityQuestionIndex].equals(securityAnswer);

                if (isSameUserName && userExists && isSamePassword) {
                    userName = userDetailsArr[1];
                    Password = userDetailsArr[2];
                    securityQ1Ans = userDetailsArr[3];
                    securityQ2Ans = userDetailsArr[4];
                    securityQ3Ans = userDetailsArr[5];
                    securityQ4Ans = userDetailsArr[6];
                    break;
                }
            }
        }catch(Exception e){
            this.print.printString(e.getMessage());
        }
        if (isSameUserName && userExists && isSamePassword) {
            return new User(userName,
                    Password,
                    securityQ1Ans,
                    securityQ2Ans,
                    securityQ3Ans,
                    securityQ4Ans);
        }else{
            return null;
        }

    }
}
