package org.lambda4j.function.bi.to;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToDoubleBiFunction2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ToDoubleBiFunction2<String, String> function = ToDoubleBiFunction2.of((t, u) -> Double.parseDouble(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ToDoubleBiFunction2<String, String> function = ToDoubleBiFunction2.of(null);
        Assertions.assertNull(function);
    }
}
