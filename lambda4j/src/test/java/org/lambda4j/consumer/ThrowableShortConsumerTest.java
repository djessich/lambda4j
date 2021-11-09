package org.lambda4j.consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableShortConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableShortConsumer<Throwable> consumer = ThrowableShortConsumer.of(Assertions::assertNotNull);
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableShortConsumer<Throwable> consumer = ThrowableShortConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
