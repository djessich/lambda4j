package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiIntFunction<String, String> function = ObjBiIntFunction.of((t, value1, value2) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiIntFunction<String, String> function = ObjBiIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
