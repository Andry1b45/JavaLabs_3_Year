package exception;

public class BadNameException extends RuntimeException {
    public BadNameException() {
        super("Bad full name!");
    }
    public BadNameException(String message) {
        super(message);
    }
}
