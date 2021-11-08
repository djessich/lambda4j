package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjIntToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjIntToByteFunction<String> function = ObjIntToByteFunction.of((t, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjIntToByteFunction<String> function = ObjIntToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
