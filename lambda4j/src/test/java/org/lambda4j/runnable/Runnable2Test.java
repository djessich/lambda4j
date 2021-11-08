package org.lambda4j.runnable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Runnable2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        Runnable2 runnable = Runnable2.of(System.out::println);
        Assertions.assertNotNull(runnable);
    }

    @Test
    void of_givenNull_returnsNull() {
        Runnable2 runnable = Runnable2.of(null);
        Assertions.assertNull(runnable);
    }
}
