package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjShortFunction<String, String> function = ObjShortFunction.of((t, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjShortFunction<String, String> function = ObjShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
