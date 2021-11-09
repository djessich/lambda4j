package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjDoubleToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjDoubleToLongFunction<String, String> function =
                BiObjDoubleToLongFunction.of((t, u, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjDoubleToLongFunction<String, String> function = BiObjDoubleToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}