package org.lambda4j.consumer.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiShortConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiShortConsumer<String, Exception> consumer =
                ThrowableObjBiShortConsumer.of((t, value1, value2) -> {
                    Assertions.assertNotNull(t);
                    Assertions.assertNotNull(value1);
                    Assertions.assertNotNull(value2);
                });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiShortConsumer<String, Exception> consumer = ThrowableObjBiShortConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}