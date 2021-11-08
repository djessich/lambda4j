package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiLongToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiLongToCharFunction<String> function = ObjBiLongToCharFunction.of((t, value1, value2) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiLongToCharFunction<String> function = ObjBiLongToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
