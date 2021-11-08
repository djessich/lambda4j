package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjFloatToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjFloatToShortFunction<String, String> function =
                BiObjFloatToShortFunction.of((t, u, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjFloatToShortFunction<String, String> function = BiObjFloatToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
