package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjByteFunction<String, String, String> function = BiObjByteFunction.of((t, u, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjByteFunction<String, String, String> function = BiObjByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
