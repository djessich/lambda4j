package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjShortToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjShortToShortFunction<String, String> function =
                BiObjShortToShortFunction.of((t, u, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjShortToShortFunction<String, String> function = BiObjShortToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
