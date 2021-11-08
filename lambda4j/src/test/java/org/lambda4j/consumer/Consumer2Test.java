package org.lambda4j.consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Consumer2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        Consumer2<String> consumer = Consumer2.of(Assertions::assertNotNull);
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        Consumer2<String> consumer = Consumer2.of(null);
        Assertions.assertNull(consumer);
    }
}
