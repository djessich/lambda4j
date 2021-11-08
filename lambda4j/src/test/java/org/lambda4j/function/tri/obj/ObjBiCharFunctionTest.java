package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiCharFunction<String, String> function = ObjBiCharFunction.of((t, value1, value2) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiCharFunction<String, String> function = ObjBiCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
