package exception;

public class EditFacultiesException extends Exception{
    public EditFacultiesException() {
        super("Error editing faculties!");
    }
    public EditFacultiesException(String message) {
        super(message);
    }
}
