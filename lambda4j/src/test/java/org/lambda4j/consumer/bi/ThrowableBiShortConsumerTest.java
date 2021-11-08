package org.lambda4j.consumer.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiShortConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiShortConsumer<Exception> consumer = ThrowableBiShortConsumer.of((value1, value2) -> {
            Assertions.assertNotNull(value1);
            Assertions.assertNotNull(value2);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiShortConsumer<Exception> consumer = ThrowableBiShortConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
