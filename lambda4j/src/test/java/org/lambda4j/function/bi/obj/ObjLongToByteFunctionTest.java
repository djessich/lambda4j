package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjLongToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjLongToByteFunction<String> function = ObjLongToByteFunction.of((t, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjLongToByteFunction<String> function = ObjLongToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
