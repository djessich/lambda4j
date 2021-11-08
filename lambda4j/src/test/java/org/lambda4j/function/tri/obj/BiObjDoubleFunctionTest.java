package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjDoubleFunction<String, String, String> function = BiObjDoubleFunction.of((t, u, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjDoubleFunction<String, String, String> function = BiObjDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
