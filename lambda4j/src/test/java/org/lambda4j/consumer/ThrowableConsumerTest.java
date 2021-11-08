package org.lambda4j.consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableConsumer<String, Exception> consumer = ThrowableConsumer.of(Assertions::assertNotNull);
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableConsumer<String, Exception> consumer = ThrowableConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
