package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjByteToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjByteToLongFunction<String, String> function =
                BiObjByteToLongFunction.of((t, u, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjByteToLongFunction<String, String> function = BiObjByteToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
