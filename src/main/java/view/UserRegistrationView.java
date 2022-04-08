package view;

import controller.User;
import controller.UserRegistration;
import model.questions.Questions;
import view.printer.Printer;

import java.util.*;

public class UserRegistrationView {
    private Scanner scanner;
    private static Printer print;


    public UserRegistrationView(Printer print, Scanner scanner) {
        this.print = print;
        this.scanner = scanner;
    }
    public void newUserRegistration(){
        this.print.printString("Enter username:");
        final String userName = scanner.nextLine();

        this.print.printString("Enter password");
        final String password = scanner.nextLine();

        final Questions question = Questions.getInstance();
        final List<String> securityQuestionsMap = question.getSecurityQuestions();
        final ArrayList<String> securityQuestionAnswers = new ArrayList<>();
        for (String entry : securityQuestionsMap) {
            this.print.printString(entry);
            securityQuestionAnswers.add(scanner.nextLine());
        }
        User user = new User(userName,
                password,
                securityQuestionAnswers.get(0),
                securityQuestionAnswers.get(1),
                securityQuestionAnswers.get(2),
                securityQuestionAnswers.get(3));
        try{
            UserRegistration userRegistration = new UserRegistration();
            boolean isUserCreated = userRegistration.register(user);
            if(isUserCreated)
                print.printString("User registered successfully");
            else
                print.printString("Registration unsuccessful");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
