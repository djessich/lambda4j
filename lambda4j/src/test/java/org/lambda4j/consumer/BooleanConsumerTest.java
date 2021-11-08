package org.lambda4j.consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BooleanConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BooleanConsumer consumer = BooleanConsumer.of(Assertions::assertNotNull);
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        BooleanConsumer consumer = BooleanConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
