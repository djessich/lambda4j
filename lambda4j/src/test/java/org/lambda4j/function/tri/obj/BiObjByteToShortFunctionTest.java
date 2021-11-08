package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjByteToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjByteToShortFunction<String, String> function =
                BiObjByteToShortFunction.of((t, u, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjByteToShortFunction<String, String> function = BiObjByteToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
