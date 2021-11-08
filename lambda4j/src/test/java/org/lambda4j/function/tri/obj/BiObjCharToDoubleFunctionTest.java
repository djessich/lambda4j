package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjCharToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjCharToDoubleFunction<String, String> function =
                BiObjCharToDoubleFunction.of((t, u, value) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjCharToDoubleFunction<String, String> function = BiObjCharToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
