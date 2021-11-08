package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjDoubleToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjDoubleToShortFunction<String> function = ObjDoubleToShortFunction.of((t, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjDoubleToShortFunction<String> function = ObjDoubleToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
