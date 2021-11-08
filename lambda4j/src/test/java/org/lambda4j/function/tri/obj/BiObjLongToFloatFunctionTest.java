package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjLongToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjLongToFloatFunction<String, String> function =
                BiObjLongToFloatFunction.of((t, u, value) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjLongToFloatFunction<String, String> function = BiObjLongToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
