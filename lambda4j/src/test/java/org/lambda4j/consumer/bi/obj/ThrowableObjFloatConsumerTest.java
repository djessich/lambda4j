package org.lambda4j.consumer.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjFloatConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjFloatConsumer<String, Throwable> consumer = ThrowableObjFloatConsumer.of((t, value) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(value);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjFloatConsumer<String, Throwable> consumer = ThrowableObjFloatConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
