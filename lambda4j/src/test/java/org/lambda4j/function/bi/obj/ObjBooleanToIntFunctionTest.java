package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBooleanToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBooleanToIntFunction<String> function = ObjBooleanToIntFunction.of((t, value) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBooleanToIntFunction<String> function = ObjBooleanToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}
