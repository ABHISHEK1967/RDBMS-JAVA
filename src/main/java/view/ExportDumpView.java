package view;

import controller.ExportDump;
import view.printer.Printer;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ExportDumpView {
    private Printer printer;
    private Scanner scanner;

    public ExportDumpView(Scanner scanner, Printer print){
        this.printer = print;
        this.scanner = scanner;
    }

    public void displaySchemas() throws IOException {
        printer.printTitle("List of schemas");
        File databaseFolder = new File("./src/main/java/Model/database");
        File[] listOfSchemas = databaseFolder.listFiles();
        boolean invalidSchemaSelection = true;

        for (int i =0; i < listOfSchemas.length; i++){
            if(listOfSchemas[i].isDirectory()){
                printer.printString((i+1) + ". " + listOfSchemas[i].getName());
            }
        }
        while (invalidSchemaSelection){
            printer.printString("Which one do you want to export ? ");
            int selectedSchemaIndex = scanner.nextInt();
            if(selectedSchemaIndex > listOfSchemas.length || selectedSchemaIndex <= 0){
                printer.printString("Please enter a valid number.");
            } else{
                boolean invalidFilePath = true;
                invalidSchemaSelection = false;
                printer.printString("The default location for downloading the dump is './'.");
                while (invalidFilePath){
                    printer.printString("Would you like to change the destination folder?");
                    printer.printString("1. Yes");
                    printer.printString("2. No");
                    String destinationDumpFolder = "./";
                    ExportDump dump;

                    String selectedOption = scanner.next();
                    switch (selectedOption){
                        case "1":
                            printer.printString("Enter the path: (Eg - " + listOfSchemas[0].getAbsolutePath());
                            destinationDumpFolder = scanner.next();

                            dump = new ExportDump(listOfSchemas[selectedSchemaIndex-1],destinationDumpFolder, printer);
                            invalidFilePath = !(dump.downloadSchema());
                            break;

                        case "2":
                            dump = new ExportDump(listOfSchemas[selectedSchemaIndex-1],destinationDumpFolder, printer);
                            dump.downloadSchema();
                            invalidFilePath = false;
                            break;

                        default:
                            break;
                    }
                }
            }
        }

        printer.printString("The dump has been exported to the specified location.");
        printer.printString("");
    }
}
