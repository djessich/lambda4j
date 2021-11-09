package org.lambda4j.consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableCharConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableCharConsumer<Throwable> consumer = ThrowableCharConsumer.of(Assertions::assertNotNull);
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableCharConsumer<Throwable> consumer = ThrowableCharConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
