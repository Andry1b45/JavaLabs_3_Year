package exception;

public class BlockedUserException extends RuntimeException {
    public BlockedUserException() {
        super("You're blocked!");
    }

    public BlockedUserException(String message) {
        super(message);
    }
}
