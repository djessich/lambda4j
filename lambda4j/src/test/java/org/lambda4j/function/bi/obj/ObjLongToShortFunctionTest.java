package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjLongToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjLongToShortFunction<String> function = ObjLongToShortFunction.of((t, value) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjLongToShortFunction<String> function = ObjLongToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
