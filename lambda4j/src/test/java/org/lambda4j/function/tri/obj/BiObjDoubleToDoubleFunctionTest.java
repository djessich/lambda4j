package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjDoubleToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjDoubleToDoubleFunction<String, String> function =
                BiObjDoubleToDoubleFunction.of((t, u, value) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjDoubleToDoubleFunction<String, String> function = BiObjDoubleToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
