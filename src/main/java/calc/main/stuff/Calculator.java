package calc.main.stuff;

import java.math.BigDecimal;

public class Calculator {

    public static final int INT_TWODIGIT_SCALE = 2;
    private final ValueValidation validation;

    public Calculator(ValueValidation validation) {
        this.validation = validation;
    }

    public int add(int value1, int value2) {
        return value1 + value2;
    }

    public int subtract(int val1, int val2) {
        return val1 - val2;
    }

    public String add(String value1, String value2) {
        BigDecimal s1 = validateDecimalPlaces(value1);
        BigDecimal s2 = validateDecimalPlaces(value2);

        return s1.add(s2).toString();
    }

    private BigDecimal validateDecimalPlaces(String sval) {
        BigDecimal value = new BigDecimal(sval);
        if (value.scale() > INT_TWODIGIT_SCALE) {
            value = null;
            throw new IllegalArgumentException("Value has to many digits behind .dot");
        }
        return value;
    }

    public String multiply(String s, String s1) {
        BigDecimal m1 = new BigDecimal(s);
        BigDecimal m2 = new BigDecimal(s1);

        return m1.multiply(m2).toString();
    }


    public Boolean isValidScale(BigDecimal value) {

        return value != null && value.scale() == INT_TWODIGIT_SCALE; // Boolean.FALSE;
    }

    public Boolean isValidScale(String value) {
        BigDecimal nbValue = new BigDecimal(value);
        Boolean validScale = isValidScale(nbValue);

        return validScale;
    }

    public String divide(String s, String s1) {
        BigDecimal v1 = new BigDecimal(s);
        BigDecimal v2 = new BigDecimal(s1);
        return v1.divide(v2).toString();
    }
}
