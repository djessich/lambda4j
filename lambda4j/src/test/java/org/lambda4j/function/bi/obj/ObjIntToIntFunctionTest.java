package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjIntToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjIntToIntFunction<String> function = ObjIntToIntFunction.of((t, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjIntToIntFunction<String> function = ObjIntToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
