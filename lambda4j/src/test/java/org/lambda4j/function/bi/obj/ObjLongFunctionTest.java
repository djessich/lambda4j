package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjLongFunction<String, String> function = ObjLongFunction.of((t, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjLongFunction<String, String> function = ObjLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
