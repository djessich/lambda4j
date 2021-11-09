package org.lambda4j.consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableIntConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableIntConsumer<Throwable> consumer = ThrowableIntConsumer.of(Assertions::assertNotNull);
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableIntConsumer<Throwable> consumer = ThrowableIntConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
