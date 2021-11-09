package org.lambda4j.consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableDoubleConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableDoubleConsumer<Exception> consumer = ThrowableDoubleConsumer.of(Assertions::assertNotNull);
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableDoubleConsumer<Exception> consumer = ThrowableDoubleConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}