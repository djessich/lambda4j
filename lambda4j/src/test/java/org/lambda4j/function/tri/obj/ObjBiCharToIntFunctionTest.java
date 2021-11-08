package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiCharToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiCharToIntFunction<String> function = ObjBiCharToIntFunction.of((t, value1, value2) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiCharToIntFunction<String> function = ObjBiCharToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
