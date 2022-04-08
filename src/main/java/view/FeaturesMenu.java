package view;

import controller.ErdGenerator;
import controller.MetaGenerator;
import view.printer.Printer;
import java.util.Scanner;

public class FeaturesMenu {
    private Printer printer;
    private Scanner scanner;

    public FeaturesMenu(Scanner scanner, Printer print){
        this.printer = print;
        this.scanner = scanner;
    }

    public void displayMenu() {
        while (true) {
            printer.printTitle("Main Menu");
            printer.printString("1. Process SQL Queries");
            printer.printString("2. Generate Dump");
            printer.printString("3. Generate ERD");
            printer.printString("4. Analytics");
            printer.printString("5. Generate Meta");
            printer.printString("6. Logout");
            printer.printString("Select an option:");
            final String input = scanner.nextLine();

            switch (input) {
                case "1":
                    try{
                        ProcessQueriesView processQueriesView = new ProcessQueriesView(scanner,printer);
                        processQueriesView.displayMenu();
                    } catch (Exception e ){
                        printer.printString(e.getMessage());
                    }

                    break;
                case "2":
                    try{
                    ExportDumpView exportDumpView = new ExportDumpView(scanner, printer);
                    exportDumpView.displaySchemas();
                     } catch (Exception e ){
                        printer.printString(e.getMessage());
                    }
                    break;
                case "3":
                    try {
                        ErdGenerator erdGenerator = new ErdGenerator();
                        printer.printString("Enter the database name:");
                        String databaseName = this.scanner.nextLine();
                        erdGenerator.generateERD(databaseName);
                    }catch (Exception e ){
                        printer.printString(e.getMessage());
                    }
                    break;
                case "4":
                    try {
                        AnalyticsView analyticsView = new AnalyticsView(scanner, printer);
                        analyticsView.displayMenu();
                    } catch (Exception e){
                        printer.printString(e.getMessage());
                    }
                    break;
                case "5":
                    MetaGenerator metaGenerator = new MetaGenerator();
                    printer.printString("Enter the database name:");
                    String dbName = this.scanner.nextLine();
                    printer.printString("Enter the table name:");
                    String tableName = this.scanner.nextLine();
                    metaGenerator.generateMetaDataFile(dbName,tableName);
                    break;
                case "6":
                    return;
                default:
                    break;
            }
        }
    }
}
