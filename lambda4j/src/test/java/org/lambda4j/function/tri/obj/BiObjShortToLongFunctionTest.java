package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjShortToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjShortToLongFunction<String, String> function =
                BiObjShortToLongFunction.of((t, u, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjShortToLongFunction<String, String> function = BiObjShortToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
