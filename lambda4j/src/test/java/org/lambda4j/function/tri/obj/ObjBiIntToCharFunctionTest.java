package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiIntToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiIntToCharFunction<String> function = ObjBiIntToCharFunction.of((t, value1, value2) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiIntToCharFunction<String> function = ObjBiIntToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
