package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjLongToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjLongToLongFunction<String> function = ObjLongToLongFunction.of((t, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjLongToLongFunction<String> function = ObjLongToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
