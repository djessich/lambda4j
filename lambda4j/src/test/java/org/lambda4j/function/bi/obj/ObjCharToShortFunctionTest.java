package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjCharToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjCharToShortFunction<String> function = ObjCharToShortFunction.of((t, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjCharToShortFunction<String> function = ObjCharToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
