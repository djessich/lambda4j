package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjByteToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjByteToCharFunction<String> function = ObjByteToCharFunction.of((t, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjByteToCharFunction<String> function = ObjByteToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
