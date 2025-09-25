package tank.data.exception;

/**
 * Signals an error caused by invalid command keywords
 */
public class TankCommandInvalidException extends Exception {
    public TankCommandInvalidException(String message) {
        super(message);
    }
}
