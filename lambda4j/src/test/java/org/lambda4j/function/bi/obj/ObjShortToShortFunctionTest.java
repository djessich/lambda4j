package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjShortToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjShortToShortFunction<String> function = ObjShortToShortFunction.of((t, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjShortToShortFunction<String> function = ObjShortToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
