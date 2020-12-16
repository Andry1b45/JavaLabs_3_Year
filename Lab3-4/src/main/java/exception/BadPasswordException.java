package exception;

public class BadPasswordException extends RuntimeException {
    public BadPasswordException() {
        super("Bad password!");
    }
    public BadPasswordException(String message) {
        super(message);
    }
}
