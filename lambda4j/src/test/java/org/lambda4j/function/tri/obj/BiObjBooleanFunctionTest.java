package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjBooleanFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjBooleanFunction<String, String, String> function = BiObjBooleanFunction.of((t, u, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjBooleanFunction<String, String, String> function = BiObjBooleanFunction.of(null);
        Assertions.assertNull(function);
    }
}
