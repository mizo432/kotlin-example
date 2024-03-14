package undecided.common.primitive.objects;

import java.util.Objects;
import java.util.function.Function;

public class Objects2 {

    public static final Object[] EMPTY_ARRAY = new Object[0];

    public static boolean isNull(Object reference) {
        return reference == null;

    }

    public static boolean equal(Object o, Object o1) {
        return Objects.equals(o, o1);
    }

    public static boolean nonNull(Object o) {
        return o != null;
    }

    public static int hash(Object... objects) {
        return Objects.hash(objects);
    }

    public static <O> String ifPresent(O value, Function<O, String> function) {
        if (isNull(value))
            return null;

        return function.apply(value);
    }

    public static <T> T ifNull(final T o, final T defaultValue) {
        if (isNull(o))
            return defaultValue;

        return o;
    }
}
