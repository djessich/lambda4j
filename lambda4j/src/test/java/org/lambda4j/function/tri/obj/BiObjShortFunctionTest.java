package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjShortFunction<String, String, String> function = BiObjShortFunction.of((t, u, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjShortFunction<String, String, String> function = BiObjShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
