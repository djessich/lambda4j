package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjIntToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjIntToDoubleFunction<String, String> function =
                BiObjIntToDoubleFunction.of((t, u, value) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjIntToDoubleFunction<String, String> function = BiObjIntToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
