package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiShortToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiShortToIntFunction<String> function =
                ObjBiShortToIntFunction.of((t, value1, value2) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiShortToIntFunction<String> function = ObjBiShortToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
