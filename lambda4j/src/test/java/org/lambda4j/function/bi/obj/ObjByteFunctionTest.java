package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjByteFunction<String, String> function = ObjByteFunction.of((t, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjByteFunction<String, String> function = ObjByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
