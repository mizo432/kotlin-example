package undecided.common.precondition;

import java.util.function.Supplier;

public class StringPreconditions {
    public static void checkNotEmpty(String reference, Supplier<RuntimeException> exceptionSupplier) {
        ObjectPreconditions.checkNotNull(reference, exceptionSupplier);
        if (reference.isEmpty())
            throw exceptionSupplier.get();


    }
}
