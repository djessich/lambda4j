package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjCharToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjCharToCharFunction<String, String> function =
                BiObjCharToCharFunction.of((t, u, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjCharToCharFunction<String, String> function = BiObjCharToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
