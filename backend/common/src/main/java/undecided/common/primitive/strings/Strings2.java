package undecided.common.primitive.strings;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import undecided.common.primitive.objects.Objects2;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 文字列関連ユーティリティ
 */
public final class Strings2 {

    /**
     * A String for a space character. キャラクターハンドリングに使用します.
     */
    public static final String SPACE = " ";

    /**
     * A String for a 0 character. キャラクターハンドリングに使用します.
     */
    public static final String ZERO = "0";


    /**
     * A String for a Full-width space character. キャラクターハンドリングに使用します.
     */
    public static final String FULL_WIDTH_SPACE = "　";

    /**
     * The empty String {@code ""} キャラクターハンドリングに使用します..
     */
    public static final String EMPTY = "";

    /**
     * A String for a " character. キャラクターハンドリングに使用します.
     */
    public static final String DOUBLE_QUOTE = "\"";
    /**
     * A String for a ' character. キャラクターハンドリングに使用します.
     */
    public static final String SINGLE_QUOTE = "'";
    public static final String NULL = null;
    // 元号テーブル 新元号発生時は以下のテーブルを追加する

    // カタカナ文字を配列として準備
    private static final char[] HANKAKU_KATAKANA_KIGOU =
            {'｡', '｢', '｣', '､', '･', 'ｦ', 'ｧ', 'ｨ', 'ｩ', 'ｪ', 'ｫ', 'ｬ', 'ｭ',
                    'ｮ', 'ｯ', 'ｰ', 'ｱ', 'ｲ', 'ｳ', 'ｴ', 'ｵ', 'ｶ', 'ｷ', 'ｸ', 'ｹ',
                    'ｺ', 'ｻ', 'ｼ', 'ｽ', 'ｾ', 'ｿ', 'ﾀ', 'ﾁ', 'ﾂ', 'ﾃ', 'ﾄ', 'ﾅ',
                    'ﾆ', 'ﾇ', 'ﾈ', 'ﾉ', 'ﾊ', 'ﾋ', 'ﾌ', 'ﾍ', 'ﾎ', 'ﾏ', 'ﾐ', 'ﾑ',
                    'ﾒ', 'ﾓ', 'ﾔ', 'ﾕ', 'ﾖ', 'ﾗ', 'ﾘ', 'ﾙ', 'ﾚ', 'ﾛ', 'ﾜ', 'ﾝ',
                    'ﾞ', 'ﾟ'};

    private static final char[] ZENKAKU_KATAKANA_KIGOU =
            {'。', '「', '」', '、', '・', 'ヲ', 'ァ', 'ィ', 'ゥ', 'ェ', 'ォ', 'ャ', 'ュ',
                    'ョ', 'ッ', 'ー', 'ア', 'イ', 'ウ', 'エ', 'オ', 'カ', 'キ', 'ク', 'ケ',
                    'コ', 'サ', 'シ', 'ス', 'セ', 'ソ', 'タ', 'チ', 'ツ', 'テ', 'ト', 'ナ',
                    'ニ', 'ヌ', 'ネ', 'ノ', 'ハ', 'ヒ', 'フ', 'ヘ', 'ホ', 'マ', 'ミ', 'ム',
                    'メ', 'モ', 'ヤ', 'ユ', 'ヨ', 'ラ', 'リ', 'ル', 'レ', 'ロ', 'ワ', 'ン',
                    '゛', '゜'};

    private static final String[] HANKAKU_KATAKANA =
            {"ｧ", "ｱ", "ｨ", "ｲ", "ｩ", "ｳ", "ｪ", "ｴ", "ｫ", "ｵ", "ｶ", "ｶﾞ", "ｷ",
                    "ｷﾞ", "ｸ", "ｸﾞ", "ｹ", "ｹﾞ", "ｺ", "ｺﾞ", "ｻ", "ｻﾞ", "ｼ",
                    "ｼﾞ", "ｽ", "ｽﾞ", "ｾ", "ｾﾞ", "ｿ", "ｿﾞ", "ﾀ", "ﾀﾞ", "ﾁ",
                    "ﾁﾞ", "ｯ", "ﾂ", "ﾂﾞ", "ﾃ", "ﾃﾞ", "ﾄ", "ﾄﾞ", "ﾅ", "ﾆ", "ﾇ",
                    "ﾈ", "ﾉ", "ﾊ", "ﾊﾞ", "ﾊﾟ", "ﾋ", "ﾋﾞ", "ﾋﾟ", "ﾌ", "ﾌﾞ",
                    "ﾌﾟ", "ﾍ", "ﾍﾞ", "ﾍﾟ", "ﾎ", "ﾎﾞ", "ﾎﾟ", "ﾏ", "ﾐ", "ﾑ", "ﾒ",
                    "ﾓ", "ｬ", "ﾔ", "ｭ", "ﾕ", "ｮ", "ﾖ", "ﾗ", "ﾘ", "ﾙ", "ﾚ", "ﾛ",
                    "ﾜ", "ﾜ", "ｲ", "ｴ", "ｦ", "ﾝ", "ｳﾞ", "ｶ", "ｹ"};

    private static final char[] ZENKAKU_KATAKANA =
            {'ァ', 'ア', 'ィ', 'イ', 'ゥ', 'ウ', 'ェ', 'エ', 'ォ', 'オ', 'カ', 'ガ', 'キ',
                    'ギ', 'ク', 'グ', 'ケ', 'ゲ', 'コ', 'ゴ', 'サ', 'ザ', 'シ', 'ジ', 'ス',
                    'ズ', 'セ', 'ゼ', 'ソ', 'ゾ', 'タ', 'ダ', 'チ', 'ヂ', 'ッ', 'ツ', 'ヅ',
                    'テ', 'デ', 'ト', 'ド', 'ナ', 'ニ', 'ヌ', 'ネ', 'ノ', 'ハ', 'バ', 'パ',
                    'ヒ', 'ビ', 'ピ', 'フ', 'ブ', 'プ', 'ヘ', 'ベ', 'ペ', 'ホ', 'ボ', 'ポ',
                    'マ', 'ミ', 'ム', 'メ', 'モ', 'ャ', 'ヤ', 'ュ', 'ユ', 'ョ', 'ヨ', 'ラ',
                    'リ', 'ル', 'レ', 'ロ', 'ヮ', 'ワ', 'ヰ', 'ヱ', 'ヲ', 'ン', 'ヴ', 'ヵ',
                    'ヶ'};
    private static final String SLASH = "/";

    private static final String HYPHEN = "-";

    private static final String EQUAL = "=";

    private static final char ZENKAKU_KATAKANA_FIRST_CHAR = ZENKAKU_KATAKANA[0];

    private static final char ZENKAKU_KATAKANA_LAST_CHAR =
            ZENKAKU_KATAKANA[ZENKAKU_KATAKANA.length - 1];

    private static final char HANKAKU_KATAKANA_FIRST_CHAR =
            HANKAKU_KATAKANA_KIGOU[0];

    private static final char HANKAKU_KATAKANA_LAST_CHAR =
            HANKAKU_KATAKANA_KIGOU[HANKAKU_KATAKANA_KIGOU.length - 1];

    private static final String ZENKAKU_BLANK = " ";

    /**
     * A String for linefeed LF ("\n").
     */
    public static final String LF = "\n";

    /**
     * A String for carriage return CR ("\r").
     */
    public static final String CR = "\r";

    /**
     * A String for carriage return and linefeed CRLF ("\r").
     */
    public static final String CRLF = CR + LF;

    public static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * Represents a failed index search.
     */
    public static final int INDEX_NOT_FOUND = -1;

    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    /**
     * 文字列が空文字列またはnullかを判定する
     *
     * @param str 文字列
     * @return 文字列が空文字列またはnullの場合trueを返却する
     */
    public static boolean isEmpty(final String str) {
        return str == null || str.isEmpty();

    }

    /**
     * 文字列が空文字列またはnull以外かを判定する
     *
     * @param str 文字列
     * @return 文字列が空文字列またはnull以外の場合trueを返却する
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 文字列が指定の文字列の長さ（文字数）であるかを返します。
     * <pre>
     *   assertTrue(Strings2.isLength(EMPTY, 0));
     *   assertTrue(Strings2.isLength("ABC", 3));
     *   assertFalse(Strings2.isLength("ABC", 2));
     * </pre>
     *
     * @param str 文字列
     * @param len 長さ（文字数）
     * @return 文字列が指定の文字列長さを持つ場合trueを返します
     */
    public static boolean isLength(@NotNull String str, int len) {
        return (str.length() == len);

    }

    /**
     * 文字列が指定の文字列の長さ（文字数）であるかを返します。
     * <pre>
     *   assertTrue(Strings2.isLengthSilently(EMPTY, 0));
     *   assertTrue(Strings2.isLengthSilently("ABC", 3));
     *   assertTrue(Strings2.isLengthSilently(null, 0));
     *   assertFalse(Strings2.isLengthSilently("ABC", 2));
     *   assertFalse(Strings2.isLengthSilently(null, 2));
     * </pre>
     *
     * @param str 文字列
     * @param len 長さ（文字数）
     * @return 文字列が指定の文字列長さを持つ場合trueを返します
     */
    public static boolean isLengthSilently(@Nullable String str, int len) {
        if (isNull(str)) {
            return (len == 0);
        }
        return isLength(str, len);

    }

    /**
     * 文字列が指定の文字列長さのいずれかを持っているかを返します。
     * <pre>
     *    assertFalse(Strings2.isLengthContains(EMPTY));
     *    assertTrue(Strings2.isLengthContains(EMPTY, 0, 3));
     *    assertTrue(Strings2.isLengthContains("A", 1, 3));
     *    assertTrue(Strings2.isLengthContains("ABC", 0, 3));
     *    assertFalse(Strings2.isLengthContains("AB", 0, 3));
     * </pre>
     *
     * @param aStr       文字列
     * @param anyLengths 文字列の長さを可変引数で指定します（１件も存在しない場合はfalseを返します）
     * @return 文字列が指定の文字列長さのいずれかを持っている場合trueを返す
     */
    public static boolean isLengthContains(@NotNull String aStr, int... anyLengths) {
        for (int anyLength : anyLengths) {
            if (isLength(aStr, anyLength)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 文字列が指定の文字列長さのいずれかを持っているかを返します。
     * <pre>
     *    assertFalse(Strings2.isLengthContainsSilently(EMPTY));
     *    assertTrue(Strings2.isLengthContainsSilently(EMPTY, 0, 3));
     *    assertTrue(Strings2.isLengthContainsSilently("A", 1, 3));
     *    assertTrue(Strings2.isLengthContainsSilently("ABC", 0, 3));
     *    assertFalse(Strings2.isLengthContainsSilently("AB", 0, 3));
     * </pre>
     *
     * @param aStr       文字列
     * @param anyLengths 文字列の長さを可変引数で指定します（１件も存在しない場合はfalseを返します）
     * @return 文字列が指定の文字列長さのいずれかを持っている場合trueを返す
     */
    public static boolean isLengthContainsSilently(@Nullable String aStr, int... anyLengths) {
        for (int anyLength : anyLengths) {
            if (isLengthSilently(aStr, anyLength)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 文字列を繰り返し回数分繰り返した文字列を取得します。
     *
     * @param aStr           文字列
     * @param aNumberOfTimes 繰り返し回数
     * @return 文字列を繰り返し回数分繰り返した文字列
     */
    public static String repeat(@NotNull String aStr, int aNumberOfTimes) {

        final int len = aStr.length();
        final long longSize = (long) len * (long) aNumberOfTimes;
        final int size = (int) longSize;
        if (size != longSize) {
            throw new ArrayIndexOutOfBoundsException("Required array size too large: " + longSize);
        }

        final char[] array = new char[size];
        if (aNumberOfTimes == 0) {
            return "";
        }
        aStr.getChars(0, len, array, 0);
        int n;
        for (n = len; n < size - n; n <<= 1) {
            System.arraycopy(array, 0, array, n, n);
        }
        System.arraycopy(array, 0, array, n, size - n);
        return new String(array);

    }

    /**
     * 文字列が数値のみで構成されているかどうかを返します。
     * <pre>
     * assertThat(Strings2.isNumeric(null)).as("nullは数値ではない").isFalse();
     * assertThat(Strings2.isNumeric("")).as("空文字列は数値ではない").isFalse();
     * assertThat(Strings2.isNumeric(" ")).as("ブランクは数値ではない").isFalse();
     * assertThat(Strings2.isNumeric("123")).as("整数は数値である").isTrue();
     * assertThat(Strings2.isNumeric("12 3")).as("数値にブランクが入っている場合は数値ではない").isFalse();
     * assertThat(Strings2.isNumeric("ab2c")).as("アルファベット混在は数値ではない").isFalse();
     * assertThat(Strings2.isNumeric("12-3")).as("マイナス記号が先頭に無い場合は数値ではない").isFalse();
     * assertThat(Strings2.isNumeric("12.3")).as("小数値は数値である").isTrue();
     * assertThat(Strings2.isNumeric("-123")).as("負の整数は数値である").isTrue();
     * assertThat(Strings2.isNumeric("-123.4")).as("少数ありの負数は数値である").isTrue();
     * assertThat(Strings2.isNumeric("+123")).as("正の整数にプラス記号が先頭についている場合は数値ではない").isFalse();
     * </pre>
     *
     * @param s 文字列
     * @return 数値のみで構成されている場合、<code>true</code>
     */
    public static boolean isNumeric(final CharSequence s) {
        if (CharSequences.isEmpty(s)) {
            return false;
        }
        int startPosition = 0;

        if ('-' == s.charAt(0)) {
            startPosition = 1;
        }
        final int size = s.length();

        if ('.' == s.charAt(startPosition))
        //小数点が最初の桁の場合対象外
        {
            return false;
        }

        if ('.' == s.charAt(size - 1))
        //小数点が最後の桁の場合対象外
        {
            return false;
        }
        if (countCharacters(s, '.') > 1)
        //小数点が複数ある対象外
        {
            return false;
        }

        for (int i = startPosition; i < size; i++) {
            final char chr = s.charAt(i);
            if (!Character.isDigit(chr) && '.' != chr) {
                return false;
            }
        }

        return true;

    }

    private final static String[][] hanZenKigouTaiouArray =
            new String[][]{{"!", "！"}, {"?", "？"}, {",", "，"},
                    {"(", "（"}, {")", "）"}, {"#", "＃"}, {"$", "＄"},
                    {"\\", "￥"}, {"+", "＋"}, {"-", "－"}, {"@", "＠"},
                    {"/", "／"}, {"*", "＊"}, {".", "．"}};

    private static int countCharacters(@NotNull final CharSequence str,
                                       final char aSearchCharacter) {
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == aSearchCharacter) {
                result += 1;
            }
        }
        return result;

    }

    /**
     * 文字列を囲みます.
     *
     * <p>
     * <pre>{@code surround("hogeHoge", Strings2.DOUBLE_QUOTE) }</pre>
     *
     * @param value 文字列
     * @param s     囲みたい文字列
     * @return 文字列を囲んだ結果を返却します。
     */
    public static String surround(String value, String s) {
        return s + value + s;
    }

    /**
     * 文字列を置き換えます。
     *
     * @param text     テキスト
     * @param fromText 置き換え対象のテキスト
     * @param toText   置き換えるテキスト
     * @return 結果
     */
    public static String replace(final String text, final String fromText, final String toText) {

        if (text == null || fromText == null || toText == null) {
            return null;
        }
        StringBuilder buf = new StringBuilder(100);
        int pos = 0;
        int pos2 = 0;
        while (true) {
            pos = text.indexOf(fromText, pos2);
            if (pos == 0) {
                buf.append(toText);
                pos2 = fromText.length();
            } else if (pos > 0) {
                buf.append(text.substring(pos2, pos));
                buf.append(toText);
                pos2 = pos + fromText.length();
            } else {
                buf.append(text.substring(pos2));
                break;
            }
        }
        return buf.toString();
    }

    /**
     * <p>Gets the leftmost {@code len} characters of a String.</p>
     *
     * <p>If {@code len} characters are not available, or the
     * String is {@code null}, the String will be returned without an exception. An empty String is
     * returned if len is negative.</p>
     *
     * <pre>
     * Strings2.left(null, *)    = null
     * Strings2.left(*, -ve)     = ""
     * Strings2.left("", *)      = ""
     * Strings2.left("abc", 0)   = ""
     * Strings2.left("abc", 2)   = "ab"
     * Strings2.left("abc", 4)   = "abc"
     * </pre>
     *
     * @param str the String to get the leftmost characters from, may be null
     * @param len the length of the required String
     * @return the leftmost characters, {@code null} if null String input
     */
    public static String left(final String str, final int len) {
        if (isNull(str)) {
            return null;

        }
        if (len < 0) {
            return EMPTY;

        }
        if (str.length() <= len) {
            return str;

        }
        return str.substring(0, len);

    }

    /**
     * <p>Gets the rightmost {@code len} characters of a String.</p>
     *
     * <p>If {@code len} characters are not available, or the String
     * is {@code null}, the String will be returned without an an exception. An empty String is
     * returned if len is negative.</p>
     *
     * <pre>
     * Strings2.right(null, *)    = null
     * Strings2.right(*, -ve)     = ""
     * Strings2.right("", *)      = ""
     * Strings2.right("abc", 0)   = ""
     * Strings2.right("abc", 2)   = "bc"
     * Strings2.right("abc", 4)   = "abc"
     * </pre>
     *
     * @param str the String to get the rightmost characters from, may be null
     * @param len the length of the required String
     * @return the rightmost characters, {@code null} if null String input
     */
    public static String right(final String str, final int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return EMPTY;
        }
        if (str.length() <= len) {
            return str;
        }
        return str.substring(str.length() - len);
    }

    public static String[] split(String str) {
        return split(str, null, -1);
    }

    public static String[] split(String str, char separatorChar) {
        return splitWorker(str, separatorChar, false);
    }

    public static String[] split(String str, String separatorChars) {
        return splitWorker(str, separatorChars, -1, false);
    }

    public static String[] split(String str, String separatorChars, int max) {
        return splitWorker(str, separatorChars, max, false);
    }

    private static String[] splitWorker(String str, char separatorChar, boolean preserveAllTokens) {
        if (str == null) {
            return new String[]{};
        } else {
            int len = str.length();
            if (len == 0) {
                return EMPTY_STRING_ARRAY;
            } else {
                List<String> list = new ArrayList<>();
                int i = 0;
                int start = 0;
                boolean match = false;
                boolean lastMatch = false;

                while (true) {
                    while (i < len) {
                        if (str.charAt(i) == separatorChar) {
                            if (match || preserveAllTokens) {
                                list.add(str.substring(start, i));
                                match = false;
                                lastMatch = true;
                            }

                            ++i;
                            start = i;
                        } else {
                            lastMatch = false;
                            match = true;
                            ++i;
                        }
                    }

                    if (match || preserveAllTokens && lastMatch) {
                        list.add(str.substring(start, i));
                    }

                    return list.toArray(new String[0]);
                }
            }
        }
    }

    /**
     * 文字列が数値表現かどうかを返します。
     *
     * @param str 文字列
     * @return 数値表現である場合、<code>true</code>
     */
    public static boolean isNumber(final String str) {
        if (isEmpty(str)) {
            return false;
        }
        final char[] chars = str.toCharArray();
        int sz = chars.length;
        boolean hasExp = false;
        boolean hasDecPoint = false;
        boolean allowSigns = false;
        boolean foundDigit = false;
        final int start = (chars[0] == '-') ? 1 : 0;
        if (sz > start + 1 && chars[start] == '0') {
            if ((chars[start + 1] == 'x') || (chars[start + 1] == 'X')) {
                int i = start + 2;
                if (i == sz) {
                    return false;
                }
                for (; i < chars.length; i++) {
                    if ((chars[i] < '0' || chars[i] > '9') && (chars[i] < 'a' || chars[i] > 'f')
                            && (chars[i] < 'A' || chars[i] > 'F')) {
                        return false;
                    }
                }
                return true;
            } else if (Character.isDigit(chars[start + 1])) {
                int i = start + 1;
                for (; i < chars.length; i++) {
                    if (chars[i] < '0' || chars[i] > '7') {
                        return false;
                    }
                }
                return true;
            }
        }
        sz--;
        int i = start;
        while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                foundDigit = true;
                allowSigns = false;

            } else if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    // two decimal points org dec in exponent
                    return false;
                }
                hasDecPoint = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                // we've already taken care of hex.
                if (hasExp) {
                    // two E's
                    return false;
                }
                if (!foundDigit) {
                    return false;
                }
                hasExp = true;
                allowSigns = true;
            } else if (chars[i] == '+' || chars[i] == '-') {
                if (!allowSigns) {
                    return false;
                }
                allowSigns = false;
                foundDigit = false;
            } else {
                return false;
            }
            i++;
        }
        if (i < chars.length) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                // no type qualifier, OK
                return true;
            }
            if (chars[i] == 'e' || chars[i] == 'E') {
                return false;
            }
            if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    return false;
                }
                return foundDigit;
            }
            if (!allowSigns && (chars[i] == 'd' || chars[i] == 'D' || chars[i] == 'f'
                    || chars[i] == 'F')) {
                return foundDigit;
            }
            if (chars[i] == 'l' || chars[i] == 'L') {
                return foundDigit && !hasExp && !hasDecPoint;
            }
            return false;
        }
        return !allowSigns && foundDigit;
    }

    public static String stripEnd(String str, String stripChars) {
        int end;
        if (str != null && (end = str.length()) != 0) {
            if (stripChars == null) {
                while (end != 0 && Character.isWhitespace(str.charAt(end - 1))) {
                    --end;
                }
            } else {
                if (stripChars.isEmpty()) {
                    return str;
                }

                while (end != 0 && stripChars.indexOf(str.charAt(end - 1)) != -1) {
                    --end;
                }
            }

            return str.substring(0, end);
        } else {
            return str;
        }
    }

    public static int indexOf(CharSequence seq, int searchChar) {
        return CharSequences.isEmpty(seq) ? -1 : CharSequences.indexOf(seq, searchChar);
    }

    public static int indexOfSilently(CharSequence seq, int searchChar, int startPos) {
        return CharSequences.isEmpty(seq) ? -1 : CharSequences.indexOf(seq, searchChar, startPos);
    }

    public static int indexOfSilently(CharSequence seq, CharSequence searchSeq) {
        return seq != null && searchSeq != null ? CharSequences.indexOf(seq, searchSeq) : -1;
    }

    private static String[] splitWorker(String str, String separatorChars, int max,
                                        boolean preserveAllTokens) {
        if (isNull(str)) {
            return new String[]{};
        } else {
            int len = str.length();
            if (len == 0) {
                return EMPTY_STRING_ARRAY;
            } else {
                List<String> list = new ArrayList<>();
                int sizePlus1 = 1;
                int i = 0;
                int start = 0;
                boolean match = false;
                boolean lastMatch = false;
                label87:
                while (true) {
                    while (true) {
                        if (i >= len) {
                            break label87;
                        }

                        if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                            if (match || preserveAllTokens) {
                                lastMatch = true;
                                if (sizePlus1++ == max) {
                                    i = len;
                                    lastMatch = false;
                                }

                                list.add(str.substring(start, i));
                                match = false;
                            }

                            ++i;
                            start = i;
                        } else {
                            lastMatch = false;
                            match = true;
                            ++i;
                        }
                    }
                }

                if (match || preserveAllTokens && lastMatch) {
                    list.add(str.substring(start, i));
                }

                return list.toArray(EMPTY_STRING_ARRAY);
            }
        }
    }

    public static int ordinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal) {
        return ordinalIndexOf(str, searchStr, ordinal, false);
    }

    public static int indexOfSilently(CharSequence seq, CharSequence searchSeq, int startPos) {
        return seq != null && searchSeq != null ?
                CharSequences.indexOf(seq, searchSeq, startPos) :
                -1;
    }


    /**
     * 配列文字列をジョインする
     *
     * @param stringSupplier ジョイン時のデリミタサプライア
     * @param strings        配列文字列
     * @return ジョイン後文字列
     */
    public static String join(Supplier<String> stringSupplier, String... strings) {
        CharSequence c = stringSupplier.get();
        return join(c, strings);
    }

    /**
     * コレクションをデリミタサプライヤを使用しジョインする.
     * <pre>
     *     {@code
     *         String actual = Strings2.join(() -> ", \n", newArrayList("ABC","DEF"));
     *     }
     * </pre>
     *
     * @param delimiterSupplier ジョイン時のデリミタサプライア
     * @param strings           コレクション文字列
     * @return ジョイン後文字列
     */
    public static String join(Supplier<String> delimiterSupplier, Collection<String> strings) {
        CharSequence delimiter = delimiterSupplier.get();
        return join(delimiter, strings);

    }

    /**
     * コレクションをデリミタでつなぎジョインする。
     * <pre>
     *     {@code
     *         String actual = Strings2.join(", \n", newArrayList("ABC","DEF"));
     *     }
     * </pre>
     *
     * @param delimiter  デリミタ
     * @param stringList 文字列のリスト
     * @return ジョイン後の文字列
     */
    public static String join(CharSequence delimiter, Collection<String> stringList) {
        StringBuilder sb = new StringBuilder();
        for (String string : stringList) {
            sb.append(string).append(delimiter);
        }
        if (stringList.size() > 0) {
            sb.setLength(sb.length() - delimiter.length());
        }
        return sb.toString();
    }


    /**
     * 配列文字列をジョインする
     *
     * @param c       ジョイン時のデリミタ
     * @param strings 配列文字列
     * @return ジョイン後文字列
     */
    public static String join(CharSequence c, String... strings) {
        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            sb.append(string).append(c);
        }
        if (strings.length > 0) {
            sb.setLength(sb.length() - c.length());
        }
        return sb.toString();
    }

    /**
     * 配列文字列をジョインする
     *
     * @param c       ジョイン時のデリミタ
     * @param strings 配列文字列
     * @return ジョイン後文字列
     */
    public static String join(char c, String... strings) {
        CharSequence delimiter = CharBuffer.wrap(new char[]{c});
        return Strings2.join(delimiter, strings);

    }

    /**
     * System.lineSeparator()をデリミタに使用し、配列文字列をジョインする
     *
     * @param strings 配列文字列
     * @return ジョイン後文字列
     */
    public static String join(String... strings) {
        String delimiter = System.lineSeparator();
        return join(delimiter, strings);
    }

    private static int ordinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal,
                                      boolean lastIndex) {
        if (str != null && searchStr != null && ordinal > 0) {
            if (searchStr.length() == 0) {
                return lastIndex ? str.length() : 0;
            } else {
                int found = 0;
                int index = lastIndex ? str.length() : -1;

                do {
                    if (lastIndex) {
                        index = CharSequences.lastIndexOf(str, searchStr, index - 1);
                    } else {
                        index = CharSequences.indexOf(str, searchStr, index + 1);
                    }

                    if (index < 0) {
                        return index;
                    }

                    ++found;
                } while (found < ordinal);

                return index;
            }
        } else {
            return -1;
        }
    }

    /**
     * System.lineSeparator()をデリミタに使用し、配列文字列をジョインする
     *
     * @param strings 配列文字列
     * @return ジョイン後文字列
     */
    public static String join(Collection<String> strings) {
        String delimiter = System.lineSeparator();
        return join(delimiter, strings);

    }

    /**
     * 左側の空白を削ります。
     *
     * @param text テキスト
     * @return 結果の文字列
     */
    public static String ltrim(final String text) {
        return ltrim(text, null);

    }

    /**
     * 左側の指定した文字列を削ります。
     *
     * <pre>
     * Strings2.ltrim(null, " ")    = null
     * Strings2.ltrim(null, null)    = null
     * Strings2.ltrim("", null)    = ""
     * Strings2.ltrim(" ", null)    = ""
     * Strings2.ltrim("ABC", "ABC")    = ""
     * Strings2.ltrim("ABCABC", "ABC")    = ""
     * Strings2.ltrim("ABCDEF", "ABC")    = "DEF"
     * Strings2.ltrim("ABCDEF", "ABC")    = "DEF"
     * Strings2.ltrim("ABCDABC", "ABC")    = "DABC"
     * Strings2.ltrim("AB", "ABC")    = "AB"
     * </pre>
     *
     * @param text     テキスト
     * @param trimText 削るテキスト
     * @return 結果の文字列
     */
    public static String ltrim(final String text, String trimText) {
        if (text == null) {
            return null;
        }
        if (trimText == null) {
            trimText = " ";
        }
        if (text.length() < trimText.length()) {
            return text;
        }
        if (text.startsWith(trimText)) {
            return ltrim(text.substring(trimText.length()), trimText);
        }
        return text;

    }

    /**
     * 右側の空白を削ります。
     *
     * @param text テキスト
     * @return 結果の文字列
     */
    public static String rtrim(final String text) {
        return rtrim(text, null);
    }

    /**
     * 右側の指定した文字列を削ります。
     *
     * <pre>
     * Strings2.rtrim(null, " ")    = null
     * Strings2.rtrim(null, null)    = null
     * Strings2.rtrim("", null)    = ""
     * Strings2.rtrim(" ", null)    = ""
     * Strings2.rtrim("DEF", "DEF")    = ""
     * Strings2.rtrim("ABCABC", "DEF")    = "ABCABC"
     * Strings2.rtrim("ABCDEF", "DEF")    = "ABC"
     * Strings2.rtrim("DEFDEF", "DEF")    = "DEF"
     * Strings2.rtrim("DEFDDEF", "DEF")    = "DEFD"
     * Strings2.rtrim("DE", "DEF")    = "DE"
     * </pre>
     *
     * @param text     テキスト
     * @param trimText 削る文字列
     * @return 結果の文字列
     */
    public static String rtrim(final String text, String trimText) {
        if (text == null) {
            return null;
        }
        if (trimText == null) {
            trimText = " ";
        }
        if (text.length() < trimText.length()) {
            return text;
        }
        if (text.endsWith(trimText)) {
            return rtrim(text.substring(0, text.length() - trimText.length()), trimText);
        }

        return text;
    }

    /**
     * サフィックスを削ります。
     *
     * @param text   テキスト
     * @param suffix サフィックス
     * @return 結果の文字列
     */
    public static String trimSuffix(@Nullable final String text, final String suffix) {
        if (text == null) {
            return null;
        }
        if (suffix == null) {
            return text;
        }
        if (text.endsWith(suffix)) {
            return text.substring(0, text.length() - suffix.length());
        }
        return text;
    }

    /**
     * プレフィックスを削ります。
     *
     * @param text   テキスト
     * @param prefix プレフィックス
     * @return 結果の文字列
     */
    public static String trimPrefix(final String text, final String prefix) {
        if (text == null) {
            return null;
        }
        if (prefix == null) {
            return text;
        }
        if (text.startsWith(prefix)) {
            return text.substring(prefix.length());
        }
        return text;
    }

    /**
     * 配列文字列がnullでない場合にジョインした結果を返す
     * <pre>
     * 配列文字列がnullの場合は例外が出ます。
     * </pre>
     * <pre>
     * <h3>使用方法</h3>
     * result = joinIfNotNull("ABC",null,DEF)
     * result = joinIfNotNull("ABC",null,null)
     * result = joinIfNotNull()
     * </pre>
     * <pre>
     * <h3>仕様</h3>
     * assertThat(joinIfNotNull()).isEqualTo();
     * assertThat(joinIfNotNull(null)).isEqualTo("");
     * assertThat(joinIfNotNull("ABC")).isEqualTo("ABC");
     * assertThat(joinIfNotNull("ABC","DEF","GHI")).isEqualTo("ABCDEFGHI");
     * assertThat(joinIfNotNull((String)null,"DEF","GHI")).isEqualTo("DEFGHI");
     * assertThat(joinIfNotNull("ABC",(String)null,"GHI")).isEqualTo("ABCGHI");
     * assertThat(joinIfNotNull("ABC","DEF",(String)null).isEqualTo("ABCDEF");
     * String[] stringArray = null;
     * joinIfNotNull(stringArray); // throwed IllegalArgumentException.
     *
     * </pre>
     *
     * @param strings 配列文字列
     * @return ジョイン後文字列
     */
    public static String joinIfNotNull(@NotNull String... strings) {
        if (strings == null) {
            throw new IllegalArgumentException("引数stringsがnullです。null以外の値を指定してください");
        }
        ;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            if (string != null) {
                result.append(string);
            } else {
                throw new IllegalArgumentException(
                        "引数stringsの要素にnullが含まれています。null以外の値を指定してください index:" + i);

            }
        }
        return result.toString();

    }

    /**
     * JavaBeansの仕様にしたがってアンキャピタライズを行ないます。
     * <p>
     * 大文字が2つ以上続く場合は、小文字にならないので注意してください。
     * </p>
     * <p>
     * 次のように使います．
     * </p>
     *
     * <pre>
     * Strings2.uncapitalize("UserId")  = "userId"
     * Strings2.uncapitalize("ABC")  = "ABC"
     * </pre>
     *
     * @param name 名前
     * @return 結果の文字列
     */
    public static String uncapitalize(final String name) {
        if (isEmpty(name)) {
            return name;
        }
        final char[] chars = name.toCharArray();
        if (chars.length >= 2 && Character.isUpperCase(chars[0]) && Character
                .isUpperCase(chars[1])) {
            return name;
        }
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);

    }

    private static boolean isNull(Collection<?> reference) {
        return Objects2.isNull(reference);

    }

    public static String collectionToCommaDelimitedString(@Nullable Collection<?> coll) {
        return collectionToDelimitedString(coll, ",");
    }

    public static String collectionToDelimitedString(@Nullable Collection<?> coll, String delim) {
        return collectionToDelimitedString(coll, delim, "", "");
    }

    /**
     * JavaBeansの仕様にしたがってキャピタライズを行ないます。
     * <p>
     * 次のように使います．
     * </p>
     *
     * <pre>
     * Strings2.capitalize("userId")  = "UserId"
     * Strings2.capitalize("ABC")  = "ABC"
     * </pre>
     *
     * @param name 名前
     * @return 結果の文字列
     */
    public static String capitalize(final String name) {
        if (isEmpty(name)) {
            return name;
        }
        final char[] chars = name.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }

    /**
     * 文字列がブランクかを返却する. /**
     * <p>Checks if a CharSequence is empty (""), null or whitespace only.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * Strings2.isBlank(null)      = true
     * Strings2.isBlank("")        = true
     * Strings2.isBlank(" ")       = true
     * Strings2.isBlank("  ")       = true
     * Strings2.isBlank("bob")     = false
     * Strings2.isBlank("  bob  ") = false
     * </pre>
     *
     * @param cs CharSequence to check, may be null
     * @return {@code true} if the CharSequence is null, empty or whitespace only
     */
    public static boolean isBlank(final CharSequence cs) {
        final int strLen = length(cs);
        if (strLen == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;

    }

    /**
     * Gets a CharSequence length or {@code 0} if the CharSequence is {@code null}.
     *
     * @param cs a CharSequence or {@code null}
     * @return CharSequence length or {@code 0} if the CharSequence is {@code null}.
     */
    public static int length(final CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }

    /**
     * 文字列がnullかを返却する.
     *
     * @param string 文字列
     * @return ブランクの場合true
     */
    public static boolean isNull(String string) {
        return null == string;

    }

    public static String trim(String str, String stripChars) {
        return str == null ? null : ltrim(rtrim(str, stripChars), stripChars);
    }

    public static String trimToNull(String str, String stripChars) {
        String ts = ltrim(rtrim(str, stripChars), stripChars);
        return isEmpty(ts) ? null : ts;
    }

    public static String trimToEmpty(String str, String stripChars) {
        return str == null ? EMPTY : ltrim(rtrim(str, stripChars), stripChars);
    }

    public static String nullToEmpty(String str) {
        return str == null ? EMPTY : str;
    }

    /**
     * 入力文字列が空文字の場合にnullを返却する
     *
     * @param str 入力文字列
     * @return 返却文字列
     */
    public static String emptyToNull(String str) {
        return isEmpty(str) ? null : str;

    }

    /**
     * サーチキャラクターが検索対象の文字列に含まれているかを返却する.
     *
     * <pre>
     *    このメソッドでは{@link String#indexOf(int)}を使用する.
     * </pre>
     *
     * <pre>
     * <code>
     * null
     * </code>
     *    または 空白 (&quot;&quot;) の文字列は
     * <code>
     * false
     * </code>
     * を返却する
     * </pre>
     *
     * <pre>
     *     Strings2.contains(null, *)    = false
     *     Strings2.contains(&quot;&quot;, *)      = false
     *     Strings2.contains(&quot;abc&quot;, 'a') = true
     *     Strings2.contains(&quot;abc&quot;, 'z') = false
     * </pre>
     *
     * @param str        検索対象の文字列
     * @param searchChar サーチキャラクター
     * @return 見つかった場合true 見つからない場合falseを返却する
     */
    public static boolean contains(String str, char searchChar) {
        if (isEmpty(str)) {
            return false;
        }
        return str.indexOf(searchChar) >= 0;
    }

    /**
     * サーチ文字列が検索対象の文字列に含まれているかを返却する.
     *
     * <pre>
     *    このメソッドでは{@link String#indexOf(int)}を使用する.
     * </pre>
     *
     * <pre>
     * <code>
     * null
     * </code>
     *    または 空白 (&quot;&quot;) の文字列は
     * <code>
     * false
     * </code>
     * を返却する
     * </pre>
     *
     * <pre>
     *     Strings2.contains(null, *)     = false
     *     Strings2.contains(*, null)     = false
     *     Strings2.contains(&quot;&quot;, &quot;&quot;)      = true
     *     Strings2.contains(&quot;abc&quot;, &quot;&quot;)   = true
     *     Strings2.contains(&quot;abc&quot;, &quot;a&quot;)  = true
     *     Strings2.contains(&quot;abc&quot;, &quot;z&quot;)  = false
     * </pre>
     *
     * @param str       検索対象の文字列
     * @param searchStr サーチキャラクター
     * @return 見つかった場合true 見つからない場合falseを返却する
     */
    public static boolean contains(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        return str.contains(searchStr);
    }

    /**
     * この文字列の部分文字列である新しい文字列を返します。
     * <pre>
     * この文字列の部分文字列である文字列を返します。部分文字列は指定されたインデックスで始まり、この文字列の最後までになります。
     * </pre>
     *
     * @param s          文字列
     * @param beginIndex 開始インデックス(この値を含む)。
     * @return 指定された部分文字列。
     */
    public static String substring(String s, int beginIndex) {
        if (s == null) {
            return "";
        }
        return s.substring(beginIndex);

    }

    public static String substring(String s, int beginIndex, int endIndex) {
        if (s == null) {
            return "";
        }
        return s.substring(beginIndex, endIndex);

    }

    /**
     * 第一引数の文字列と第二引数の文字列が一致している場合trueを返却する
     *
     * @param str1 第一引数
     * @param str2 第二引数
     * @return 一致している場合はtrueを返す
     */
    public static boolean equals(@Nullable String str1, @Nullable String str2) {
        if (isNull(str1) && isNull(str2)) {
            return true;
        }
        if (isNull(str1)) {
            return false;
        }
        if (isNull(str2)) {
            return false;
        }
        return str1.equals(str2);
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);

    }

    /**
     * 大文字小文字を無視して文字列同士が等しいかどうか返します。どちらもnullの場合は、<code>true</code>を返します。
     *
     * @param target1 文字列1
     * @param target2 文字列2
     * @return 大文字小文字を無視して文字列同士が等しければ{@literal true}
     */
    public static boolean equalsIgnoreCase(String target1, String target2) {
        return (target1 == null) ? (target2 == null) : target1.equalsIgnoreCase(target2);
    }

    /**
     * 大文字小文字を無視して特定の文字で終わっているのかどうかを返します。
     *
     * @param target1 テキスト
     * @param target2 比較する文字列
     * @return 大文字小文字を無視して特定の文字で終わっていれば{@literal true}
     */
    public static boolean endsWithIgnoreCase(final String target1, final String target2) {
        if (target1 == null || target2 == null) {
            return false;
        }
        final int length1 = target1.length();
        final int length2 = target2.length();
        if (length1 < length2) {
            return false;
        }
        final String s1 = target1.substring(length1 - length2);
        return s1.equalsIgnoreCase(target2);
    }

    /**
     * 特定の文字で終わっているのかどうかを返します。
     *
     * @param target1 テキスト
     * @param target2 比較する文字列
     * @return 特定の文字で終わっていれば{@literal true}
     */
    public static boolean endsWith(final String target1, final String target2) {
        if (isNull(target1) || isNull(target2)) {
            return false;
        }
        final int length1 = target1.length();
        final int length2 = target2.length();
        if (length1 < length2) {
            return false;
        }
        final String s1 = target1.substring(length1 - length2);
        return s1.equals(target2);
    }

    /**
     * 大文字小文字を無視して特定の文字で始まっているのかどうかを返します。
     *
     * @param target1 テキスト
     * @param target2 比較する文字列
     * @return 大文字小文字を無視して特定の文字で始まっていれば{@literal true}
     */
    public static boolean startsWithIgnoreCase(final String target1, final String target2) {
        if (target1 == null || target2 == null) {
            return false;
        }
        final int length1 = target1.length();
        final int length2 = target2.length();
        if (length1 < length2) {
            return false;
        }
        final String s1 = target1.substring(0, target2.length());
        return s1.equalsIgnoreCase(target2);
    }

    /**
     * 特定の文字で始まっているのかどうかを返します。
     *
     * @param target1 テキスト
     * @param target2 比較する文字列
     * @return 特定の文字で始まっていれば{@literal true}
     */
    public static boolean startsWith(final String target1, final String target2) {
        if (isNull(target1) || isNull(target2)) {
            return false;
        }
        final int length1 = target1.length();
        final int length2 = target2.length();
        if (length1 < length2) {
            return false;
        }
        final String s1 = target1.substring(0, target2.length());
        return s1.equals(target2);
    }

    /**
     * 文字列の最後から指定した文字列で始まっている部分より手前を返します。
     *
     * @param str       文字列
     * @param separator セパレータ
     * @return 結果の文字列
     */
    public static String substringFromLast(final String str, final String separator) {
        if (isEmpty(str) || isEmpty(separator)) {
            return str;
        }
        final int pos = str.lastIndexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }

    /**
     * 引数の文字列を返します。引数の文字列が<code>null</code>だったら<code>defaultStr</code>を返します。
     * <p>
     * 次のように使います．
     * </p>
     *
     * <pre>
     * Strings2.defaultString(null, "NULL")  = "NULL"
     * Strings2.defaultString("", "NULL")    = ""
     * Strings2.defaultString("aaa", "NULL") = "aaa"
     * Strings2.defaultString("aaa", null) = "aaa"
     * Strings2.defaultString(null, null) = null
     * </pre>
     *
     * @param str        文字列(<code>null</code>可)
     * @param defaultStr 引数の文字列が<code>null</code>だったら返す文字列(<code>null</code>可)
     * @return 引数の文字列を返します。引数の文字列が<code>null</code>だったら<code>defaultStr</code> を返します。
     */
    public static String defaultString(final String str, final String defaultStr) {
        return str == null ? defaultStr : str;

    }

    /**
     * 空の場合にデフォルト文字列を返します。
     * <p>
     * 引数の文字列が<code>EMPTY</code>または<code>null</code>だったら<code>defaultStr</code>を返します。
     * <p>
     * 次のように使います．
     * </p>
     *
     * <pre>
     * Strings2.defaultIfEmpty(null, "NULL")  = "NULL"
     * Strings2.defaultIfEmpty("", "NULL")    = "NULL"
     * Strings2.defaultIfEmpty("aaa", "NULL") = "aaa"
     * Strings2.defaultIfEmpty("aaa", null) = "aaa"
     * Strings2.defaultIfEmpty(null, null) = null
     * </pre>
     *
     * @param str        文字列(<code>null</code>可)
     * @param defaultStr 引数の文字列が<code>null</code>だったら返す文字列(<code>null</code>可)
     * @return 引数の文字列を返します。引数の文字列が<code>null</code>だったら<code>defaultStr</code> を返します。
     */
    public static String defaultIfEmpty(final String str, final String defaultStr) {
        return isEmpty(str) ? defaultStr : str;

    }

    public static String collectionToDelimitedString(@Nullable Collection<?> coll, String delim,
                                                     String prefix, String suffix) {
        if (isNull(coll)) {
            return EMPTY;
        }

        if (coll.isEmpty()) {
            return EMPTY;
        } else {
            int totalLength = coll.size() * (prefix.length() + suffix.length())
                    + (coll.size() - 1) * delim.length();

            Object element;
            for (Iterator var5 = coll.iterator(); var5.hasNext();
                 totalLength += String.valueOf(element).length()) {
                element = var5.next();
            }

            StringBuilder sb = new StringBuilder(totalLength);
            Iterator<?> it = coll.iterator();

            while (it.hasNext()) {
                sb.append(prefix).append(it.next()).append(suffix);
                if (it.hasNext()) {
                    sb.append(delim);
                }
            }

            return sb.toString();
        }
    }

    /**
     * 引数の文字列を返します。引数の文字列が<code>null</code>だったら空文字を返します。
     * <p>
     * 次のように使います．
     * </p>
     *
     * <pre>
     * Strings2.defaultString(null)  = ""
     * Strings2.defaultString("")    = ""
     * Strings2.defaultString("aaa") = "aaa"
     * </pre>
     *
     * @param str 文字列(<code>null</code>可)
     * @return 引数の文字列を返します。引数の文字列が<code>null</code>だったら空文字を返します。
     */
    public static String defaultString(final String str) {
        return str == null ? EMPTY : str;

    }

    /**
     * 文字列を元に、オブジェクトを返却する（文字列はEMPTY）
     * <p>
     * 次のように使います．
     * </p>
     *
     * <pre>
     * Strings2.defaultObjectIfEmpty(null, null, () -\gt; "") -\gt; NullPointerException
     * Strings2.defaultObjectIfEmpty(null, Integer::valueOf, null) -\gt; NullPointerException
     * Strings2.defaultObjectIfEmpty(null, Integer::valueOf, () -\gt; 0) = 0
     * Strings2.defaultObjectIfEmpty("", Integer::valueOf, () -\gt; 0) = 0
     * Strings2.defaultObjectIfEmpty("1", Integer::valueOf, () -\gt; 0) = 1
     * Strings2.defaultObjectIfEmpty("001", JaCode::reconstruct, JaCode::empty) -\gt; JaCode.Of("001")
     * Strings2.defaultObjectIfEmpty(null, JaCode::reconstruct, JaCode::empty) -\gt; JaCode.empty()
     * </pre>
     *
     * @param str              文字列
     * @param nonEmptyFunction 文字列がnot EMPTYの場合のオブジェクト作成メソッド
     * @param emptySupplier    文字列がEMPTYの場合のオブジェクト作成メソッド
     * @param <T>              オブジェクトの型
     * @return オブジェクト
     */
    public static <T> T defaultObjectIfEmpty(final String str,
                                             @NotNull Function<String, T> nonEmptyFunction,
                                             @NotNull Supplier<T> emptySupplier) {
        if (nonEmptyFunction == null) {
            throw new IllegalArgumentException(
                    "引数nonEmptyFunctionがnullです。null以外の値を指定してください");
        }
        if (emptySupplier == null) {
            throw new IllegalArgumentException("引数emptySupplierがnullです。null以外の値を指定してください");
        }

        return isNotEmpty(str) ? nonEmptyFunction.apply(str) : emptySupplier.get();

    }

    /**
     * 先頭文字を大文字かした文字列を取得します
     *
     * @param s 文字列
     * @return 先頭文字を大文字かした文字列
     */
    public static String camelUpperCase(@NotNull String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        if (EMPTY.equals(s)) {
            return s;
        }
        if (s.length() == 1) {
            return s.substring(0, 1).toUpperCase();
        }

        return s.substring(0, 1).toUpperCase()
                + s.substring(1);

    }

    /**
     * キャメル記法をスネークケース記法に変換します。
     * <p>
     * 次のように使います．
     * </p>
     *
     * <pre>
     * Strings2.decamelize("UserId")  = "USER_ID"
     * </pre>
     *
     * @param s テキスト
     * @return 結果の文字列
     */
    public static String decamelize(final String s) {
        if (s == null) {
            return null;
        }
        if (s.length() == 1) {
            return s.toUpperCase(Locale.getDefault());
        }
        final StringBuilder buf = new StringBuilder(40);
        int pos = 0;
        for (int i = 1; i < s.length(); ++i) {
            if (Character.isUpperCase(s.charAt(i))) {
                if (buf.length() != 0) {
                    buf.append('_');
                }
                buf.append(s.substring(pos, i).toUpperCase(Locale.getDefault()));
                pos = i;
            }
        }
        if (buf.length() != 0) {
            buf.append('_');
        }
        buf.append(s.substring(pos).toUpperCase(Locale.getDefault()));
        return buf.toString();
    }

    /**
     * スネークケース記法をキャメル記法に変換します。
     * <p>
     * 次のように使います．
     * </p>
     *
     * <pre>
     * Strings2.camelize("USER_ID")  = "UserId"
     * </pre>
     *
     * @param s テキスト
     * @return 結果の文字列
     */
    public static String camelize(String s) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase(Locale.getDefault());
        final String[] array = split(s, "_");
        if (array.length == 1) {
            return capitalize(s);
        }
        final StringBuilder buf = new StringBuilder(40);
        for (String s1 : array) {
            buf.append(capitalize(s1));
        }
        return buf.toString();
    }

    /**
     * 先頭文字が 小文字 の キャメルケース に変換.
     *
     * @param word 変換前の文字列
     * @return 先頭文字が 小文字 の キャメルケース に変換された文字列
     */
    public static String toLowerCamelCase(final String word) {
        final String camel = camelize(word);
        if (camel == null || camel.length() < 2) {
            return camel;
        } else {
            return camel.substring(0, 1).toLowerCase(Locale.getDefault()) + camel.substring(1);
        }
    }

    /**
     * 文字列の最後から指定した文字列で始まっている部分より後ろを返します。
     *
     * @param str       文字列
     * @param separator セパレータ
     * @return 結果の文字列
     */
    public static String substringToLast(final String str, final String separator) {
        if (isEmpty(str) || isEmpty(separator)) {
            return str;
        }
        final int pos = str.lastIndexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(pos + separator.length());
    }

    /**
     * 16進数の文字列に変換します。
     *
     * @param bytes バイトの配列
     * @return 16進数の文字列
     */
    public static String toHex(final byte[] bytes) {
        if (bytes == null) {
            return "";
        }
        final StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte aByte : bytes) {
            appendHex(sb, aByte);
        }
        return new String(sb);
    }

    /**
     * {@literal int}の値を16進数の文字列に変換します。
     *
     * @param i {@literal int}の値
     * @return 16進数の文字列
     */
    public static String toHex(final int i) {
        final StringBuilder buf = new StringBuilder();
        appendHex(buf, i);
        return new String(buf);
    }

    /**
     * 文字列に、数値を16進数に変換した文字列を追加します。
     *
     * @param buf 追加先の文字列
     * @param i   数値
     */
    public static void appendHex(final StringBuilder buf, final byte i) {
        buf.append(Character.forDigit((i & 0xf0) >> 4, 16));
        buf.append(Character.forDigit((i & 0x0f), 16));
    }

    /**
     * 文字列に、数値を16進数に変換した文字列を追加します。
     *
     * @param buf 追加先の文字列
     * @param i   数値
     */
    public static void appendHex(final StringBuilder buf, final int i) {
        buf.append(Integer.toHexString((i >> 24) & 0xff));
        buf.append(Integer.toHexString((i >> 16) & 0xff));
        buf.append(Integer.toHexString((i >> 8) & 0xff));
        buf.append(Integer.toHexString(i & 0xff));
    }

    /**
     * 先頭文字を大文字かした文字列を取得します
     * <pre>
     *     文字列が空の場合、文字列を返します。
     * </pre>
     * <pre>
     *       assertThat(camelUpperCaseSilently("")).isEmpty();
     *       assertThat(camelUpperCaseSilently(null)).isNull();
     *       assertThat(camelUpperCaseSilently("a")).isEqualTo("A");
     *       assertThat(camelUpperCaseSilently("A")).isEqualTo("A");
     *       assertThat(camelUpperCaseSilently("abc")).isEqualTo("Abc");
     *       assertThat(camelUpperCaseSilently("Abc")).isEqualTo("Abc");
     * </pre>
     *
     * @param s 文字列
     * @return 先頭文字を大文字かした文字列
     */
    public static String camelUpperCaseSilently(String s) {
        if (isEmpty(s)) {
            return s;
        }
        return camelUpperCase(s);

    }

    /**
     * 文字列の長さが、長さになるまで付与キャラクターを、文字列の末尾に付与して文字列を返却します
     *
     * @param string    文字列
     * @param minLength 長さ
     * @param padChar   付与キャラクター
     * @return パディングをした結果
     */
    public static String padEnd(String string, int minLength, char padChar) {
        if (isNull(string)) {
            throw new IllegalArgumentException("引数stringがnullです。null以外の値を指定してください");
        }

        if (string.length() >= minLength) {
            return string;
        }
        return string +
                String.valueOf(padChar).repeat(minLength - string.length());

    }

    public static String escapeHtml(String string) {
        String result = string;
        result = result.replace("&", "&amp;");
        result = result.replace(">", "&gt;");
        result = result.replace("<", "&lt;");
        result = result.replace("\"", "&quot;");
        result = result.replace("'", "&#x27;");
        result = result.replace("`", "&#x60;");
        return result;
    }

    /**
     * 全ての文字が数字かを返却する
     * <pre>
     *    assertThat(Strings2.isDigit(null)).isFalse();
     *    assertThat(Strings2.isDigit("")).isFalse();
     *    assertThat(Strings2.isDigit(" ")).isFalse();
     *    assertThat(Strings2.isDigit("123")).isTrue();
     *    assertThat(Strings2.isDigit("\u0967\u0968\u0969")).isTrue();
     *    assertThat(Strings2.isDigit("12 3")).isFalse();
     *    assertThat(Strings2.isDigit("ab2c")).isFalse();
     *    assertThat(Strings2.isDigit("12-3")).isFalse();
     *    assertThat(Strings2.isDigit("12.3")).isFalse();
     *    assertThat(Strings2.isDigit("-123")).isFalse();
     *    assertThat(Strings2.isDigit("+123")).isFalse();
     * </pre>
     *
     * @param str 文字列
     * @return 全ての文字が数字の場合、trueを返却する
     */
    public static boolean isDigit(String str) {
        return CharSequences.isDigit(str);

    }

    /**
     * 文字列を拡張書式でフォーマットする
     * <pre>
     *   assertThat(Strings2.formatEx("", "")).isEqualTo("");
     *   assertThat(Strings2.formatEx("", "X")).isEqualTo("");
     *   assertThat(Strings2.formatEx(null, "")).isNull();
     *   assertThat(Strings2.formatEx("1", "X")).isEqualTo("1");
     *   assertThat(Strings2.formatEx("1", "XX")).isEqualTo("1");
     *   assertThat(Strings2.formatEx("123", "XXX")).isEqualTo("123");
     *   assertThat(Strings2.formatEx("123", "XXX-")).isEqualTo("123");
     *   assertThat(Strings2.formatEx("1234", "XXX-")).isEqualTo("123-");
     *   assertThat(Strings2.formatEx("", "XXX-X")).isEqualTo("");
     *   assertThat(Strings2.formatEx(null, "XXX-X")).isNull();
     *   assertThat(Strings2.formatEx("1234", "XXX-X")).isEqualTo("123-4");
     *   assertThat(Strings2.formatEx("12345", "XXX-XX")).isEqualTo("123-45");
     *   assertThat(Strings2.formatEx("1234567", "XXX-XXXX")).isEqualTo("123-4567");
     *   assertThat(Strings2.formatEx("12345678", "XXX-XXXX")).isEqualTo("123-4567");
     *   assertThat(Strings2.formatEx("12345678", "XXX--XXXX")).isEqualTo("123--4567");
     * </pre>
     *
     * @param string       文字列
     * @param formatString 拡張書式
     * @return 変換後文字列
     */
    public static String formatEx(String string, String formatString) {
        if (isNull(formatString)) {
            throw new IllegalArgumentException("引数formatStringがnullです。null以外の値を指定してください");
        }
        if (isNull(string)) {
            return null;
        }
        if (isEmpty(string)) {
            return EMPTY;
        }
        int stringLength = string.length();
        int formatStringLength = formatString.length();
        int formatCharIndex = 0;
        StringBuilder result = new StringBuilder();

        for (int stringIndex = 0; stringIndex < stringLength; stringIndex++) {
            char sourceChar = string.charAt(stringIndex);
            if (formatCharIndex >= formatStringLength) {
                break;
            }
            char formatChar = formatString.charAt(formatCharIndex);
            if (formatChar == 'X') {
                result.append(sourceChar);
                formatCharIndex++;
            } else {
                result.append(formatChar);
                if (formatCharIndex < formatStringLength) {
                    formatCharIndex++;
                    stringIndex--;
                } else {
                    break;
                }
            }


        }

        return result.toString();

    }

    /**
     * 文字列の長さが、長さになるまで付与キャラクターを、文字列の先頭に付与して文字列を返却します.
     *
     * <pre>
     *         assertThatThrownBy(() -&gt; {
     *         padStart(null, 3, 'a');
     *       }).as("第一引数でnullを設定した場合Npeをスローする")
     *           .isInstanceOf(NullPointerException.class);
     *       assertThat(padStart("", 3, 'a')).isEqualTo("aaa");
     *       assertThat(padStart("123", 3, 'a')).isEqualTo("123");
     *       assertThat(padStart("12", 3, 'a')).isEqualTo("a12");
     *       assertThat(padStart("1", 3, 'a')).isEqualTo("aa1");
     *       assertThat(padStart("1234", 3, 'a'))
     *           .as("第一引数が最小桁数よりも長い場合第一引数そのままが返される")
     *           .isEqualTo("1234");
     * </pre>
     *
     * @param string    文字列
     * @param minLength 長さ
     * @param padChar   付与キャラクター
     * @return パディングをした結果
     */
    public static String padStart(@NotNull String string, int minLength, char padChar) {
        if (isNull(string)) {
            throw new IllegalArgumentException("引数stringがnullです。null以外の値を指定してください");
        }
        if (string.length() >= minLength) {
            return string;
        }
        return String.valueOf(padChar).repeat(minLength - string.length()) +
                string;
    }

    /**
     * 文字列の全角変換可能なものは全角に変換する
     * <h3> 呼び出しサンプル </h3>
     * <code><pre>
     * //入力文字列を全角変換する。
     * String inMojiretsuString = &quot;&quot;;
     * String result            = &quot;&quot;;
     *
     * try{
     *   result = Strings2.changeHankakuToZenkaku(inMojiretsuString);
     * }chatch(GunmaRuntimeException e){
     *   // ex) errorMsgLabel.setText(&quot;文字列の変換に失敗しました。&quot; + e);
     * }
     * </pre></code>
     *
     * @param aString 変換対象文字列
     * @return 全角変換後文字列:String
     */
    public static String changeHankakuToZenkaku(String aString) {
        // ｳﾞ対応した文字列を変換対象にセット
        StringBuffer stringBuffer =
                hankakuKatakanaTokubetsuHenkan(aString);
        String result = stringBuffer.toString();

        // 半角数字を全角変換
        result = hankakuNumberToZenkakuNumber(result);

        // 半角英字を全角変換
        result = hankakuAlphabetToZenkakuAlphabet(result);

        // 半角カナを全角変換
        result = hankakuKatakanaToZenkakuKatakana(result);
        // 半角記号を全角変換
        result = changeHankakuToZenkakuKigou(result);

        return result;

    }

    /**
     * パラメータにより渡された文字列の半角変換可能なものは半角に変換する
     * <h3> 呼び出しサンプル </h3>
     * <code><pre>
     *                 //入力文字列を半角変換する。
     *                     String inMojiretsuString = &quot;&quot;;
     *                     String result            = &quot;&quot;;
     *
     *                 try{
     *                     result = Strings2.changeZenkakuToHankaku(inMojiretsuString);
     *                 }chatch(GunmaRuntimeException e){
     *                     //画面 errorMsgLabel にエラーメッセージを表示
     *                     errorMsgLabel.setText(&quot;文字列の変換に失敗しました。&quot; + e);
     *                 }
     *
     *
     * </pre></code>
     *
     * @param targetString 変換対象文字列
     * @return 半角変換後文字列:String
     */
    public static String changeZenkakuToHankaku(String targetString) {
        // 特別対応した文字列を変換対象にセット
        StringBuffer stringBuffer =
                zenkakuToHankakuTokubetsuHenkan(targetString);
        String result = stringBuffer.toString();

        // 全角数字を半角変換
        result = zenkakuNumToHankaku(result);

        // 全角英字を半角変換
        result = zenkakuAlphabetToHankaku(result);

        // 全角カナを半角変換
        result = zenkakuKatakanaToHankakuKatakana(result);
        // 全角記号を半角変換
        result = zenkakuKatakanaToHankakuKigou(result);

        return result;

    }

    /**
     * 全角文字を半角文字に変換（テーブル対応できないもの）
     *
     * @param targetString 対象文字列
     * @return 変換後文字列
     */
    private static StringBuffer zenkakuToHankakuTokubetsuHenkan(String targetString) {

        StringBuffer stringBuffer = new StringBuffer(targetString);

        for (int i = 0; i < stringBuffer.length() - 1; i++) {
            char henkan = stringBuffer.charAt(i);
            switch (henkan) {
                case 'ー':
                    henkan = 'ｰ';
                    break;
                case '゛':
                    henkan = 'ﾞ';
                    break;
                default:
                    break;
            }
            stringBuffer.setCharAt(i, henkan);
        }
        return stringBuffer;
    }

    /**
     * 半角カナ ｳﾞ を全角カナ ヴ に変換
     *
     * @param targetString 変換対象文字列
     * @return 変換後文字列
     */
    private static StringBuffer hankakuKatakanaTokubetsuHenkan(String targetString) {

        StringBuffer stringBuffer = new StringBuffer(targetString);

        int index;
        // ｳﾞをヴに変換（テーブル対応できないため）
        for (int i = 0; i < stringBuffer.length() - 1; i++) {
            char henkan = stringBuffer.charAt(i);
            if (henkan == 'ｳ') {
                i++;
                char dakuten = stringBuffer.charAt(i);
                if (dakuten == 'ﾞ') {
                    henkan = 'ヴ';
                    stringBuffer.setCharAt(i, henkan);

                    // 'ﾞ'をヴに変換したので直前の'ｳ'は削除
                    index = i;
                    stringBuffer.delete(index - 1, index);
                } else {
                    // 2文字目が濁点か見るために次のindexになってるので一つ戻る
                    i = i - 1;
                }
            }
        }
        return stringBuffer;
    }

    /**
     * 全角カナ文字を全角かな文字に変換します。
     *
     * @param targetString 変換元文字列
     * @return 変換後文字列
     */
    private StringBuffer zenkakuKatakanaToHiragana(String targetString) {

        StringBuffer stringBuffer = new StringBuffer(targetString);

        for (int i = 0; i < stringBuffer.length(); i++) {
            char henkan = stringBuffer.charAt(i);
            if (henkan >= 'ァ' && henkan <= 'ン') {
                stringBuffer.setCharAt(i, (char) (henkan - 'ァ' + 'ぁ'));
            } else if (henkan == 'ヵ') {
                stringBuffer.setCharAt(i, 'か');
            } else if (henkan == 'ヶ') {
                stringBuffer.setCharAt(i, 'け');
            } else if (henkan == 'ヴ') {
                stringBuffer.setCharAt(i, 'う');
                stringBuffer.insert(i + 1, '゛');
                i++;
            }
        }
        return stringBuffer;
    }

    /**
     * かな文字を全角カナ文字に変換します。
     *
     * @param targetString 変換元文字列
     * @return 変換後文字列
     */
    private StringBuffer hiraganaToKatakana(String targetString) {

        StringBuffer stringBuffer = new StringBuffer(targetString);

        for (int i = 0; i < stringBuffer.length(); i++) {
            char henkan = stringBuffer.charAt(i);
            if (henkan >= 'ぁ' && henkan <= 'ん') {
                stringBuffer.setCharAt(i, (char) (henkan - 'ぁ' + 'ァ'));
            }
        }
        return stringBuffer;
    }

    /**
     * 全角数字を半角に変換します。
     *
     * @param targetString 変換元文字列
     * @return 変換後文字列
     */
    private static String zenkakuNumToHankaku(String targetString) {

        StringBuilder stringBuffer = new StringBuilder(targetString);

        for (int i = 0; i < stringBuffer.length(); i++) {
            char henkan = stringBuffer.charAt(i);
            if (henkan >= '０' && henkan <= '９') {
                stringBuffer.setCharAt(i, (char) (henkan - '０' + '0'));
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 半角数字を全角に変換します。
     *
     * @param targetString 変換元文字列
     * @return 変換後文字列
     */
    private static String hankakuNumberToZenkakuNumber(String targetString) {

        StringBuilder stringBuffer = new StringBuilder(targetString);

        for (int i = 0; i < targetString.length(); i++) {
            char henkan = targetString.charAt(i);
            if (henkan >= '0' && henkan <= '9') {
                stringBuffer.setCharAt(i, (char) (henkan - '0' + '０'));
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 全角アルファベットを半角に変換します。
     *
     * @param targetString 変換元文字列
     * @return 変換後文字列
     */
    private static String zenkakuAlphabetToHankaku(String targetString) {

        StringBuilder stringBuffer = new StringBuilder(targetString);

        for (int i = 0; i < stringBuffer.length(); i++) {
            char henkan = stringBuffer.charAt(i);
            if (henkan >= 'ａ' && henkan <= 'ｚ') {
                stringBuffer.setCharAt(i, (char) (henkan - 'ａ' + 'a'));
            } else if (henkan >= 'Ａ' && henkan <= 'Ｚ') {
                stringBuffer.setCharAt(i, (char) (henkan - 'Ａ' + 'A'));
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 半角アルファベットを全角に変換します。
     *
     * @param targetString 変換元文字列
     * @return 変換後文字列
     */
    private static String hankakuAlphabetToZenkakuAlphabet(String targetString) {

        StringBuilder stringBuffer = new StringBuilder(targetString);

        for (int i = 0; i < targetString.length(); i++) {
            char henakn = targetString.charAt(i);
            if (henakn >= 'a' && henakn <= 'z') {
                stringBuffer.setCharAt(i, (char) (henakn - 'a' + 'ａ'));
            } else if (henakn >= 'A' && henakn <= 'Z') {
                stringBuffer.setCharAt(i, (char) (henakn - 'A' + 'Ａ'));
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 入力文字列の全角カタカナを半角カタカナに変換する
     *
     * @param string 入力文字列
     * @return 変換後文字列
     */
    static String zenkakuKatakanaToHankakuKigou(String string) {
        if (string == null) {
            return null;
        }
        String result = string;

        for (String[] strings : hanZenKigouTaiouArray) {
            String fromText = strings[1];
            String toText = strings[0];

            result =
                    replace(result,
                            fromText, toText);
        }

        return result;
    }

    /**
     * 全角カタカナから半角カタカナへ変換します。
     *
     * @param targetString 変換前の文字
     * @return 変換後の文字
     */
    private static String zenkakuKatakanaToHankakuKatakana(String targetString) {

        StringBuilder stringBuffer = new StringBuilder();

        for (int i = 0; i < targetString.length(); i++) {
            char originalChar = targetString.charAt(i);
            String convertedChar =
                    zenkakuKatakanSaToHankakuKatakana(originalChar);
            stringBuffer.append(convertedChar);
        }
        return stringBuffer.toString();
    }

    /**
     * 全角カタカナから半角カタカナへ変換します。
     *
     * @param targetString 変換前の文字
     * @return 変換後の文字
     */
    private static String zenkakuKatakanSaToHankakuKatakana(char targetString) {

        if (targetString >= ZENKAKU_KATAKANA_FIRST_CHAR
                && targetString <= ZENKAKU_KATAKANA_LAST_CHAR) {
            return HANKAKU_KATAKANA[targetString
                    - ZENKAKU_KATAKANA_FIRST_CHAR];
        } else {
            return String.valueOf(targetString);
        }
    }

    /**
     * 文字列中の半角カタカナを全角カタカナに変換します。
     *
     * @param targetString 変換前文字列
     * @return 変換後文字列
     */
    private static String hankakuKatakanaToZenkakuKatakana(String targetString) {

        if (targetString.length() == 0) {
            return targetString;
        } else if (targetString.length() == 1) {
            return hankakuKatakanaToZenkakuKatakana(targetString.charAt(0))
                    + "";
        } else {
            StringBuilder stringBuffer = new StringBuilder(targetString);
            int i;
            for (i = 0; i < stringBuffer.length() - 1; i++) {
                char originalChar1 = stringBuffer.charAt(i);
                char originalChar2 = stringBuffer.charAt(i + 1);
                char margedChar = mergeChar(originalChar1, originalChar2);
                if (margedChar != originalChar1) {
                    stringBuffer.setCharAt(i, margedChar);
                    stringBuffer.deleteCharAt(i + 1);
                } else {
                    char convertedChar =
                            hankakuKatakanaToZenkakuKatakana(originalChar1);
                    if (convertedChar != originalChar1) {
                        stringBuffer.setCharAt(i, convertedChar);
                    }
                }
            }
            if (i < stringBuffer.length()) {
                char originalChar1 = stringBuffer.charAt(i);
                char convertedChar =
                        hankakuKatakanaToZenkakuKatakana(originalChar1);
                if (convertedChar != originalChar1) {
                    stringBuffer.setCharAt(i, convertedChar);
                }
            }
            return stringBuffer.toString();
        }
    }

    /**
     * 2文字目が濁点・半濁点で、1文字目に加えることができる場合は、合成した文字を返します。
     * <pre>
     * 合成ができないときは、moji1を返します。
     * </pre>
     *
     * @param _moji1Char 変換前の1文字目
     * @param _moji2Char 変換前の2文字目
     * @return 変換後の文字
     */
    private static char mergeChar(char _moji1Char, char _moji2Char) {

        if (_moji2Char == 'ﾞ') {
            if ("ｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾊﾋﾌﾍﾎ".indexOf(_moji1Char) > 0) {
                return switch (_moji1Char) {
                    case 'ｶ' -> 'ガ';
                    case 'ｷ' -> 'ギ';
                    case 'ｸ' -> 'グ';
                    case 'ｹ' -> 'ゲ';
                    case 'ｺ' -> 'ゴ';
                    case 'ｻ' -> 'ザ';
                    case 'ｼ' -> 'ジ';
                    case 'ｽ' -> 'ズ';
                    case 'ｾ' -> 'ゼ';
                    case 'ｿ' -> 'ゾ';
                    case 'ﾀ' -> 'ダ';
                    case 'ﾁ' -> 'ヂ';
                    case 'ﾂ' -> 'ヅ';
                    case 'ﾃ' -> 'デ';
                    case 'ﾄ' -> 'ド';
                    case 'ﾊ' -> 'バ';
                    case 'ﾋ' -> 'ビ';
                    case 'ﾌ' -> 'ブ';
                    case 'ﾍ' -> 'ベ';
                    case 'ﾎ' -> 'ボ';
                    default -> _moji1Char;
                };
            }
        } else if (_moji2Char == 'ﾟ') {
            if ("ﾊﾋﾌﾍﾎ".indexOf(_moji1Char) > 0) {
                return switch (_moji1Char) {
                    case 'ﾊ' -> 'パ';
                    case 'ﾋ' -> 'ピ';
                    case 'ﾌ' -> 'プ';
                    case 'ﾍ' -> 'ペ';
                    case 'ﾎ' -> 'ポ';
                    default -> _moji1Char;
                };
            }
        }
        return _moji1Char;
    }

    /**
     * 引数の妥当性を確認する 第一引数のlengthより第二引数が同じかそれ以上であるか
     *
     * @param aString   文字列:String
     * @param _hanniInt 範囲:int
     * @return 引数が妥当の場合はtrue。そうでない場合はfalse。
     */
    private boolean isHanniValid(String aString, int _hanniInt) {
        if (isNull(aString)) {
            return false;
        }

        return _hanniInt >= aString.length();

    }

    /**
     * 半角カタカナから全角カタカナへ変換します。
     *
     * @param targetString 変換前の文字
     * @return 変換後の文字
     */
    private static char hankakuKatakanaToZenkakuKatakana(char targetString) {

        if (targetString >= HANKAKU_KATAKANA_FIRST_CHAR
                && targetString <= HANKAKU_KATAKANA_LAST_CHAR) {
            return ZENKAKU_KATAKANA_KIGOU[targetString
                    - HANKAKU_KATAKANA_FIRST_CHAR];
        } else {
            return targetString;
        }
    }

    /**
     * 半角記号を全角記号に変換する
     *
     * @param string 変換前文字列
     * @return 変換後文字列
     */
    static String changeHankakuToZenkakuKigou(String string) {
        if (string == null) {
            return null;
        }
        String resultString = string;

        for (String[] strings : hanZenKigouTaiouArray) {
            String fromText = strings[0];
            String toText = strings[1];

            resultString =
                    replace(resultString,
                            fromText, toText);
        }

        return resultString;
    }

    /**
     * 入力文字列を大文字化する.
     *
     * @param aString 入力文字列
     * @return 入力文字列を大文字化した文字列
     */
    public static String toUpperCase(String aString) {
        if (isNull(aString)) {
            return null;
        }
        return aString.toUpperCase(Locale.JAPANESE);

    }

    /**
     * 入力文字列を小文字化する.
     *
     * @param aString 入力文字列
     * @return 入力文字列を小文字化した文字列
     */
    public static String toLowerCase(String aString) {
        if (isNull(aString)) {
            return null;
        }
        return aString.toLowerCase(Locale.JAPANESE);

    }

    public static int shiftJisCharLength(@NotNull String string) {
        if (isNull(string)) {
            throw new IllegalArgumentException("引数stringがnullです。null以外の値を指定してください");
        }
        byte[] bytes = string.getBytes(Charset.forName("MS932"));

        return bytes.length;

    }


    /**
     * 文字列の全ての文字が半角文字かを判定する
     * <pre>
     *       assertThat(Strings2.isHankakuAllCharacter(null)).isTrue();
     *       assertThat(Strings2.isHankakuAllCharacter("")).isTrue();
     *       assertThat(Strings2.isHankakuAllCharacter("　")).isFalse();
     *       assertThat(Strings2.isHankakuAllCharacter("ABC")).isTrue();
     *       assertThat(Strings2.isHankakuAllCharacter("ＡＢＣ")).isFalse();
     *       assertThat(Strings2.isHankakuAllCharacter("ＡＢＣABC")).isFalse();
     * </pre>
     * <pre>
     *   文字列が<code>null</code>あるいは空文字列の場合はtrueを返却します。
     * </pre>
     *
     * @param aString 文字列
     * @return 文字列の全ての文字が半角文字の場合trueを返却する
     */
    public static boolean isHankakuAllCharacters(String aString) {
        if (isNull(aString)) {
            return true;
        }

        if (isEmpty(aString)) {
            return true;
        }

        char[] chars = aString.toCharArray();
        for (char c : chars) {
            if ((c > '\u007e') && // 英数字
                    (c != '\u00a5') && // \記号07
                    (c != '\u203e') && // ~記号
                    (c < '\uff61' || c > '\uff9f') // 半角カナ
            ) {
                return false;

            }
        }
        return true;

    }

    /**
     * 文字列の全ての文字が全角文字かを判定する
     * <pre>
     *       assertThat(Strings2.isZenkakuAllCharacter(null)).isTrue();
     *       assertThat(Strings2.isZenkakuAllCharacter("")).isTrue();
     *       assertThat(Strings2.isZenkakuAllCharacter("　")).isTrue();
     *       assertThat(Strings2.isZenkakuAllCharacter("ABC")).isFalse();
     *       assertThat(Strings2.isZenkakuAllCharacter("ＡＢＣ")).isTrue();
     *       assertThat(Strings2.isZenkakuAllCharacter("ＡＢＣABC")).isFalse();
     * </pre>
     * <pre>
     *   文字列が<code>null</code>あるいは空文字列の場合はtrueを返却します。
     * </pre>
     *
     * @param aString 文字列
     * @return 文字列の全ての文字が全角文字の場合trueを返却する
     */
    public static boolean isZenkakuAllCharacters(String aString) {
        if (isNull(aString)) {
            return true;
        }

        if (isEmpty(aString)) {
            return true;
        }

        char[] chars = aString.toCharArray();
        for (char c : chars) {
            if ((c <= '\u007e') || // 英数字
                    (c == '\u00a5') || // \記号07
                    (c == '\u203e') || // ~記号
                    (c >= '\uff61' && c <= '\uff9f') // 半角カナ
            ) {
                return false;
            }
        }
        return true;

    }

    /**
     * 文字列(String)の複数要素を結合する
     *
     * @param strings 複数要素の文字列
     * @return 結合後の文字列
     */
    public static String concat(@NotNull String... strings) {
        checkNotNull(strings, "strings");
        checkAllEntityNotNull(strings, "strings");

        StringBuilder sb = new StringBuilder();
        for (String charSequence : strings) {
            sb.append(charSequence);
        }

        return sb.toString();
    }

    static void checkNotNull(Object reference, String argName) {
        if (null == reference) {
            throw new IllegalArgumentException("引数:" + argName + " はnullは許されません");
        }

    }

    static void checkAllEntityNotNull(Object[] references, String argName) {
        for (int index = 0; index < references.length; index++) {
            if (null == references[index]) {
                throw new IllegalArgumentException(
                        "引数:" + argName + " の要素にnullは許されません　index:" + index);
            }

        }


    }

    /**
     * 文字列からブランク業を省略します。
     *
     * @param aString 文字列
     * @return 省略後の文字列
     */
    public static String omitBlankLine(String aString) {
        if (isNull(aString)) {
            return null;
        }
        if (isBlank(aString)) {
            return "";
        }

        String[] lines = aString.split("\r\n|\r|\n");
        ;
        List<String> linesList = new ArrayList<>();
        for (String line : lines) {
            if (Strings2.isNotBlank(line)) {
                linesList.add(line);

            }

        }
        return join(System.lineSeparator(), linesList.toArray(new String[0]));

    }

    /**
     * 文字列に空白以外の文字が含まれているかを判定します
     *
     * @param aString 文字列
     * @return 文字列に空白以外の文字が含まれているとtrueを返却します
     */
    public static boolean hasText(String aString) {
        return aString != null && !aString.isEmpty() && containsText(aString);
    }

    private static boolean containsText(CharSequence str) {
        int strLen = str.length();

        for (int i = 0; i < strLen; ++i) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    public static String stringValueOf(int value) {
        return String.valueOf(value);

    }

    /**
     * 文字列を可変変数で渡し、最初の非空文字列を返却する
     * <pre>
     *     すべての要素が空文字列またはnullの場合はnullを返却する
     * </pre>
     *
     * @param strings 文字列 可変変数
     * @return 最初の非空文字列
     */
    public static String findFirstByNotEmpty(@NotNull String... strings) {
        if (null == strings) {
            throw new IllegalArgumentException("引数stringsがnullですが、ここではnullを指定できません。");
        }
        for (String string : strings) {
            if (isNotEmpty(string)) {
                return string;

            }

        }
        return null;

    }

    /**
     * 文字列を可変変数で渡し、最初の非空文字列を返却する
     * <pre>
     *     すべての要素が空文字列またはnullの場合はnullを返却する
     *     可変要素がnullの場合はnullを返却する
     * </pre>
     *
     * @param strings 文字列 可変変数
     * @return 最初の非空文字列
     */
    public static String findFirstByNotEmptySilently(String... strings) {
        if (null == strings) {
            return null;

        }
        return findFirstByNotEmpty(strings);

    }

}
