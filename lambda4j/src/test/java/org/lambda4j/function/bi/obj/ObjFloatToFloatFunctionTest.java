package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjFloatToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjFloatToFloatFunction<String> function = ObjFloatToFloatFunction.of((t, value) -> Float.parseFloat(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjFloatToFloatFunction<String> function = ObjFloatToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}
