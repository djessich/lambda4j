package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjFloatToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjFloatToFloatFunction<String, Throwable> function =
                ThrowableObjFloatToFloatFunction.of((t, value) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjFloatToFloatFunction<String, Throwable> function = ThrowableObjFloatToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
