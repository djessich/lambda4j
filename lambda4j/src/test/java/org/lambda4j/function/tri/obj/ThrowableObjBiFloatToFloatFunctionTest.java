package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiFloatToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiFloatToFloatFunction<String, Throwable> function =
                ThrowableObjBiFloatToFloatFunction.of((t, value1, value2) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiFloatToFloatFunction<String, Throwable> function = ThrowableObjBiFloatToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
