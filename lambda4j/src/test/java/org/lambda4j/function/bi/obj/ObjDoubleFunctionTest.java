package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjDoubleFunction<String, String> function = ObjDoubleFunction.of((t, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjDoubleFunction<String, String> function = ObjDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
