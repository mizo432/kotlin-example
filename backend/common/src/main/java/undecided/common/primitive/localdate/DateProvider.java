package undecided.common.primitive.localdate;

import java.time.*;
import java.time.temporal.ChronoField;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 現在日付を供給します
 */
public class DateProvider {

    private final static AtomicReference<DateProvider> dateProvider =
            new AtomicReference<>(new DateProvider());

    DateProvider() {

    }


    public static long currentTimeMillis() {
        LocalDateTime currentLocalDateTime = currentLocalDateTime();
        Instant instant = currentLocalDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return instant.toEpochMilli();
    }


    protected DateProvider(DateProvider aDateProvider) {
        DateProvider.setDateProvider(aDateProvider);
    }

    public static void setDateProvider(DateProvider aDateProvider) {
        DateProvider.dateProvider.set(aDateProvider);
    }

    /**
     * 現在日時をLocalDateTime型で取得します
     *
     * @return 現在日時
     */
    public static LocalDateTime currentLocalDateTime() {
        return DateProvider.dateProvider.get().now();

    }

    /**
     * 現在日付をLocalDate型で取得します
     *
     * @return 現在日付
     */
    public static LocalDate currentLocalDate() {
        return currentLocalDateTime().toLocalDate();

    }

    public static LocalTime currentLocalTime() {
        return currentLocalDateTime().toLocalTime();

    }

    /**
     * 現在の年月を取得する
     *
     * @return 現在の年月
     */
    public static YearMonth currentYearMonth() {
        LocalDateTime currentDateTime = currentLocalDateTime();
        return YearMonth.of(currentDateTime.getYear(), currentDateTime.getMonth());

    }

    public static Year currentYear() {
        LocalDate currentDate = currentLocalDate();
        return Year.of(currentDate.getYear());
    }

    protected LocalDateTime now() {
        LocalDateTime localDateTime = LocalDateTime.now();
        int nano = localDateTime.getNano();
        int milli = nano % 1000000;
        int newNano = nano - milli;
        return localDateTime.with(ChronoField.NANO_OF_SECOND, newNano);

    }

    /**
     * DateProviderを初期化する
     */
    public static void clear() {
        DateProvider.dateProvider.set(new DateProvider());

    }

}
