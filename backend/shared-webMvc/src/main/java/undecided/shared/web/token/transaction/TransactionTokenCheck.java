package undecided.shared.web.token.transaction;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * フォームの連続送信を防ぐためのトークン チェックを実行する機能を提供するカスタム アノテーション。
 * <pre>
 * このトークンは、1 つのセッション内でコントローラー内の複数の handlerMethods への呼び出しでトランザクションを定義する機能を提供するため、トランザクション トークンと呼ばれます。
 * メソッドが実行される前に、これらの handlerMethods へのすべてのリクエストが有効なトークンであるかどうかがチェックされます。
 * </pre>
 * <pre>
 *     トークンチェックトランザクションの開始と終了を指定するために、
 *   {@code type= TransactionTokenType.BEGIN}) と {@code type= TransactionTokenType.END}) をそれぞれ使用できます。
 *   {@code type} が指定されていない場合、デフォルトで {@code type} 属性の値が設定され、対応するハンドラ メソッドの呼び出しがトークン チェック トランザクションの <i>inside</i> であることを示します。
 *   (デフォルトでは、{@code type= TransactionTokenType.IN})
 * </pre>
 * <pre>
 * トランザクショントークンの構造は {@code tokenName~tokenKey~tokenValue} です。デフォルトでは、トークンの名前、キー、および値コンポーネントを区切るために使用される区切り文字は {@code ~ (チルダ)} です。
 * </pre>
 * <pre>
 * {@code TransactionTokenCheck} は、メソッド レベルだけでなく、クラス レベルでも適用できます。
 * クラス レベルのアノテーションは、コントローラー レベルで共通の名前空間を定義します。
 * メソッド レベルでは、@TransactionTokenCheck アノテーションの {@code value} 属性を使用して、トランザクションに名前を付けることができます。
 * これは、トークン文字列の {@code tokenName} 部分のクラス レベルの名前空間と一緒です。
 * コントローラーレベルとメソッドレベルで @RequestMapping タグと同じ値を指定することをお勧めします
 * </pre>
 * <pre>
 * デフォルトでは、{@code tokenName} のキーを作成するための再試行回数は 10 です。
 * すべての試行が完了すると、FIFO アルゴリズムに従って古いキーが新しいキーに置き換えられます。
 * このデフォルト値は、applicationContext ファイルの設定によって変更できます。
 * </pre>
 * <pre>
 * デフォルトでは、名前空間が定義されておらず、メソッド レベルの {@code value} 属性も指定されていない場合、
 * 生成されるすべてのトランザクション トークンの {@code tokenName} として「globalToken」が使用されます。
 * </pre>
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionTokenCheck {

    @AliasFor("namespace")
    String value() default "";

    @AliasFor("value")
    String namespace() default "";

    TransactionTokenType type() default TransactionTokenType.IN;

}
