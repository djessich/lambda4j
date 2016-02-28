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
package at.gridtec.lambda4j.core.util;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * This class implements utils for {@link Throwable}.
 *
 * @see Throwable
 */
public final class ThrowableUtils {

    /**
     * Private constructor to prevent instantiation.
     */
    private ThrowableUtils() {

    }

    /**
     * The given {@link Throwable} is sneakily thrown by this method. This means that there is no need to catch it, nor
     * to declare that you throw it using the throws keyword. The exception is still thrown, but the Java compiler stops
     * warning about it. The following example demonstrates how to use this method correctly.
     * <pre>
     * public void run() {
     *     throw sneakyThrow(new IOException("Sneaky Thrown!"));
     * }
     * </pre>
     * The given {@code Throwable} is not wrapped (f.e in a {@link RuntimeException}, ignored, swallowed, or redefined.
     * The JVM actually does not know or care about the concept of a 'checked exceptions'. All this method does is hide
     * the act of throwing a checked exception from the java compiler.
     * <p>
     * Note: that this method has a return type of {@code RuntimeException}. It is therefore advised you always call
     * this method as argument to the {@code throw} statement to avoid compiler errors regarding no return statement and
     * similar problems. This method won't, of course, return an actual {@code RuntimeException}. It never returns, it
     * always throws the provided exception.
     * <p>
     * Warning: This method should be save, but use with care.
     *
     * @param t The throwable to throw without requiring you to catch its type.
     * @return A dummy RuntimeException; this method never returns normally, it <em>always</em> throws an exception.
     * @throws NullPointerException If given argument was {@code null}
     */
    public static RuntimeException sneakyThrow(@Nonnull Throwable t) {
        Objects.requireNonNull(t);
        ThrowableUtils.<RuntimeException>sneakyThrow0(t);
        return null;
    }

    @SuppressWarnings("unchecked")
    private static <X extends Throwable> void sneakyThrow0(Throwable t) throws X {
        throw (X) t;
    }
}
