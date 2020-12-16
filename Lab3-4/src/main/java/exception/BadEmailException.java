package exception;

public class BadEmailException extends RuntimeException {
    public BadEmailException() {
        super("Bad email!");
    }
    public BadEmailException(String message) {
        super(message);
    }
}
