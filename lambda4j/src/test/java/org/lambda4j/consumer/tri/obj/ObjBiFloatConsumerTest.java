package org.lambda4j.consumer.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiFloatConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiFloatConsumer<String> consumer = ObjBiFloatConsumer.of((t, value1, value2) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(value1);
            Assertions.assertNotNull(value2);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiFloatConsumer<String> consumer = ObjBiFloatConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
