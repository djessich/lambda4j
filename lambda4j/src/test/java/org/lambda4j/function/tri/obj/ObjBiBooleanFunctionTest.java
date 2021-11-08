package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiBooleanFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiBooleanFunction<String, String> function = ObjBiBooleanFunction.of((t, value1, value2) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiBooleanFunction<String, String> function = ObjBiBooleanFunction.of(null);
        Assertions.assertNull(function);
    }
}
