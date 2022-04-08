package view.printer;

public class Printer {
    private static Printer print;

    public final void printString(String string) {
        System.out.println(string);
    }

    public final void printTitle(String title) {
        System.out.println("***************************************");
        System.out.println("\t\t"+title);
        System.out.println("***************************************");
    }


}
