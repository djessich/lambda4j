package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiByteToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiByteToDoubleFunction<String> function =
                ObjBiByteToDoubleFunction.of((t, value1, value2) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiByteToDoubleFunction<String> function = ObjBiByteToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
