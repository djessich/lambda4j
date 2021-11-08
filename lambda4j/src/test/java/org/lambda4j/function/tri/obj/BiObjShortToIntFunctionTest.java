package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjShortToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjShortToIntFunction<String, String> function =
                BiObjShortToIntFunction.of((t, u, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjShortToIntFunction<String, String> function = BiObjShortToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
