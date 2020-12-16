package exception;

public class BadRegionException extends RuntimeException {
    public BadRegionException() {
        super("Bad region name!");
    }
    public BadRegionException(String message) {
        super(message);
    }
}
