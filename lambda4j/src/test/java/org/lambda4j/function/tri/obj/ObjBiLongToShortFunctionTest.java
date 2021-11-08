package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiLongToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiLongToShortFunction<String> function =
                ObjBiLongToShortFunction.of((t, value1, value2) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiLongToShortFunction<String> function = ObjBiLongToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
