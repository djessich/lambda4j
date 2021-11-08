package org.lambda4j.runnable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableRunnableTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableRunnable runnable = ThrowableRunnable.of(System.out::println);
        Assertions.assertNotNull(runnable);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableRunnable runnable = ThrowableRunnable.of(null);
        Assertions.assertNull(runnable);
    }
}
