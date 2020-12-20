package exception;

public class RemoveApplicationsException extends Exception{
    public RemoveApplicationsException() {
        super("Error removing applications!");
    }
    public RemoveApplicationsException(String message) {
        super(message);
    }
}
