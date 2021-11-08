package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjIntFunction<String, String, String> function = BiObjIntFunction.of((t, u, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjIntFunction<String, String, String> function = BiObjIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
