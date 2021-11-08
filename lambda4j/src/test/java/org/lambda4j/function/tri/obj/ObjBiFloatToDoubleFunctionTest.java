package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiFloatToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiFloatToDoubleFunction<String> function =
                ObjBiFloatToDoubleFunction.of((t, value1, value2) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiFloatToDoubleFunction<String> function = ObjBiFloatToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
