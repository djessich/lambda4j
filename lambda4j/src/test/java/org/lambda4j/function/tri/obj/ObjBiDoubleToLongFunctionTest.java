package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiDoubleToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiDoubleToLongFunction<String> function =
                ObjBiDoubleToLongFunction.of((t, value1, value2) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiDoubleToLongFunction<String> function = ObjBiDoubleToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
