package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjShortToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjShortToIntFunction<String> function = ObjShortToIntFunction.of((t, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjShortToIntFunction<String> function = ObjShortToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
