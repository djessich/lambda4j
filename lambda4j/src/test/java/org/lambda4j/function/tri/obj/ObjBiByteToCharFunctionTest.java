package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiByteToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiByteToCharFunction<String> function = ObjBiByteToCharFunction.of((t, value1, value2) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiByteToCharFunction<String> function = ObjBiByteToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
