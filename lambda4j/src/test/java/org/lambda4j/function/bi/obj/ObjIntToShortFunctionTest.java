package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjIntToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjIntToShortFunction<String> function = ObjIntToShortFunction.of((t, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjIntToShortFunction<String> function = ObjIntToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
