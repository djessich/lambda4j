package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjByteToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjByteToLongFunction<String> function = ObjByteToLongFunction.of((t, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjByteToLongFunction<String> function = ObjByteToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
