package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjCharToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjCharToLongFunction<String> function = ObjCharToLongFunction.of((t, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjCharToLongFunction<String> function = ObjCharToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
