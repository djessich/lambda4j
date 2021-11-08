package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjLongFunction<String, String, String> function = BiObjLongFunction.of((t, u, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjLongFunction<String, String, String> function = BiObjLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
