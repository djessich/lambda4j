package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiLongToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiLongToDoubleFunction<String> function =
                ObjBiLongToDoubleFunction.of((t, value1, value2) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiLongToDoubleFunction<String> function = ObjBiLongToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
