package exception;

public class GetApplicationsException extends Exception{
    public GetApplicationsException() {
        super("Error getting applications!");
    }
    public GetApplicationsException(String message) {
        super(message);
    }
}
