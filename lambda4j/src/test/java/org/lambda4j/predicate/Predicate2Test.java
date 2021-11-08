package org.lambda4j.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Predicate2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        Predicate2<String> predicate = Predicate2.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        Predicate2<String> predicate = Predicate2.of(null);
        Assertions.assertNull(predicate);
    }
}
