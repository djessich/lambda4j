/*
 * Copyright (c) 2016 Gridtec. All rights reserved.
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
package at.gridtec.lambda4j.functions;

import at.gridtec.lambda4j.functions.function.ThrowableFunction;

import java.time.Duration;
import java.time.Instant;

/**
 * Created by domin on 04.04.2016.
 */
public class Main {

    public static ThrowableFunction<Long, Long> CACHED;

    public static void main(String[] args) throws Throwable {

        CACHED = Main::uncached;
        CACHED = CACHED.memoized();

        Instant start = Instant.now();
        long value1 = CACHED.applyThrows(10L);
        long value2 = CACHED.applyThrows(10L);
        long value3 = CACHED.applyThrows(5L);
        long value4 = CACHED.applyThrows(7L);
        long value5 = CACHED.applyThrows(5L);
        long value6 = CACHED.applyThrows(10L);
        long value7 = CACHED.applyThrows(7L);
        Instant stop = Instant.now();

        System.out.println(value1);
        System.out.println(value2);
        System.out.println(value3);
        System.out.println(value4);
        System.out.println(value5);
        System.out.println(value6);
        System.out.println(value7);
        System.out.println(Duration.between(start, stop).abs());
    }

    private static long uncached(long n) throws Throwable {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (n < 1) {
            return n;
        }
        return n == 1 ? n : n * CACHED.applyThrows(n - 1);
    }

}
