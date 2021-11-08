package org.lambda4j.function.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriBooleanFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriBooleanFunction<String> function =
                TriBooleanFunction.of((value1, value2, value3) -> Boolean.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriBooleanFunction<String> function = TriBooleanFunction.of((TriBooleanFunction<String>) null);
        Assertions.assertNull(function);
    }
}
