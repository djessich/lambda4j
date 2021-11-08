package org.lambda4j.consumer.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjIntConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjIntConsumer<String, String> consumer = BiObjIntConsumer.of((t, u, value) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(u);
            Assertions.assertNotNull(value);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjIntConsumer<String, String> consumer = BiObjIntConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
