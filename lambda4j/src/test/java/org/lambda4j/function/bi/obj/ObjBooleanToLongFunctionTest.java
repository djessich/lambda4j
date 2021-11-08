package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBooleanToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBooleanToLongFunction<String> function = ObjBooleanToLongFunction.of((t, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBooleanToLongFunction<String> function = ObjBooleanToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
