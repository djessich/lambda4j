package org.lambda4j.consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ByteConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ByteConsumer consumer = ByteConsumer.of(Assertions::assertNotNull);
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ByteConsumer consumer = ByteConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
