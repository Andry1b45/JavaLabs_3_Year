package exception;

public class GetFacultiesException extends RuntimeException{
    public GetFacultiesException() {
        super("Error getting faculties!");
    }
    public GetFacultiesException(String message) {
        super(message);
    }
}
