package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjBooleanToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjBooleanToCharFunction<String, String> function =
                BiObjBooleanToCharFunction.of((t, u, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjBooleanToCharFunction<String, String> function = BiObjBooleanToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
