package exception;

public class EditFacultiesException extends RuntimeException{
    public EditFacultiesException() {
        super("Error editing faculties!");
    }
    public EditFacultiesException(String message) {
        super(message);
    }
}
