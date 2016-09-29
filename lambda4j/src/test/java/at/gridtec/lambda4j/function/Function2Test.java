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
package at.gridtec.lambda4j.function;

import static org.assertj.core.api.Assertions.assertThat;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.Consumer2;

import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("ConstantConditions")
public class Function2Test {

    @Test
    public void shouldReturnNonNullLambdaWhenArgumentIsLambdaExpression() {
        final Function2<Object, Object> lambda = Function2.of(t -> t);
        assertThat(lambda).isNotNull();
    }

    @Test
    public void shouldReturnNonNullLambdaWhenArgumentIsMethodReference() {
        class Type {
            private Object methodReference(Object obj) {
                return null;
            }
        }
        final Type type = new Type();
        final Function2<Object, Object> lambda = Function2.of(type::methodReference);
        assertThat(lambda).isNotNull();
    }

    @Test
    public void shouldReturnNullWhenArgumentIsNull() {
        final Function2<Object, Object> nullReference = Function2.of(null);
        assertThat(nullReference).isNull();
    }

    @Test
    public void shouldApplyLambdaFromArgumentForValidArgumentLambdaExpressionAndObject() {
        final String result = Function2.call(t -> t, "test");
        assertThat(result).isEqualTo("test");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenCallArgumentOneIsNull() {
        Function2.call(null, new Object());
    }

    @Test
    public void shouldReturnInputAsIsWhenNonNullIdentityLambdaIsApplied() {
        final Function2<String, String> identity = Function2.identity();
        assertThat(identity).isNotNull();
        assertThat(identity.apply("test")).isEqualTo("test");
    }

    @Test
    public void shouldReturnNonNullConstantValueForNonNullConstantValue() {
        final Function2<Object, Object> lambda = Function2.constant(5);
        assertThat(lambda).isNotNull();
        assertThat(lambda.apply(0)).isEqualTo(5);
        assertThat(lambda.apply(1)).isEqualTo(5); // second time to check constness
    }

    @Test
    public void shouldReturnNullConstantValueForNullConstantValue() {
        final Function2<Object, Object> lambda = Function2.constant(null);
        assertThat(lambda).isNotNull();
        assertThat(lambda.apply(0)).isEqualTo(null);
        assertThat(lambda.apply(1)).isEqualTo(null); // second time to check constness
    }

    @Test
    public void shouldReturnArityOfOne() {
        final Function2<Object, Object> lambda = obj -> null;
        assertThat(lambda.arity()).isEqualTo(1);
    }

    @Test
    public void shouldReturnNonNullComposedLambdaWhenComposedWithCompose() {
        final Function2<Object, Object> lambda = obj -> obj;
        final Function2<Object, Object> before = obj -> obj;
        final Function2<Object, Object> composed = lambda.compose(before);
        assertThat(composed).isNotNull();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenComposedWithComposeNull() {
        Function2.of(obj -> obj).compose(null);
    }

    @Test
    public void shouldReturnNonNullComposedLambdaWhenComposedWithAndThen() {
        final Function2<Object, Object> lambda = obj -> obj;
        final Function2<Object, Object> after = obj -> obj;
        final Function2<Object, Object> composed = lambda.andThen(after);
        assertThat(composed).isNotNull();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenComposedWithAndThenNull() {
        Function2.of(obj -> obj).compose(null);
    }

    @Test
    public void shouldReturnNonNullConsumedLambda() {
        final Function2<Object, Object> lambda = obj -> obj;
        final Consumer2<Object> consumer = System.out::println;
        final Consumer2<Object> consumed = lambda.consume(consumer);
        assertThat(consumed).isNotNull();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenConsumedByNull() {
        Function2.of(obj -> obj).consume(null);
    }

    @Test
    public void shouldReturnNonNullReversedLambda() {
        final Function2<Object, Object> lambda = obj -> obj;
        assertThat(lambda.reversed()).isNotNull();
    }

    @Test
    public void shouldReturnNonNullMemoizedLambda() {
        final AtomicInteger integer = new AtomicInteger(0);
        final Function2<Integer, Integer> lambda = i -> i + integer.getAndIncrement();
        final Function2<Integer, Integer> memoized = lambda.memoized();
        // Returned memoized lambda must not be null
        assertThat(memoized).isNotNull();
        // Should apply lambda on first apply()
        final int expected = memoized.apply(1);
        // Should return memoized value if applied again with input 1
        assertThat(memoized.apply(1)).isEqualTo(expected);
        // Should calculate new values when called subsequently with different parameters; AtomicInteger should only be incremented once yet
        assertThat(memoized.apply(2)).isEqualTo(2 + 1);
        // Should return memoized value if applied again with input 2
        assertThat(memoized.apply(2)).isEqualTo(2 + 1);
    }

    @Test
    public void shouldNotMemoizeAgainWhenAlreadyMemoized() {
        final Function2<Object, Object> lambda = obj -> obj;
        final Function2<Object, Object> memoized = lambda.memoized();
        assertThat(memoized).isNotNull();
        assertThat(memoized.memoized() == memoized).isTrue();
    }

    @Test
    public void shouldRecognizeMemoizedLambdaWhenMemoized() {
        final Function2<Object, Object> lambda = obj -> obj;
        final Function2<Object, Object> memoized = lambda.memoized();
        assertThat(memoized).isNotNull();
        assertThat(lambda instanceof Lambda.Memoized).isFalse();
        assertThat(memoized instanceof Lambda.Memoized).isTrue();
    }
}