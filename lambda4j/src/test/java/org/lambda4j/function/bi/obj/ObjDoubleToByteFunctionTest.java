package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjDoubleToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjDoubleToByteFunction<String> function = ObjDoubleToByteFunction.of((t, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjDoubleToByteFunction<String> function = ObjDoubleToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
