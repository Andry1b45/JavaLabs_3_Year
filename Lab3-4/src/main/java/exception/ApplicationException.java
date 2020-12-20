package exception;

public class ApplicationException extends Exception{
    public ApplicationException() {
        super("Error sending application!");
    }
    public ApplicationException(String message) {
        super(message);
    }
}
