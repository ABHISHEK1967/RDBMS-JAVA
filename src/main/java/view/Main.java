package view;

import controller.User;
import controller.UserLogin;
import controller.UserSession;
import view.printer.Printer;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Printer printer = new Printer();
        Scanner scanner = new Scanner(System.in);
        UserSession userSession = UserSession.getInstance();
        while (true) {
            printer.printTitle("Welcome to RDBMS Project");
            printer.printString("1. User registration.");
            printer.printString("2. User login.");
            printer.printString("3. Exit.");
            printer.printString("Select an option:");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    UserRegistrationView userRegistrationView = new UserRegistrationView(printer, scanner);
                    userRegistrationView.newUserRegistration();
                    break;
                case "2":
                    UserLogin userLogin = new UserLogin(printer, scanner);
                    final User user = userLogin.performLogin();
                    if(user!=null){
                        userSession.newUserSession(user);
                        FeaturesMenu featureMenu = new FeaturesMenu(scanner, printer);
                        featureMenu.displayMenu();
                    }else{
                        printer.printString("Invalid credentials");
                    }
                    break;
                case "3":
                    userSession.destroyUserSession();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}
