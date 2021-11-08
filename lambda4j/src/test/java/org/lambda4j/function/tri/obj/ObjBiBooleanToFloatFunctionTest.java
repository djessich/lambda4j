package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiBooleanToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiBooleanToFloatFunction<String> function =
                ObjBiBooleanToFloatFunction.of((t, value1, value2) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiBooleanToFloatFunction<String> function = ObjBiBooleanToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
