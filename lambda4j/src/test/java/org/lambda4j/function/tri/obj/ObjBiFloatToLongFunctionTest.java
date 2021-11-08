package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiFloatToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiFloatToLongFunction<String> function =
                ObjBiFloatToLongFunction.of((t, value1, value2) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiFloatToLongFunction<String> function = ObjBiFloatToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
