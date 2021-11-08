package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiIntToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiIntToDoubleFunction<String> function =
                ObjBiIntToDoubleFunction.of((t, value1, value2) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiIntToDoubleFunction<String> function = ObjBiIntToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
