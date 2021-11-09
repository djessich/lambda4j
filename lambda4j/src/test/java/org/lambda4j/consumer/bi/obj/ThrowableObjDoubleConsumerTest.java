package org.lambda4j.consumer.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjDoubleConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjDoubleConsumer<String, Throwable> consumer = ThrowableObjDoubleConsumer.of((t, value) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(value);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjDoubleConsumer<String, Throwable> consumer = ThrowableObjDoubleConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
