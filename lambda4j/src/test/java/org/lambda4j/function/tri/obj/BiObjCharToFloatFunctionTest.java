package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjCharToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjCharToFloatFunction<String, String> function =
                BiObjCharToFloatFunction.of((t, u, value) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjCharToFloatFunction<String, String> function = BiObjCharToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
