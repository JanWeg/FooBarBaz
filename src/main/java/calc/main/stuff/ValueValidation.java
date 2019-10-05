package calc.main.stuff;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ValueValidation {
    public List<String> getErrors(String ... values) {
        ArrayList<String> errors = new ArrayList<String>();
        for ( String value : values) {
            BigDecimal bigDecimalVal;
            try {
                bigDecimalVal = new BigDecimal(value);
            } catch (NumberFormatException nfe) {
                errors.add(String.format("The value %s passed in was not a valid number.", value));
                continue;
            }
            if (bigDecimalVal.scale() > 2 ) {
                errors.add(String.format("The value %s has to many places after the dot.", value));
            }
        }
        return errors;
    }
}
