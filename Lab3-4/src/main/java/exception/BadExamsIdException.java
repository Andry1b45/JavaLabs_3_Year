package exception;

public class BadExamsIdException extends RuntimeException {
    public BadExamsIdException() {
        super("Bad exams ID!");
    }
    public BadExamsIdException(String message) {
        super(message);
    }
}
