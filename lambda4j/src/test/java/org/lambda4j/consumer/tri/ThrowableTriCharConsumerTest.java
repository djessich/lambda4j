package org.lambda4j.consumer.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriCharConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriCharConsumer<Throwable> consumer = ThrowableTriCharConsumer.of((value1, value2, value3) -> {
            Assertions.assertNotNull(value1);
            Assertions.assertNotNull(value2);
            Assertions.assertNotNull(value3);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriCharConsumer<Throwable> consumer = ThrowableTriCharConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
