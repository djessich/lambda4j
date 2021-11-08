package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiCharToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiCharToDoubleFunction<String> function =
                ObjBiCharToDoubleFunction.of((t, value1, value2) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiCharToDoubleFunction<String> function = ObjBiCharToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
