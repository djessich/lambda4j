package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjLongToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjLongToFloatFunction<String, Throwable> function =
                ThrowableObjLongToFloatFunction.of((t, value) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjLongToFloatFunction<String, Throwable> function = ThrowableObjLongToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
