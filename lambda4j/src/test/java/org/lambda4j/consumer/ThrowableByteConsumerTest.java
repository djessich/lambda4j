package org.lambda4j.consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableByteConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableByteConsumer<Throwable> consumer = ThrowableByteConsumer.of(Assertions::assertNotNull);
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableByteConsumer<Throwable> consumer = ThrowableByteConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
