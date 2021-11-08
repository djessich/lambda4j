package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjByteToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjByteToByteFunction<String> function = ObjByteToByteFunction.of((t, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjByteToByteFunction<String> function = ObjByteToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
