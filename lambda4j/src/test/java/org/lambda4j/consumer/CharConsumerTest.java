package org.lambda4j.consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CharConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        CharConsumer consumer = CharConsumer.of(Assertions::assertNotNull);
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        CharConsumer consumer = CharConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
