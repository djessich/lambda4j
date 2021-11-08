package org.lambda4j.consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DoubleConsumer2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        DoubleConsumer2 consumer = DoubleConsumer2.of(Assertions::assertNotNull);
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        DoubleConsumer2 consumer = DoubleConsumer2.of(null);
        Assertions.assertNull(consumer);
    }
}
