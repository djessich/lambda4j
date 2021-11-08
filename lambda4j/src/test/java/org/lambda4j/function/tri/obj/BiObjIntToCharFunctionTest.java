package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjIntToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjIntToCharFunction<String, String> function = BiObjIntToCharFunction.of((t, u, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjIntToCharFunction<String, String> function = BiObjIntToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
