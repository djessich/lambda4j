package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjLongToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjLongToShortFunction<String, String> function =
                BiObjLongToShortFunction.of((t, u, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjLongToShortFunction<String, String> function = BiObjLongToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
