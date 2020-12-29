package exception;

public class GetStudentsException extends RuntimeException{
    public GetStudentsException() {
        super("Error getting applications!");
    }
    public GetStudentsException(String message) {
        super(message);
    }
}
