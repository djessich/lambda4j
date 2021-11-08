package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjDoubleToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjDoubleToFloatFunction<String> function = ObjDoubleToFloatFunction.of((t, value) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjDoubleToFloatFunction<String> function = ObjDoubleToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
