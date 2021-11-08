package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiFloatFunction<String, String> function = ObjBiFloatFunction.of((t, value1, value2) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiFloatFunction<String, String> function = ObjBiFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
