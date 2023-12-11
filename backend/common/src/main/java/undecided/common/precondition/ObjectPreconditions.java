package undecided.common.precondition;

import java.util.Objects;
import java.util.function.Supplier;

public class ObjectPreconditions {
    public static <T> void checkNotNull(T reference, Supplier<RuntimeException> exceptionSupplier) {
        if (Objects.isNull(reference))
            throw exceptionSupplier.get();
    }
}
