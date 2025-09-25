package tank.data.exception;

/**
 * Signals an error caused by missing or incomplete arguments given by user
 */
public class TankArgumentMissingException extends RuntimeException {
    public TankArgumentMissingException(String message) {
        super(message);
    }
}
