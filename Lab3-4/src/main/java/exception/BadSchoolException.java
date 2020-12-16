package exception;

public class BadSchoolException extends RuntimeException {
    public BadSchoolException() {
        super("Bad school exception!");
    }
    public BadSchoolException(String message) {
        super(message);
    }
}
