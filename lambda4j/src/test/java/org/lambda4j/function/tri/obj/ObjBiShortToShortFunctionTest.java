package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiShortToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiShortToShortFunction<String> function =
                ObjBiShortToShortFunction.of((t, value1, value2) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiShortToShortFunction<String> function = ObjBiShortToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
