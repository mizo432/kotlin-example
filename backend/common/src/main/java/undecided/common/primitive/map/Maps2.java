package undecided.common.primitive.map;

import java.util.HashMap;
import java.util.Map;

public class Maps2 {

    public static <K, V> Map<K, V> newHashMap() {
        return new HashMap<>();
    }

    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map.isEmpty();
    }
}
