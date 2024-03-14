package undecided.common.primitive.array;

import java.util.Arrays;

public class Arrays2 {

    /**
     * 参照配列が空かを判定する
     *
     * @param referenceArray 参照配列
     * @param <T>            配列の型
     * @return 参照配列の要素が空の場合trueを返却する
     * @throws NullPointerException 参照配列がnullの場合にスローされます
     */
    public static <T> boolean isEmpty(T[] referenceArray) {
        if (null == referenceArray) {
            throw new NullPointerException("参照配列がnullです。nullではない配列を設定してください");
        }
        return (referenceArray.length == 0);

    }

    /**
     * 参照配列が空かを判定する
     * <pre>
     *     参照配列がnullの場合は空として扱う
     * </pre>
     *
     * @param referenceArray 参照配列
     * @param <T>            配列の型
     * @return 参照配列の要素が空の場合trueを返却する
     */
    public static <T> boolean isEmptySilently(T[] referenceArray) {
        if (null == referenceArray) {
            return false;
        }
        return isEmpty(referenceArray);

    }

    public static int hash(Object[] args) {
        return Arrays.hashCode(args);

    }

    public static String toString(Object[] args) {
        return Arrays.toString(args);

    }

    public static boolean equal(Object[] args, Object[] args1) {
        return Arrays.equals(args, args1);

    }
}
