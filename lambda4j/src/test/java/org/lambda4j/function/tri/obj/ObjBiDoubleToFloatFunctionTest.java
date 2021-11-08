package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiDoubleToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiDoubleToFloatFunction<String> function =
                ObjBiDoubleToFloatFunction.of((t, value1, value2) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiDoubleToFloatFunction<String> function = ObjBiDoubleToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
