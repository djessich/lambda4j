package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiBooleanToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiBooleanToLongFunction<String> function =
                ObjBiBooleanToLongFunction.of((t, value1, value2) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiBooleanToLongFunction<String> function = ObjBiBooleanToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
