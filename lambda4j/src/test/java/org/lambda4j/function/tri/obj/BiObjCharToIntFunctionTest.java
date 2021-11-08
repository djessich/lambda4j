package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjCharToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjCharToIntFunction<String, String> function =
                BiObjCharToIntFunction.of((t, u, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjCharToIntFunction<String, String> function = BiObjCharToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
