package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjLongToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjLongToDoubleFunction<String, String> function =
                BiObjLongToDoubleFunction.of((t, u, value) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjLongToDoubleFunction<String, String> function = BiObjLongToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
