package org.lambda4j.consumer.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiBooleanConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiBooleanConsumer<String, Throwable> consumer =
                ThrowableObjBiBooleanConsumer.of((t, value1, value2) -> {
                    Assertions.assertNotNull(t);
                    Assertions.assertNotNull(value1);
                    Assertions.assertNotNull(value2);
                });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiBooleanConsumer<String, Throwable> consumer = ThrowableObjBiBooleanConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
