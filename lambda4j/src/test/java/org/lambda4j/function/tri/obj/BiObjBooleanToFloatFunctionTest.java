package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjBooleanToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjBooleanToFloatFunction<String, String> function =
                BiObjBooleanToFloatFunction.of((t, u, value) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjBooleanToFloatFunction<String, String> function = BiObjBooleanToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
