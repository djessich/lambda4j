/*
 * Copyright (c) 2021 The lambda4j authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lambda4j.consumer.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjLongConsumerTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjLongConsumer<String, String> consumer = BiObjLongConsumer.of((t, u, value) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(u);
            Assertions.assertNotNull(value);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjLongConsumer<String, String> consumer = BiObjLongConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
