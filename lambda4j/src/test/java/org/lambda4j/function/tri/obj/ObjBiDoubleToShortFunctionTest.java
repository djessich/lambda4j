package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiDoubleToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiDoubleToShortFunction<String> function =
                ObjBiDoubleToShortFunction.of((t, value1, value2) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiDoubleToShortFunction<String> function = ObjBiDoubleToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
