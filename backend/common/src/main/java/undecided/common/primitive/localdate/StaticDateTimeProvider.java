package undecided.common.primitive.localdate;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 必ず同じ日時を返却する日時供給
 */
public class StaticDateTimeProvider extends DateProvider {

    private static AtomicReference<LocalDateTime> localDateTime = new AtomicReference<>();

    private StaticDateTimeProvider() {

    }

    public static void initialize(LocalDateTime localDateTime) {
        StaticDateTimeProvider instance = new StaticDateTimeProvider();
        instance.setLocalDateTime(localDateTime);
        setDateProvider(instance);

    }

    private void setLocalDateTime(LocalDateTime localDateTime) {
        StaticDateTimeProvider.localDateTime.set(localDateTime);

    }

    @Override
    protected LocalDateTime now() {
        return localDateTime.get();

    }

}
