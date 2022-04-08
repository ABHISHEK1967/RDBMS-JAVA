package view;

import controller.ProcessQuery;
import view.printer.Printer;
import java.io.IOException;
import java.util.Scanner;

public class ProcessQueriesView {
    private Printer printer;
    private Scanner scanner;

    private ProcessQuery processQuery = new ProcessQuery();

    ProcessQueriesView(Scanner scanner, Printer print) throws IOException {
        this.printer = print;
        this.scanner = scanner;
    }

    public void displayMenu() throws Exception {
        while (true) {
            printer.printTitle("Process SQL Queries");
            printer.printString("1. Execute Query");
            printer.printString("2. Return");
            final String input = scanner.nextLine();
            switch (input) {
                case "1":
                    printer.printString("Enter your SQL Query:");
                    String query = scanner.nextLine();
                    printer.printString(processQuery.processorQuery(query));
                    break;
                case "2":
                    processQuery.closeFileWriter();
                    return;
            }
        }

    }



}
