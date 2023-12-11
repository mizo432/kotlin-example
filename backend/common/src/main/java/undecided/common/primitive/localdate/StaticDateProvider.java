package undecided.common.primitive.localdate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 同じ日で設定する日付供給
 */
public class StaticDateProvider extends DateProvider {

    private static final AtomicReference<LocalDate> localDate = new AtomicReference<>();

    public StaticDateProvider(LocalDate localDate) {
        super();
        StaticDateProvider.localDate.set(localDate);
    }

    public static void initialize(LocalDate localDate) {
        StaticDateProvider instance = new StaticDateProvider(localDate);
        new DateProvider(instance);
    }

    @Override
    protected LocalDateTime now() {
        return LocalDateTime.of(StaticDateProvider.localDate.get(), LocalTime.now());
    }

}
