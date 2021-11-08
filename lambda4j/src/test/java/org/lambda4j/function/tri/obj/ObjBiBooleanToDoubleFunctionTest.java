package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiBooleanToDoubleFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiBooleanToDoubleFunction<String> function =
                ObjBiBooleanToDoubleFunction.of((t, value1, value2) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiBooleanToDoubleFunction<String> function = ObjBiBooleanToDoubleFunction.of(null);
        Assertions.assertNull(function);
    }
}
