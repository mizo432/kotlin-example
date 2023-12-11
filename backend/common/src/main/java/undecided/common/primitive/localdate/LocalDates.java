package undecided.common.primitive.localdate;


import com.google.common.base.Strings;
import org.jetbrains.annotations.NotNull;
import undecided.common.precondition.ObjectPreconditions;
import undecided.common.precondition.StringPreconditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class LocalDates {

    public static LocalDate tryToParse(@NotNull String string) {
        checkNotEmpty(string, "string");
        if (string.length() <= 8) {
//            checkAllCharactersAreDigits(string, () -> new IllegalArgumentException(
//                    "引数に10進数の数字以外の文字が含まれています。引数:" + string));
            if (string.length() == 1) {
                // 月内の日にちを1桁で渡した場合
                return LocalDate.parse(
                        DateProvider.currentYearMonth().format(DateTimeFormatter.ofPattern("yyyyMM"))
                                + Strings.padStart(string, 2, '0'),
                        DateTimeFormatter.ofPattern("yyyyMMdd").withResolverStyle(
                                ResolverStyle.LENIENT));
            }
            if (string.length() == 2) {
                // 月内の日にちを2桁で渡した場合
                return LocalDate.parse(
                        DateProvider.currentYearMonth().format(DateTimeFormatter.ofPattern("yyyyMM"))
                                + string,
                        DateTimeFormatter.ofPattern("yyyyMMdd").withResolverStyle(
                                ResolverStyle.LENIENT));
            }
            if (string.length() == 4) {
                // 月日を4桁で渡した場合
                return LocalDate.parse(
                        DateProvider.currentYear().format(DateTimeFormatter.ofPattern("yyyy")) + string,
                        DateTimeFormatter.ofPattern("yyyyMMdd").withResolverStyle(
                                ResolverStyle.LENIENT));
            }
            if (string.length() == 8) {
                // 年月日を6桁で渡した場合
                return LocalDate.parse(string,
                        DateTimeFormatter.ofPattern("yyyyMMdd").withResolverStyle(
                                ResolverStyle.LENIENT));
            }
        }
        if (string.length() == 10) {
            // 年月日を10桁（スラッシュ区切りまたはハイフン区切り）で渡した場合
            return LocalDate.parse(string.replace("/", "-"),
                    DateTimeFormatter.ISO_LOCAL_DATE.withResolverStyle(
                            ResolverStyle.LENIENT));
        }

        throw new IllegalArgumentException("引数は10桁または8桁,4桁,あるいは2桁でなければなりません 引数:" + string);


    }

    private static <T> void checkNotNull(@NotNull T reference, @NotNull String label) {
        ObjectPreconditions
                .checkNotNull(reference, () -> new IllegalArgumentException(String.format("引数の%sは必須ですが指定されていません", label)));

    }

    private static void checkNotEmpty(@NotNull String reference, @NotNull String label) {
        StringPreconditions.checkNotEmpty(label, () -> new IllegalArgumentException(
                "引数のlabelが空白です。引数のlabelに空白は許容されません。"));

        StringPreconditions.checkNotEmpty(reference, () -> new IllegalArgumentException(
                label + "が空白です。" + label + "に空白は許容されません。"));

    }

}
