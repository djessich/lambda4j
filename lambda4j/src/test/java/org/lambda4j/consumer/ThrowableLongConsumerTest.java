package org.lambda4j.consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableLongConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableLongConsumer<Exception> consumer = ThrowableLongConsumer.of(Assertions::assertNotNull);
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableLongConsumer<Exception> consumer = ThrowableLongConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
