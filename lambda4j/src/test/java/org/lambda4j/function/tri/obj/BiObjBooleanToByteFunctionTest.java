package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjBooleanToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjBooleanToByteFunction<String, String> function =
                BiObjBooleanToByteFunction.of((t, u, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjBooleanToByteFunction<String, String> function = BiObjBooleanToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
