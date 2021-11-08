package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjFloatToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjFloatToFloatFunction<String, String> function =
                BiObjFloatToFloatFunction.of((t, u, value) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjFloatToFloatFunction<String, String> function = BiObjFloatToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
