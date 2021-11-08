package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjFloatToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjFloatToLongFunction<String> function = ObjFloatToLongFunction.of((t, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjFloatToLongFunction<String> function = ObjFloatToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
