package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjFloatFunction<String, String> function = ObjFloatFunction.of((t, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjFloatFunction<String, String> function = ObjFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
