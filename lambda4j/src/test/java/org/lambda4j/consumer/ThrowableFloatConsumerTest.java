package org.lambda4j.consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableFloatConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableFloatConsumer<Exception> consumer = ThrowableFloatConsumer.of(Assertions::assertNotNull);
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableFloatConsumer<Exception> consumer = ThrowableFloatConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
