package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiLongToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiLongToIntFunction<String> function = ObjBiLongToIntFunction.of((t, value1, value2) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiLongToIntFunction<String> function = ObjBiLongToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
