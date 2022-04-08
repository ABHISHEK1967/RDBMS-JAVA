package controller;

public class GenericException extends Exception{
    String message;
    public GenericException(String message) {
        super(message);
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
