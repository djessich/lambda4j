package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjCharFunction<String, String, String> function = BiObjCharFunction.of((t, u, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjCharFunction<String, String, String> function = BiObjCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
