package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiIntToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiIntToFloatFunction<String, Throwable> function =
                ThrowableObjBiIntToFloatFunction.of((t, value1, value2) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiIntToFloatFunction<String, Throwable> function = ThrowableObjBiIntToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
