package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjByteToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjByteToShortFunction<String> function = ObjByteToShortFunction.of((t, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjByteToShortFunction<String> function = ObjByteToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
