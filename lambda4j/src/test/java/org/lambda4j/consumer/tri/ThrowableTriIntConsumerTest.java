package org.lambda4j.consumer.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriIntConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriIntConsumer<Throwable> consumer = ThrowableTriIntConsumer.of((value1, value2, value3) -> {
            Assertions.assertNotNull(value1);
            Assertions.assertNotNull(value2);
            Assertions.assertNotNull(value3);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriIntConsumer<Throwable> consumer = ThrowableTriIntConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
