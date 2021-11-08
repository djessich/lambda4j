package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjShortToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjShortToByteFunction<String> function = ObjShortToByteFunction.of((t, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjShortToByteFunction<String> function = ObjShortToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
