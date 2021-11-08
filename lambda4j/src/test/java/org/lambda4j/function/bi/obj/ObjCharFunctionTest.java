package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjCharFunction<String, String> function = ObjCharFunction.of((t, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjCharFunction<String, String> function = ObjCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
