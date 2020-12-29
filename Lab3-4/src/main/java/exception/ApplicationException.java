package exception;

public class ApplicationException extends RuntimeException{
    public ApplicationException() {
        super("Error sending application!");
    }
    public ApplicationException(String message) {
        super(message);
    }
}
