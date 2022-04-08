package view;

import controller.AnalyticsController;
import view.printer.Printer;

import java.util.Scanner;

public class AnalyticsView {
    private Printer printer;
    private Scanner scanner;

    private AnalyticsController anayticsController = new AnalyticsController();

    AnalyticsView(Scanner scanner, Printer printer) {
        this.printer = printer;
        this.scanner = scanner;
    }

    public void displayMenu() throws Exception {
        while (true) {
            printer.printTitle("Database Analysis");
            printer.printString("1. Execute Analysis");
            printer.printString("2. Return");
            final String input = scanner.nextLine();
            switch (input) {
                case "1":
                    printer.printString("Enter your Analysis Query:");
                    String query = scanner.nextLine();
                    String message = anayticsController.analytics(query);
                    if (message.equalsIgnoreCase("Invalid Analytics query")){
                        printer.printString(message);
                        return;
                    }
                    break;
                case "2":
                    FeaturesMenu featuresMenu = new FeaturesMenu(scanner, printer);
                    return;
            }
        }

    }
}
