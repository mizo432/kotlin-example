package undecided.common.entity.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);

    }

    public EntityNotFoundException(String messageCode, Object... args) {
        super();

    }
}
