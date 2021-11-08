package org.lambda4j.consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntConsumer2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        IntConsumer2 consumer = IntConsumer2.of(Assertions::assertNotNull);
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        IntConsumer2 consumer = IntConsumer2.of(null);
        Assertions.assertNull(consumer);
    }
}
