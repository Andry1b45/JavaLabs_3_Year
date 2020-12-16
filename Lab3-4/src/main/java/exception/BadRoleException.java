package exception;

public class BadRoleException extends RuntimeException {
    public BadRoleException() {
        super("Bad role!");
    }
    public BadRoleException(String message) {
        super(message);
    }
}
