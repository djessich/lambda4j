package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjIntFunction<String, String> function = ObjIntFunction.of((t, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjIntFunction<String, String> function = ObjIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
