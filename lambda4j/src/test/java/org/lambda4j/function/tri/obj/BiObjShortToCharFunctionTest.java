package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjShortToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjShortToCharFunction<String, String> function = BiObjShortToCharFunction.of((t, u, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjShortToCharFunction<String, String> function = BiObjShortToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
