package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjFloatToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjFloatToLongFunction<String, String> function =
                BiObjFloatToLongFunction.of((t, u, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjFloatToLongFunction<String, String> function = BiObjFloatToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
