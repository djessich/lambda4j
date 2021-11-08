package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjCharToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjCharToIntFunction<String> function = ObjCharToIntFunction.of((t, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjCharToIntFunction<String> function = ObjCharToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
