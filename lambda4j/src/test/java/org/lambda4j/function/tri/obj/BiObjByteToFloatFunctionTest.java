package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjByteToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjByteToFloatFunction<String, String> function =
                BiObjByteToFloatFunction.of((t, u, value) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjByteToFloatFunction<String, String> function = BiObjByteToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
