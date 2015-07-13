/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
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

/**
 * This package allows us to use lambdas which are able to throw any {@link java.lang.Exception}s. Therefore we are
 * able to call any methods from lambdas without remembering to catch exceptions. The thrown {@link
 * java.lang.Exception} is always sneakily thrown from any throwable lambda, unless its a {@link
 * java.lang.RuntimeException}. This means that there is no need to catch the thrown exception, nor to declare that you
 * throw it using the <em>throws</em> keyword. The exception is still thrown, but the Java compiler stops warning about
 * it.
 * <p>
 * However, when using this throwable lambda, be aware of the following consequences:
 * <ol>
 * <li>If the calling code is to handle a thrown {@code Exception}, it MUST be declared in the methods
 * <em>throws</em> clause which uses this lambda. The compiler will not force you to add it.</li>
 * <li>If the calling code already handles a thrown {@code Exception}, it needs to be declared in the methods
 * <em>throws</em> clause which uses this lambda. If not the compiler prints an error that the corresponding {@code
 * try} block never throws the specific exception.</li>
 * <li>In any case, there is no way of explicitly catching the thrown {@code Exception} in the method which uses this
 * lambda. If you try, the compiler prints an error that the corresponding {@code try} block never throws the specific
 * exception.</li>
 * </ol>
 * <p>
 * When the calling code never throws the specific exception that it declares, you should omit it. For example: {@code
 * new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but UTF-8 is guaranteed by the Java
 * specification to be always present. The exception should therefore be omitted.
 * <p>
 * Moreover, if no checked exception should be used at all or its use is inappropriate for any reasons, omit the
 * declaration in the <em>throws</em> clause. The checked exception will behave just like a normal <b>unchecked</b>
 * exception due to sneaky throwing.
 * <p>
 * A throwable Lambda inherits from its non-throwable equivalent. This means, that for example {@link
 * at.gridtec.lambda4j.throwable.function.ThrowableFunction} inherits from {@link java.util.function.Function}.
 * So each throwable Lambda is usable as its non-throwing counterpart. For example:
 * <pre>
 *     final List&lt;String&gt; stringList = new ArrayList&lt;String&gt;();
 *     // ... populate list ...
 *     final ThrowableFunction&lt;String, Class&lt;?&gt;&gt; mapper = Class::forName;
 *     List&lt;Class&lt;?&gt;&gt; result = stringList.stream().map(mapper).collect(Collectors.toList());
 * </pre>
 * <p>
 * To avoid unnecessary casting of a non-throwable to a throwable lambda (anonymous), each throwable one does provide a
 * wrapping method to implicitly cast the non-throwable. In some cases the throwable instance is ambiguous for the
 * compiler, which might happen for overloaded methods accepting different functional interfaces. For example:
 * <pre>
 *     final List&lt;String&gt; stringList = new ArrayList&lt;String&gt;();
 *     // ... populate list ...
 *     List&lt;Class&lt;?&gt;&gt; result = stringList.stream()
 *             .map(Throwable.wrap(Class::forName)) // implicit cast using wrap method
 *             .collect(Collectors.toList());
 * </pre>
 * <p>
 * Also mapping from a non-throwable instance to a throwable one is possible. Each throwable lambda provides a method
 * for this case. The following example demonstrates such a case and assumes an old-style error handling lambda, which
 * is a lambda catching the appropriate exception and throwing an unchecked exception instead of the original one.
 * Obviously, the method used in this case is just for convenience.
 * <pre>
 *     final Function&lt;String, Class&lt;?&gt;&gt; function = str -&gt; {
 *         try {
 *             return Class::forName;
 *         } catch (ClassNotFoundException e) {
 *             throw new RuntimeException(e);
 *         }
 *     };
 *     ThrowableFunction&lt;String, Class&lt;?&gt;&gt; throwableFunction = ThrowableFunction.from(function);
 * </pre>
 * <p>
 * Moreover each throwable lambda, include some default methods. Those can be used to react to thrown exceptions. For
 * example we are able to execute some other throwable lambda if the first one fails.
 * <pre>
 *     final List&lt;String&gt; stringList = new ArrayList&lt;String&gt;();
 *     // ... populate list ...
 *     final ThrowableFunction&lt;String, Class&lt;?&gt;&gt; mapper = Class::forName;
 *     mapper.orElse(str -&gt; System.out.println("Failed for input " + str));
 *     List&lt;Class&lt;?&gt;&gt; result = stringList.stream().map(mapper).collect(Collectors.toList());
 * </pre>
 * <p>
 * Obviously, the default methods available differ from the type of lambda. This means, that a throwable {@link
 * java.util.function.Predicate} will include other methods than a throwable {@link java.util.function.Consumer}. For
 * more information on the default methods available, visit the documentation of the appropriate throwable
 * lambda.
 *
 * @see java.util.function
 */
package at.gridtec.lambda4j.throwable;