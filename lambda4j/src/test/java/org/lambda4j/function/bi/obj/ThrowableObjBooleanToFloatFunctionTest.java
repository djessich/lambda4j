package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBooleanToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBooleanToFloatFunction<String, Throwable> function =
                ThrowableObjBooleanToFloatFunction.of((t, value) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBooleanToFloatFunction<String, Throwable> function = ThrowableObjBooleanToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
