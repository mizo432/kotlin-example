package undecided.sale.product.model.municipal;

import lombok.Getter;

import java.util.Objects;

@Getter
public class MunicipalId {

    /**
     * Prefecture code.
     * -- GETTER --
     * Get the prefecture code.
     */
    private final String prefCode;

    /**
     * Municipality code.
     * -- GETTER --
     * Get the municipality code.
     */
    private final String municipalCode;


    private MunicipalId(String prefCode, String municipalCode) {
        Objects.requireNonNull(prefCode);
        Objects.requireNonNull(municipalCode);
        if (prefCode.length() != 2) {
            throw new IllegalArgumentException("Illegal length of prefecture code");
        }
        if (municipalCode.length() != 3) {
            throw new IllegalArgumentException("Illegal length of municipality code");
        }
        this.prefCode = prefCode;
        this.municipalCode = municipalCode;
    }


    /**
     * Create the MunicipalId.
     *
     * @param prefCode      the prefecture code
     * @param municipalCode the municipality code
     * @return MunicipalId
     */
    public static undecided.sale.cash.model.municipal.MunicipalId of(String prefCode, String municipalCode) {
        return new undecided.sale.cash.model.municipal.MunicipalId(prefCode, municipalCode);
    }


    /**
     * Create the MunicipalId.
     *
     * @param code prefecture code + municipality code + [check digit]
     * @return MunicipalId
     */
    public static undecided.sale.cash.model.municipal.MunicipalId of(String code) {
        Objects.requireNonNull(code);
        if (code.length() == 5) {
            return new undecided.sale.cash.model.municipal.MunicipalId(code.substring(0, 2), code.substring(2));
        } else if (code.length() == 6) {
            undecided.sale.cash.model.municipal.MunicipalId id = new undecided.sale.cash.model.municipal.MunicipalId(code.substring(0, 2), code.substring(2, 5));
            if (id.getCheckDigit() != Integer.parseInt(code.substring(5))) {
                throw new IllegalArgumentException("Illegal check digit");
            }
            return id;
        }
        throw new IllegalArgumentException("Illegal format");
    }


    /**
     * Get the check digit.
     * <p>
     * 第1桁から第5桁までの数字に、それぞれ6.5.4.3.2を乗じて算出した積の和を求め、
     * その和を11で除し、商と剰余（以下「余り数字」という。）を求めて、
     * 11と余り数字との差の下1桁の数字を検査数字とする。
     * ただし、積の和が11より小なるときは、検査数字は、11から積の和を控除した数字とする。
     * <a href="https://www.soumu.go.jp/main_content/000137948.pdf">...</a>
     * </p>
     *
     * @return the check digit
     */
    public int getCheckDigit() {
        int a = Integer.parseInt(prefCode.substring(0, 1));
        int b = Integer.parseInt(prefCode.substring(1, 2));
        int c = Integer.parseInt(municipalCode.substring(0, 1));
        int d = Integer.parseInt(municipalCode.substring(1, 2));
        int e = Integer.parseInt(municipalCode.substring(2, 3));
        int sp = (a * 6) + (b * 5) + (c * 4) + (d * 3) + (e * 2);
        return (11 - (sp % 11)) % 10;
    }


    /**
     * Get the code with check digit.
     *
     * @return the code with check digit
     */
    public String getCode() {
        return prefCode + municipalCode + getCheckDigit();
    }


    @Override
    public String toString() {
        return "MunicipalId{" +
                "prefCode='" + prefCode + '\'' +
                ", municipalCode='" + municipalCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        undecided.sale.cash.model.municipal.MunicipalId that = (undecided.sale.cash.model.municipal.MunicipalId) o;
        return prefCode.equals(that.prefCode) &&
                municipalCode.equals(that.municipalCode);
    }


    @Override
    public int hashCode() {
        return Objects.hash(prefCode, municipalCode);
    }
}
