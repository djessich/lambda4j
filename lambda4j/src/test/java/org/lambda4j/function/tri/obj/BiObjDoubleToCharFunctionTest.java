package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjDoubleToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjDoubleToCharFunction<String, String> function =
                BiObjDoubleToCharFunction.of((t, u, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjDoubleToCharFunction<String, String> function = BiObjDoubleToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
