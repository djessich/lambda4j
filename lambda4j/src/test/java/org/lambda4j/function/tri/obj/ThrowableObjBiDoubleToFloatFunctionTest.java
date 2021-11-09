package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiDoubleToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiDoubleToFloatFunction<String, Throwable> function =
                ThrowableObjBiDoubleToFloatFunction.of((t, value1, value2) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiDoubleToFloatFunction<String, Throwable> function = ThrowableObjBiDoubleToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
