package pl.koder95.cmf;

public class ModifierException extends Exception {

    public ModifierException() {
    }

    public ModifierException(String message) {
        super(message);
    }

    public ModifierException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModifierException(Throwable cause) {
        super(cause);
    }
}
