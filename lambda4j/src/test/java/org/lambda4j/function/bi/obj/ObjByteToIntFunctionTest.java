package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjByteToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjByteToIntFunction<String> function = ObjByteToIntFunction.of((t, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjByteToIntFunction<String> function = ObjByteToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
