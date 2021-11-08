package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiIntToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiIntToByteFunction<String> function = ObjBiIntToByteFunction.of((t, value1, value2) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiIntToByteFunction<String> function = ObjBiIntToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
