package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiLongToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiLongToFloatFunction<String, Throwable> function =
                ThrowableObjBiLongToFloatFunction.of((t, value1, value2) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiLongToFloatFunction<String, Throwable> function = ThrowableObjBiLongToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
