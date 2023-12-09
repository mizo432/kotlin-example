package undefined.common;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entityName, Long id) {
        super("table:" + entityName + "に" + entityName + "Id:" + id + "は存在しません");
    }
}
