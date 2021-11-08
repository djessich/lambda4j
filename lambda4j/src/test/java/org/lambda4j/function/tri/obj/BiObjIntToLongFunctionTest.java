package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjIntToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjIntToLongFunction<String, String> function = BiObjIntToLongFunction.of((t, u, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjIntToLongFunction<String, String> function = BiObjIntToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
