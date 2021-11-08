package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiBooleanToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiBooleanToShortFunction<String> function =
                ObjBiBooleanToShortFunction.of((t, value1, value2) -> Short.parseShort(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiBooleanToShortFunction<String> function = ObjBiBooleanToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
