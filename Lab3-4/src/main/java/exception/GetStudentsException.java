package exception;

public class GetStudentsException extends Exception{
    public GetStudentsException() {
        super("Error getting applications!");
    }
    public GetStudentsException(String message) {
        super(message);
    }
}
