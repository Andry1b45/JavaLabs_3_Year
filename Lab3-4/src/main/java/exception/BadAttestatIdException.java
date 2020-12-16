package exception;

public class BadAttestatIdException extends RuntimeException {
    public BadAttestatIdException() {
        super("Bad attestat ID!");
    }
    public BadAttestatIdException(String message) {
        super(message);
    }
}
