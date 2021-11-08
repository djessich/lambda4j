package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjIntToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjIntToLongFunction<String> function = ObjIntToLongFunction.of((t, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjIntToLongFunction<String> function = ObjIntToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
