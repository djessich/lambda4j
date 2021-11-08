package org.lambda4j.consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongConsumer2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        LongConsumer2 consumer = LongConsumer2.of(Assertions::assertNotNull);
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        LongConsumer2 consumer = LongConsumer2.of(null);
        Assertions.assertNull(consumer);
    }
}
