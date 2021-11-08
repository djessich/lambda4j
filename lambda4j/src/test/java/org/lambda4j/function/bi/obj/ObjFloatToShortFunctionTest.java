package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjFloatToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjFloatToShortFunction<String> function = ObjFloatToShortFunction.of((t, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjFloatToShortFunction<String> function = ObjFloatToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
