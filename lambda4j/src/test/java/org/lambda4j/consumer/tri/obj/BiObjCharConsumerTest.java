package org.lambda4j.consumer.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjCharConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjCharConsumer<String, String> consumer = BiObjCharConsumer.of((t, u, value) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(u);
            Assertions.assertNotNull(value);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjCharConsumer<String, String> consumer = BiObjCharConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
