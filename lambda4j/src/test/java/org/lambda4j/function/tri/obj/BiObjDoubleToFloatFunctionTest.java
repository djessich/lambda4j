package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjDoubleToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjDoubleToFloatFunction<String, String> function =
                BiObjDoubleToFloatFunction.of((t, u, value) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjDoubleToFloatFunction<String, String> function = BiObjDoubleToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
