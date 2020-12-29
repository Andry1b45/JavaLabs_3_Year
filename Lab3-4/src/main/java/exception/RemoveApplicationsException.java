package exception;

public class RemoveApplicationsException extends RuntimeException{
    public RemoveApplicationsException() {
        super("Error removing applications!");
    }
    public RemoveApplicationsException(String message) {
        super(message);
    }
}
