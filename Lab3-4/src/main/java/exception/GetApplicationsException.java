package exception;

public class GetApplicationsException extends RuntimeException{
    public GetApplicationsException() {
        super("Error getting applications!");
    }
    public GetApplicationsException(String message) {
        super(message);
    }
}
