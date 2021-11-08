package org.lambda4j.consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShortConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ShortConsumer consumer = ShortConsumer.of(Assertions::assertNotNull);
        Assertions.assertNotNull(consumer);
        consumer.accept((short) 0);
    }

    @Test
    void of_givenNull_returnsNull() {
        ShortConsumer consumer = ShortConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
