package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiBooleanToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiBooleanToFloatFunction<String, Exception> function =
                ThrowableObjBiBooleanToFloatFunction.of((t, value1, value2) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiBooleanToFloatFunction<String, Exception> function =
                ThrowableObjBiBooleanToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
