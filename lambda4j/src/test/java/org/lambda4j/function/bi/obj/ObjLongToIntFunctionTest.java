package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjLongToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjLongToIntFunction<String> function = ObjLongToIntFunction.of((t, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjLongToIntFunction<String> function = ObjLongToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
