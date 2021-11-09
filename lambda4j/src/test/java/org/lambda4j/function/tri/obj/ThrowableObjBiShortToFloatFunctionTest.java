package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiShortToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiShortToFloatFunction<String, Throwable> function =
                ThrowableObjBiShortToFloatFunction.of((t, value1, value2) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiShortToFloatFunction<String, Throwable> function = ThrowableObjBiShortToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
