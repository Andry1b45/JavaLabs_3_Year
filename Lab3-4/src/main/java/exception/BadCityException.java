package exception;

public class BadCityException extends RuntimeException {
    public BadCityException() {
        super("Incorrect city name!");
    }
    public BadCityException(String message) {
        super(message);
    }
}
