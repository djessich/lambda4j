package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjCharToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjCharToFloatFunction<String, Throwable> function =
                ThrowableObjCharToFloatFunction.of((t, value) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjCharToFloatFunction<String, Throwable> function = ThrowableObjCharToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
