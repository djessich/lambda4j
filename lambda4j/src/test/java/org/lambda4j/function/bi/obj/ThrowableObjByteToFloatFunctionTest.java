package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjByteToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjByteToFloatFunction<String, Throwable> function =
                ThrowableObjByteToFloatFunction.of((t, value) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjByteToFloatFunction<String, Throwable> function = ThrowableObjByteToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
