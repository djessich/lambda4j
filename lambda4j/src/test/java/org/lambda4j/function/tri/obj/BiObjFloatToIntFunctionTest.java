package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjFloatToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjFloatToIntFunction<String, String> function =
                BiObjFloatToIntFunction.of((t, u, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjFloatToIntFunction<String, String> function = BiObjFloatToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
