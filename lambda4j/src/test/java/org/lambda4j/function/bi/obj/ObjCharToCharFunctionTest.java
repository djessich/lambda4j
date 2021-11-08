package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjCharToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjCharToCharFunction<String> function = ObjCharToCharFunction.of((t, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjCharToCharFunction<String> function = ObjCharToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
