package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjByteToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjByteToIntFunction<String, String> function =
                BiObjByteToIntFunction.of((t, u, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjByteToIntFunction<String, String> function = BiObjByteToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
