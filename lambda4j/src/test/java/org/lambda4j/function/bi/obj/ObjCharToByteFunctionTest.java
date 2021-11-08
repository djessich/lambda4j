package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjCharToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjCharToByteFunction<String> function = ObjCharToByteFunction.of((t, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjCharToByteFunction<String> function = ObjCharToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
