package view;

import view.printer.Printer;

import java.util.Scanner;

public class UserLoginView {
    private Scanner scanner;
    private static Printer print;

    public UserLoginView(Printer print, Scanner scanner){
        this.print = print;
        this.scanner = scanner;
    }
}
