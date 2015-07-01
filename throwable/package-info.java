/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
 *
 * This program is proprietary software; all information contained herein is, and
 * remains, the property of Gridtec and its suppliers, if any. The intellectual
 * and technical concepts contained herein are proprietary to Gridtec and its suppliers
 * and may be covered by Austrian and Foreign Patents, patents in process, and are
 * protected by trade secret or copyright law.
 *
 * Dissemination of this information or reproduction of this material is strictly
 * forbidden unless prior written permission is obtained from Gridtec company.
 *
 * This software consists of voluntary contributions made by individuals on behalf
 * of Gridtec. For more information on Gridtec, please refer to www.gridtec.at homepage.
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
 * A throwable Lambda inherits its Java 8, non-throwable equivalent. This means, that for example {@link
 * at.gridtec.internals.lang.function.throwable.ThrowableFunction} inherits from {@link java.util.function.Function}.
 * So each throwable Lambda is usable as its Java 8 counterpart. For example:
 * <pre>
 *     final List&lt;String&gt; stringList = new ArrayList&lt;String&gt;();
 *     // ... populate list ...
 *     final ThrowableFunction&lt;String, Class&lt;?&gt;&gt; mapper = Class::forName;
 *     List&lt;Class&lt;?&gt;&gt; result = stringList.stream().map(mapper).collect(Collectors.toList());
 * </pre>
 * <p>
 * Thereby any throwable lambda include some default methods. Those can be used to react to thrown exceptions. For
 * example we are able to execute some other lambda if the first one fails.
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
package at.gridtec.internals.lang.function.throwable;