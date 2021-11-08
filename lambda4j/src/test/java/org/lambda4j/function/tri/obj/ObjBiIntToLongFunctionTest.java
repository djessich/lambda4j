package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiIntToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiIntToLongFunction<String> function = ObjBiIntToLongFunction.of((t, value1, value2) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiIntToLongFunction<String> function = ObjBiIntToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
