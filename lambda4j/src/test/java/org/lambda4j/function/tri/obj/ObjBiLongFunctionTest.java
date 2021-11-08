package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiLongFunction<String, String> function = ObjBiLongFunction.of((t, value1, value2) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiLongFunction<String, String> function = ObjBiLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
