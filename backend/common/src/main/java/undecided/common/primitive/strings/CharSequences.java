package undecided.common.primitive.strings;

public class CharSequences {

    public static final char CR = '\r';
    public static final char LR = '\n';
    private static final int NOT_FOUND = -1;

    public CharSequences() {

    }

    /**
     * 文字列が空文字列またはnullかを判定する
     *
     * @param cs 文字列
     * @return 文字列が空文字列またはnullの場合trueを返却する
     */
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.isEmpty();
    }

    /**
     * このシーケンスのサブシーケンスであるCharSequenceを返します。
     * <pre>
     * サブシーケンスは、指定されたインデックスのchar値で始まり、インデックスend - 1のchar値で終わります。
     * 返される(charの)シーケンスの長さはend - startです。このため、start == endの場合、空のシーケンスが返されます。
     * </pre>
     *
     * @param cs    キャラクターシーケンス
     * @param start 開始インデックス(この値を含む)
     * @return 指定されたサブシーケンス
     */
    public static CharSequence subSequence(CharSequence cs, int start) {
        return cs == null ? null : cs.subSequence(start, cs.length());
    }

    static int indexOf(CharSequence cs, int searchChar) {
        return indexOf(cs, searchChar, 0);
    }

    static int indexOf(CharSequence cs, int searchChar, int start) {
        if (cs instanceof String) {
            return ((String) cs).indexOf(searchChar, start);
        } else {
            int sz = cs.length();
            if (start < 0) {
                start = 0;
            }

            if (searchChar < 65536) {
                for (int i = start; i < sz; ++i) {
                    if (cs.charAt(i) == searchChar) {
                        return i;
                    }
                }
            }

            if (searchChar <= 1114111) {
                char[] chars = Character.toChars(searchChar);

                for (int i = start; i < sz - 1; ++i) {
                    char high = cs.charAt(i);
                    char low = cs.charAt(i + 1);
                    if (high == chars[0] && low == chars[1]) {
                        return i;
                    }
                }
            }

            return -1;
        }
    }

    static int indexOf(CharSequence cs, CharSequence searchChar) {
        return indexOf(cs, searchChar, 0);

    }

    static int indexOf(CharSequence cs, CharSequence searchChar, int start) {
        return cs.toString().indexOf(searchChar.toString(), start);
    }

    static int lastIndexOf(CharSequence cs, int searchChar, int start) {
        if (cs instanceof String) {
            return ((String) cs).lastIndexOf(searchChar, start);
        } else {
            int sz = cs.length();
            if (start < 0) {
                return -1;
            } else {
                if (start >= sz) {
                    start = sz - 1;
                }

                if (searchChar < 65536) {
                    for (int i = start; i >= 0; --i) {
                        if (cs.charAt(i) == searchChar) {
                            return i;
                        }
                    }
                }

                if (searchChar <= 1114111) {
                    char[] chars = Character.toChars(searchChar);
                    if (start == sz - 1) {
                        return -1;
                    }

                    for (int i = start; i >= 0; --i) {
                        char high = cs.charAt(i);
                        char low = cs.charAt(i + 1);
                        if (chars[0] == high && chars[1] == low) {
                            return i;
                        }
                    }
                }

                return -1;
            }
        }
    }

    static int lastIndexOf(CharSequence cs, CharSequence searchChar, int start) {
        return cs.toString().lastIndexOf(searchChar.toString(), start);
    }

    static char[] toCharArray(CharSequence cs) {
        if (cs instanceof String) {
            return ((String) cs).toCharArray();
        } else {
            int sz = cs.length();
            char[] array = new char[cs.length()];

            for (int i = 0; i < sz; ++i) {
                array[i] = cs.charAt(i);
            }

            return array;
        }
    }

    static boolean regionMatches(CharSequence cs, boolean ignoreCase, int thisStart,
                                 CharSequence substring, int start, int length) {
        if (cs instanceof String && substring instanceof String) {
            return ((String) cs)
                    .regionMatches(ignoreCase, thisStart, (String) substring, start, length);
        } else {
            int index1 = thisStart;
            int index2 = start;
            int tmpLen = length;
            int srcLen = cs.length() - thisStart;
            int otherLen = substring.length() - start;
            if (thisStart >= 0 && start >= 0 && length >= 0) {
                if (srcLen >= length && otherLen >= length) {
                    while (tmpLen-- > 0) {
                        char c1 = cs.charAt(index1++);
                        char c2 = substring.charAt(index2++);
                        if (c1 != c2) {
                            if (!ignoreCase) {
                                return false;
                            }

                            if (Character.toUpperCase(c1) != Character.toUpperCase(c2)
                                    && Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
                                return false;
                            }
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public static boolean isDigit(CharSequence cs) {
        if (CharSequences.isEmpty(cs)) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
