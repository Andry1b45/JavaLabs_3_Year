package exception;

public class GetFacultiesException extends Exception{
    public GetFacultiesException() {
        super("Error getting faculties!");
    }
    public GetFacultiesException(String message) {
        super(message);
    }
}
