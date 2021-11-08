package org.lambda4j.consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FloatConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        FloatConsumer consumer = FloatConsumer.of(Assertions::assertNotNull);
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        FloatConsumer consumer = FloatConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
