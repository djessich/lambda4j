package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjBooleanToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjBooleanToIntFunction<String, String> function =
                BiObjBooleanToIntFunction.of((t, u, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjBooleanToIntFunction<String, String> function = BiObjBooleanToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
