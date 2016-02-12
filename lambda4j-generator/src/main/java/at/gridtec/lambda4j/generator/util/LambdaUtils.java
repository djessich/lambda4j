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
package at.gridtec.lambda4j.generator.util;

import at.gridtec.lambda4j.generator.Lambda;
import at.gridtec.lambda4j.generator.LambdaTypeEnum;
import at.gridtec.lambda4j.generator.cache.LambdaCache;
import at.gridtec.lambda4j.generator.entities.TypeEntity;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.SerializationUtils;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Lambda descriptor utils class.
 */
// TODO missing javadocs
// TODO Search operations including returns
@SuppressWarnings("unused")
public final class LambdaUtils {

    /**
     * Private constructor to prevent instantiation.
     */
    private LambdaUtils() {

    }

    /**
     * Returns a deep copy of the given lambda.
     *
     * @param lambda The lambda to be copied
     * @return A deep copy of the given lambda.
     * @throws NullPointerException If given lambda is {@code null}
     */
    public static Lambda copy(@Nonnull final Lambda lambda) {
        Objects.requireNonNull(lambda);
        return SerializationUtils.clone(lambda);
    }

    /**
     * Searches for a {@link Lambda} using given lambda type, lambda arity and throwable flag. If the lambda exists, it
     * will be returned as is, otherwise {@code null} is returned.
     *
     * @param type The lambdas type
     * @param arity The lambdas arity
     * @param isThrowable The flag indicating if lambda is throwable
     * @return The lambda from given lambda type, lambda arity and throwable flag, or {@code null} if no such lambda
     * exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     */
    public static Lambda search(@Nonnull final LambdaTypeEnum type, @Nonnegative int arity, boolean isThrowable) {
        // Check arguments
        Objects.requireNonNull(type);
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }
        // Find lambda and return it if such or null
        return LambdaCache.getInstance()
                .getLambdas()
                .stream()
                .filter(l -> l.getType().equals(type))
                .filter(l -> l.getArity() == arity)
                .filter(l -> l.isThrowable() == isThrowable)
                .findFirst()
                .orElse(null);
    }

    /**
     * Searches for a {@link Lambda} using given lambda type, lambda arity, lambda return type and throwable flag. If
     * the lambda exists, it will be returned as is, otherwise {@code null} is returned. Thereby the return type may be
     * {@code null}.
     *
     * @param type The lambdas type
     * @param arity The lambdas arity
     * @param returnType The lambdas return type
     * @param isThrowable The flag indicating if lambda is throwable
     * @return The lambda from given lambda type, lambda arity, lambda return type and throwable flag, or {@code null}
     * if no such lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     */
    public static Lambda searchByReturnType(@Nonnull final LambdaTypeEnum type, @Nonnegative int arity,
            @Nullable final TypeEntity returnType, boolean isThrowable) {
        // Check arguments
        Objects.requireNonNull(type);
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Find lambda and return it if such or null
        return LambdaCache.getInstance()
                .getLambdas()
                .stream()
                .filter(l -> l.getType().equals(type))
                .filter(l -> l.getArity() == arity)
                .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                .filter(l -> l.isThrowable() == isThrowable)
                .findFirst()
                .orElse(null);
    }

    // TODO testing -> Javadoc
    public static Lambda searchByReturnType(@Nonnegative int arity, @Nullable final TypeEntity returnType,
            boolean isThrowable) {
        // Check arguments
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Find lambda and return it if such or null
        return LambdaCache.getInstance()
                .getLambdas()
                .stream()
                .filter(l -> l.getArity() == arity)
                .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                .filter(l -> l.isThrowable() == isThrowable)
                .findFirst()
                .orElse(null);
    }

    /**
     * Searches for a {@link Lambda} using given lambda type, lambda arity, lambda first input type and throwable flag.
     * If the lambda exists, it will be returned as is, otherwise {@code null} is returned. Thereby the first input type
     * may be {@code null}.
     *
     * @param type The lambdas type
     * @param arity The lambdas arity
     * @param firstInputType The lambdas first input type
     * @param isThrowable The flag indicating if lambda is throwable
     * @return The lambda from given lambda type, lambda arity, lambda first input type and throwable flag, or {@code
     * null} if no such lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     */
    public static Lambda searchByFirstInputType(@Nonnull final LambdaTypeEnum type, @Nonnegative int arity,
            @Nullable final TypeEntity firstInputType, boolean isThrowable) {
        // Check arguments
        Objects.requireNonNull(type);
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Find lambda and return it if such or null
        return LambdaCache.getInstance()
                .getLambdas()
                .stream()
                .filter(l -> l.getType().equals(type))
                .filter(l -> l.getArity() == arity)
                .filter(l -> l.getFirstInputType() == null || l.getFirstInputType().equals(firstInputType))
                .filter(l -> l.isThrowable() == isThrowable)
                .findFirst()
                .orElse(null);
    }

    /**
     * Searches for a {@link Lambda} using given lambda type, lambda arity, lambda first input type, lambda return type
     * and throwable flag. If the lambda exists, it will be returned as is, otherwise {@code null} is returned. Thereby
     * the first input or return type may be {@code null}.
     *
     * @param type The lambdas type
     * @param arity The lambdas arity
     * @param firstInputType The lambdas first input type
     * @param returnType The lambdas return type
     * @param isThrowable The flag indicating if lambda is throwable
     * @return The lambda from given lambda type, lambda arity, lambda first input type, lambda return type and
     * throwable flag, or {@code null} if no such lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     */
    // TODO do we really need lambda type
    public static Lambda searchByFirstInputAndReturnType(@Nonnull final LambdaTypeEnum type, @Nonnegative int arity,
            @Nullable final TypeEntity firstInputType, @Nullable final TypeEntity returnType, boolean isThrowable) {
        // Check arguments
        Objects.requireNonNull(type);
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }
        // Find lambda and return it if such or null
        return LambdaCache.getInstance()
                .getLambdas()
                .stream()
                .filter(l -> l.getType().equals(type))
                .filter(l -> l.getArity() == arity)
                .filter(l -> l.getFirstInputType() == null || l.getFirstInputType().equals(firstInputType))
                .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                .filter(l -> l.isThrowable() == isThrowable)
                .findFirst()
                .orElse(null);
    }

    // TODO do javadoc
    public static Lambda searchByFirstInputAndReturnType(@Nonnegative int arity,
            @Nullable final TypeEntity firstInputType, @Nullable final TypeEntity returnType, boolean isThrowable) {
        // Check arguments
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }
        // Find lambda and return it if such or null
        return LambdaCache.getInstance()
                .getLambdas()
                .stream()
                .filter(l -> l.getArity() == arity)
                .filter(l -> l.getFirstInputType() == null || l.getFirstInputType().equals(firstInputType))
                .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                .filter(l -> l.isThrowable() == isThrowable)
                .findFirst()
                .orElse(null);
    }

    /**
     * Searches for a {@link Lambda} using given lambda type, lambda arity, lambda second input type and throwable flag.
     * If the lambda exists, it will be returned as is, otherwise {@code null} is returned. Thereby the second input
     * type may be {@code null}.
     *
     * @param type The lambdas type
     * @param arity The lambdas arity
     * @param secondInputType The lambdas second input type
     * @param isThrowable The flag indicating if lambda is throwable
     * @return The lambda from given lambda type, lambda arity, lambda second input type and throwable flag, or {@code
     * null} if no such lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     */
    public static Lambda searchBySecondInputType(@Nonnull final LambdaTypeEnum type, @Nonnegative int arity,
            @Nullable final TypeEntity secondInputType, boolean isThrowable) {
        // Check arguments
        Objects.requireNonNull(type);
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Find lambda and return it if such or null
        return LambdaCache.getInstance()
                .getLambdas()
                .stream()
                .filter(l -> l.getType().equals(type))
                .filter(l -> l.getArity() == arity)
                .filter(l -> l.getSecondInputType() == null || l.getSecondInputType().equals(secondInputType))
                .filter(l -> l.isThrowable() == isThrowable)
                .findFirst()
                .orElse(null);
    }

    /**
     * Searches for a {@link Lambda} using given lambda type, lambda arity, lambda second input type, lambda return type
     * and throwable flag. If the lambda exists, it will be returned as is, otherwise {@code null} is returned. Thereby
     * the second input or return type may be {@code null}.
     *
     * @param type The lambdas type
     * @param arity The lambdas arity
     * @param secondInputType The lambdas second input type
     * @param returnType The lambdas return type
     * @param isThrowable The flag indicating if lambda is throwable
     * @return The lambda from given lambda type, lambda arity, lambda second input type, lambda return type and
     * throwable flag, or {@code null} if no such lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     */
    public static Lambda searchBySecondInputAndReturnType(@Nonnull final LambdaTypeEnum type, @Nonnegative int arity,
            @Nullable final TypeEntity secondInputType, @Nullable final TypeEntity returnType, boolean isThrowable) {
        // Check arguments
        Objects.requireNonNull(type);
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Find lambda and return it if such or null
        return LambdaCache.getInstance()
                .getLambdas()
                .stream()
                .filter(l -> l.getType().equals(type))
                .filter(l -> l.getArity() == arity)
                .filter(l -> l.getSecondInputType() == null || l.getSecondInputType().equals(secondInputType))
                .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                .filter(l -> l.isThrowable() == isThrowable)
                .findFirst()
                .orElse(null);
    }

    /**
     * Searches for a {@link Lambda} using given lambda type, lambda arity, lambda third input type and throwable flag.
     * If the lambda exists, it will be returned as is, otherwise {@code null} is returned. Thereby the third input type
     * may be {@code null}.
     *
     * @param type The lambdas type
     * @param arity The lambdas arity
     * @param thirdInputType The lambdas third input type
     * @param isThrowable The flag indicating if lambda is throwable
     * @return The lambda from given lambda type, lambda arity, lambda third  input type and throwable flag, or {@code
     * null} if no such lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     */
    public static Lambda searchByThirdInputType(@Nonnull final LambdaTypeEnum type, @Nonnegative int arity,
            @Nullable final TypeEntity thirdInputType, boolean isThrowable) {
        // Check arguments
        Objects.requireNonNull(type);
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Find lambda and return it if such or null
        return LambdaCache.getInstance()
                .getLambdas()
                .stream()
                .filter(l -> l.getType().equals(type))
                .filter(l -> l.getArity() == arity)
                .filter(l -> l.getThirdInputType() == null || l.getThirdInputType().equals(thirdInputType))
                .filter(l -> l.isThrowable() == isThrowable)
                .findFirst()
                .orElse(null);
    }

    /**
     * Searches for a {@link Lambda} using given lambda type, lambda arity, lambda third input type, lambda return type
     * and throwable flag. If the lambda exists, it will be returned as is, otherwise {@code null} is returned. Thereby
     * the third input or return type may be {@code null}.
     *
     * @param type The lambdas type
     * @param arity The lambdas arity
     * @param thirdInputType The lambdas third input type
     * @param returnType The lambdas return type
     * @param isThrowable The flag indicating if lambda is throwable
     * @return The lambda from given lambda type, lambda arity, lambda third  input type, lambda return type and
     * throwable flag, or {@code null} if no such lambda exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     */
    public static Lambda searchByThirdInputAndReturnType(@Nonnull final LambdaTypeEnum type, @Nonnegative int arity,
            @Nullable final TypeEntity thirdInputType, @Nullable final TypeEntity returnType, boolean isThrowable) {
        // Check arguments
        Objects.requireNonNull(type);
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Find lambda and return it if such or null
        return LambdaCache.getInstance()
                .getLambdas()
                .stream()
                .filter(l -> l.getType().equals(type))
                .filter(l -> l.getArity() == arity)
                .filter(l -> l.getThirdInputType() == null || l.getThirdInputType().equals(thirdInputType))
                .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                .filter(l -> l.isThrowable() == isThrowable)
                .findFirst()
                .orElse(null);
    }

    /**
     * Searches for a {@link Lambda} using given lambda type, input types and throwable flag. If the lambda exists, it
     * will be returned as is, otherwise {@code null} is returned. Thereby the input types may be {@code null}.
     *
     * @param type The lambdas type
     * @param arity The lambdas arity
     * @param firstInputType The lambdas first input type
     * @param secondInputType The lambdas second input type
     * @param thirdInputType The lambdas third input type
     * @param isThrowable The flag indicating if lambda is throwable
     * @return The lambda from given lambda type, input types and throwable flag, or {@code null} if no such lambda
     * exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     */
    public static Lambda searchByInputTypes(@Nonnull final LambdaTypeEnum type, @Nonnegative int arity,
            @Nullable final TypeEntity firstInputType, @Nullable final TypeEntity secondInputType,
            @Nullable final TypeEntity thirdInputType, boolean isThrowable) {
        // Check arguments
        Objects.requireNonNull(type);
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Find lambda and return it if such or null
        return LambdaCache.getInstance()
                .getLambdas()
                .stream()
                .filter(l -> l.getType().equals(type))
                .filter(l -> l.getArity() == arity)
                .filter(l -> l.getFirstInputType() == null || l.getFirstInputType().equals(firstInputType))
                .filter(l -> l.getSecondInputType() == null || l.getSecondInputType().equals(secondInputType))
                .filter(l -> l.getThirdInputType() == null || l.getThirdInputType().equals(thirdInputType))
                .filter(l -> l.isThrowable() == isThrowable)
                .findFirst()
                .orElse(null);
    }

    /**
     * Searches for a {@link Lambda} using given lambda type, input types, return type and throwable flag. If the lambda
     * exists, it will be returned as is, otherwise {@code null} is returned. Thereby the input types or return type may
     * be {@code null}.
     *
     * @param type The lambdas type
     * @param arity The lambdas arity
     * @param firstInputType The lambdas first input type
     * @param secondInputType The lambdas second input type
     * @param thirdInputType The lambdas third input type
     * @param returnType The lambdas return type
     * @param isThrowable The flag indicating if lambda is throwable
     * @return The lambda from given lambda type, input types and throwable flag, or {@code null} if no such lambda
     * exists.
     * @throws NullPointerException If given lambda type is {@code null}
     * @throws IllegalArgumentException If given lambda arity is < 0
     */
    public static Lambda searchByInputTypesAndReturnType(@Nonnull final LambdaTypeEnum type, @Nonnegative int arity,
            @Nullable final TypeEntity firstInputType, @Nullable final TypeEntity secondInputType,
            @Nullable final TypeEntity thirdInputType, @Nullable TypeEntity returnType, boolean isThrowable) {
        // Check arguments
        Objects.requireNonNull(type);
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Find lambda and return it if such or null
        return LambdaCache.getInstance()
                .getLambdas()
                .stream()
                .filter(l -> l.getType().equals(type))
                .filter(l -> l.getArity() == arity)
                .filter(l -> l.getFirstInputType() == null || l.getFirstInputType().equals(firstInputType))
                .filter(l -> l.getSecondInputType() == null || l.getSecondInputType().equals(secondInputType))
                .filter(l -> l.getThirdInputType() == null || l.getThirdInputType().equals(thirdInputType))
                .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                .filter(l -> l.isThrowable() == isThrowable)
                .findFirst()
                .orElse(null);
    }

    // TODO testing
    public static Lambda searchByInputTypesAndReturnType(@Nonnegative int arity,
            @Nullable final TypeEntity firstInputType, @Nullable final TypeEntity secondInputType,
            @Nullable final TypeEntity thirdInputType, @Nullable TypeEntity returnType, boolean isThrowable) {
        // Check arguments
        if (arity < 0) {
            throw new IllegalArgumentException("arity must be greater than 0");
        }

        // Find lambda and return it if such or null
        return LambdaCache.getInstance()
                .getLambdas()
                .stream()
                .filter(l -> l.getArity() == arity)
                .filter(l -> l.getFirstInputType() == null || l.getFirstInputType().equals(firstInputType))
                .filter(l -> l.getSecondInputType() == null || l.getSecondInputType().equals(secondInputType))
                .filter(l -> l.getThirdInputType() == null || l.getThirdInputType().equals(thirdInputType))
                .filter(l -> l.getReturnType() == null || l.getReturnType().equals(returnType))
                .filter(l -> l.isThrowable() == isThrowable)
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the {@link LambdaTypeEnum#COMPARATOR} lambda type.
     *
     * @return The {@code LambdaTypeEnum#COMPARATOR} lambda type.
     */
    public static LambdaTypeEnum getComparatorType() {
        return LambdaTypeEnum.COMPARATOR;
    }

    /**
     * Returns the {@link LambdaTypeEnum#CONSUMER} lambda type.
     *
     * @return The {@code LambdaTypeEnum#CONSUMER} lambda type.
     */
    public static LambdaTypeEnum getConsumerType() {
        return LambdaTypeEnum.CONSUMER;
    }

    /**
     * Returns the {@link LambdaTypeEnum#FUNCTION} lambda type.
     *
     * @return The {@code LambdaTypeEnum#FUNCTION} lambda type.
     */
    public static LambdaTypeEnum getFunctionType() {
        return LambdaTypeEnum.FUNCTION;
    }

    /**
     * Returns the {@link LambdaTypeEnum#OPERATOR} lambda type.
     *
     * @return The {@code LambdaTypeEnum#OPERATOR} lambda type.
     */
    public static LambdaTypeEnum getOperatorType() {
        return LambdaTypeEnum.OPERATOR;
    }

    /**
     * Returns the {@link LambdaTypeEnum#PREDICATE} lambda type.
     *
     * @return The {@code LambdaTypeEnum#PREDICATE} lambda type.
     */
    public static LambdaTypeEnum getPredicateType() {
        return LambdaTypeEnum.PREDICATE;
    }

    /**
     * Returns the {@link LambdaTypeEnum#RUNNABLE} lambda type.
     *
     * @return The {@code LambdaTypeEnum#RUNNABLE} lambda type.
     */
    public static LambdaTypeEnum getRunnableType() {
        return LambdaTypeEnum.RUNNABLE;
    }

    /**
     * Returns the {@link LambdaTypeEnum#SUPPLIER} lambda type.
     *
     * @return The {@code LambdaTypeEnum#SUPPLIER} lambda type.
     */
    public static LambdaTypeEnum getSupplierType() {
        return LambdaTypeEnum.SUPPLIER;
    }

    /**
     * Checks if the given {@link Lambda} is of the given type.
     *
     * @param lambda The {@code Lambda} to be checked
     * @param type The type to check if given lambda is of
     * @return {@code true} if given lambda is of given type, {@code false} otherwise.
     * @throws NullPointerException If given {@code Lambda} is {@code null}
     */
    public static boolean isOfType(@Nonnull final Lambda lambda, @Nullable final LambdaTypeEnum type) {
        Objects.requireNonNull(lambda);
        return lambda.getType().equals(type);
    }

    /**
     * Checks if the given {@link Lambda} is of type {@link LambdaTypeEnum#COMPARATOR}.
     *
     * @param lambda The {@code Lambda} to be checked
     * @return {@code true} if given lambda is of type {@code LambdaTypeEnum#COMPARATOR}, {@code false} otherwise.
     * @throws NullPointerException If given argument is {@code null}
     * @see #isOfType(Lambda, LambdaTypeEnum)
     */
    public static boolean isOfTypeComparator(@Nonnull final Lambda lambda) {
        Objects.requireNonNull(lambda);
        return isOfType(lambda, LambdaTypeEnum.COMPARATOR);
    }

    /**
     * Checks if the given {@link Lambda} is of type {@link LambdaTypeEnum#CONSUMER}.
     *
     * @param lambda The {@code Lambda} to be checked
     * @return {@code true} if given lambda is of type {@code LambdaTypeEnum#CONSUMER}, {@code false} otherwise.
     * @throws NullPointerException If given argument is {@code null}
     * @see #isOfType(Lambda, LambdaTypeEnum)
     */
    public static boolean isOfTypeConsumer(@Nonnull final Lambda lambda) {
        Objects.requireNonNull(lambda);
        return isOfType(lambda, LambdaTypeEnum.CONSUMER);
    }

    /**
     * Checks if the given {@link Lambda} is of type {@link LambdaTypeEnum#FUNCTION}.
     *
     * @param lambda The {@code Lambda} to be checked
     * @return {@code true} if given lambda is of type {@code LambdaTypeEnum#FUNCTION}, {@code false} otherwise.
     * @throws NullPointerException If given argument is {@code null}
     * @see #isOfType(Lambda, LambdaTypeEnum)
     */
    public static boolean isOfTypeFunction(@Nonnull final Lambda lambda) {
        Objects.requireNonNull(lambda);
        return isOfType(lambda, LambdaTypeEnum.FUNCTION);
    }

    /**
     * Checks if the given {@link Lambda} is of type {@link LambdaTypeEnum#OPERATOR}.
     *
     * @param lambda The {@code Lambda} to be checked
     * @return {@code true} if given lambda is of type {@link LambdaTypeEnum#OPERATOR}, {@code false} otherwise.
     * @throws NullPointerException If given argument is {@code null}
     * @see #isOfType(Lambda, LambdaTypeEnum)
     */
    public static boolean isOfTypeOperator(@Nonnull final Lambda lambda) {
        Objects.requireNonNull(lambda);
        return isOfType(lambda, LambdaTypeEnum.OPERATOR);
    }

    /**
     * Checks if the given {@link Lambda} is of type {@link LambdaTypeEnum#PREDICATE}.
     *
     * @param lambda The {@code Lambda} to be checked
     * @return {@code true} if given lambda is of type {@code LambdaTypeEnum#PREDICATE}, {@code false} otherwise.
     * @throws NullPointerException If given argument is {@code null}
     * @see #isOfType(Lambda, LambdaTypeEnum)
     */
    public static boolean isOfTypePredicate(@Nonnull final Lambda lambda) {
        Objects.requireNonNull(lambda);
        return isOfType(lambda, LambdaTypeEnum.PREDICATE);
    }

    /**
     * Checks if the given {@link Lambda} is of type {@link LambdaTypeEnum#RUNNABLE}.
     *
     * @param lambda The {@code Lambda} to be checked
     * @return {@code true} if given lambda is of type {@link LambdaTypeEnum#RUNNABLE}, {@code false} otherwise.
     * @throws NullPointerException If given argument is {@code null}
     * @see #isOfType(Lambda, LambdaTypeEnum)
     */
    public static boolean isOfTypeRunnable(@Nonnull final Lambda lambda) {
        Objects.requireNonNull(lambda);
        return isOfType(lambda, LambdaTypeEnum.RUNNABLE);
    }

    /**
     * Checks if the given {@link Lambda} is of type {@link LambdaTypeEnum#SUPPLIER}.
     *
     * @param lambda The {@code Lambda} to be checked
     * @return {@code true} if given lambda is of type {@code LambdaTypeEnum#SUPPLIER}, {@code false} otherwise.
     * @throws NullPointerException If given argument is {@code null}
     * @see #isOfType(Lambda, LambdaTypeEnum)
     */
    public static boolean isOfTypeSupplier(@Nonnull final Lambda lambda) {
        Objects.requireNonNull(lambda);
        return isOfType(lambda, LambdaTypeEnum.SUPPLIER);
    }

    /**
     * Returns a {@link TypeEntity} referencing a {@code Object} type.
     *
     * @return A a {@code TypeEntity} referencing a {@code Object} type.
     */
    public static TypeEntity getNewTypeEntity() {
        return new TypeEntity();
    }

    /**
     * Returns a {@link TypeEntity} referencing a {@code Object} type.
     *
     * @return A a {@code TypeEntity} referencing a {@code Object} type.
     */
    public static TypeEntity getObjectTypeEntity() {
        return new TypeEntity(Object.class, "Object");
    }

    /**
     * Returns a {@link TypeEntity} referencing a {@code boolean} type.
     *
     * @return A a {@code TypeEntity} referencing a {@code boolean} type.
     */
    public static TypeEntity getBooleanTypeEntity() {
        return new TypeEntity(boolean.class, boolean.class.getSimpleName());
    }

    /**
     * Returns a {@link TypeEntity} referencing a {@code byte} type.
     *
     * @return A a {@code TypeEntity} referencing a {@code byte} type.
     */
    public static TypeEntity getByteTypeEntity() {
        return new TypeEntity(byte.class, byte.class.getSimpleName());
    }

    /**
     * Returns a {@link TypeEntity} referencing a {@code char} type.
     *
     * @return A a {@code TypeEntity} referencing a {@code char} type.
     */
    public static TypeEntity getCharTypeEntity() {
        return new TypeEntity(char.class, char.class.getSimpleName());
    }

    /**
     * Returns a {@link TypeEntity} referencing a {@code double} type.
     *
     * @return A a {@code TypeEntity} referencing a {@code double} type.
     */
    public static TypeEntity getDoubleTypeEntity() {
        return new TypeEntity(double.class, double.class.getSimpleName());
    }

    /**
     * Returns a {@link TypeEntity} referencing a {@code float} type.
     *
     * @return A a {@code TypeEntity} referencing a {@code float} type.
     */
    public static TypeEntity getFloatTypeEntity() {
        return new TypeEntity(float.class, float.class.getSimpleName());
    }

    /**
     * Returns a {@link TypeEntity} referencing a {@code int} type.
     *
     * @return A a {@code TypeEntity} referencing a {@code int} type.
     */
    public static TypeEntity getIntTypeEntity() {
        return new TypeEntity(int.class, int.class.getSimpleName());
    }

    /**
     * Returns a {@link TypeEntity} referencing a {@code long} type.
     *
     * @return A a {@code TypeEntity} referencing a {@code long} type.
     */
    public static TypeEntity getLongTypeEntity() {
        return new TypeEntity(long.class, long.class.getSimpleName());
    }

    /**
     * Returns a {@link TypeEntity} referencing a {@code short} type.
     *
     * @return A a {@code TypeEntity} referencing a {@code short} type.
     */
    public static TypeEntity getShortTypeEntity() {
        return new TypeEntity(short.class, short.class.getSimpleName());
    }

    /**
     * Returns a {@link TypeEntity} referencing {@code void}.
     *
     * @return A a {@code TypeEntity} referencing {@code void}.
     */
    public static TypeEntity getVoidTypeEntity() {
        return new TypeEntity(void.class, void.class.getSimpleName());
    }

    /**
     * Checks if the given string represents a primitive type.
     *
     * @param type The type in string representation
     * @return {@code true} if, and only if, given string represents a primitive type, {@code false} otherwise.
     */
    public static boolean isPrimitiveType(@Nullable final TypeEntity type) {
        return type != null && ClassUtils.isPrimitiveOrWrapper(type.getTypeClass());
    }

    /**
     * Builds an argument {@link String} containing the type and name. A possible output would be {@code T t} or {@code
     * byte value}.
     *
     * @param type The type from which the {@code String} should be built
     * @return An argument {@code String} containing type and name.
     * @throws NullPointerException If given argument is {@code null}
     */
    public static String buildParameterString(@Nonnull final TypeEntity type) {
        Objects.requireNonNull(type);
        final StringBuilder builder = new StringBuilder();
        boolean isPrimitve = isPrimitiveType(type);
        if (isPrimitve) {
            builder.append(buildParameterTypeStringIfPrimitive(type));
        } else {
            builder.append(buildParameterTypeStringIfGeneric(type));
        }
        builder.append(" ");
        if (isPrimitve) {
            builder.append(buildParameterNameStringIfPrimitive(type));
        } else {
            builder.append(buildParameterNameStringIfGeneric(type));
        }
        return builder.toString();
    }

    /**
     * Builds an argument {@link String} containing the type and name if not primitive. A possible output would be
     * {@code T t}.
     *
     * @param type The type from which the {@code String} should be built
     * @return An argument {@code String} containing type and name if not primitive.
     * @throws NullPointerException If given argument is {@code null}
     */
    public static String buildParameterStringIfGeneric(@Nonnull final TypeEntity type) {
        Objects.requireNonNull(type);
        final StringBuilder builder = new StringBuilder();
        if (!isPrimitiveType(type)) {
            builder.append(buildParameterString(type));
        }
        return builder.toString();
    }

    /**
     * Builds an argument {@link String} containing the type and name if primitive. A possible output would be {@code
     * byte value}.
     *
     * @param type The type from which the {@code String} should be built
     * @return An argument {@code String} containing type and name if primitive.
     * @throws NullPointerException If given argument is {@code null}
     */
    public static String buildParameterStringIfPrimitive(@Nonnull final TypeEntity type) {
        Objects.requireNonNull(type);
        final StringBuilder builder = new StringBuilder();
        if (isPrimitiveType(type)) {
            builder.append(buildParameterString(type));
        }
        return builder.toString();
    }

    /**
     * Builds an argument {@link String} containing only the given arguments type. A possible output would be {@code T}
     * or {@code byte}.
     *
     * @param type The type from which the {@code String} should be built
     * @return An argument {@code String} containing only the given arguments type.
     * @throws NullPointerException If given argument is {@code null}
     */
    public static String buildParameterTypeString(@Nonnull final TypeEntity type) {
        Objects.requireNonNull(type);
        return type.getTypeName();
    }

    /**
     * Builds an argument {@link String} containing only the given arguments type if not primitive. A possible output
     * would be {@code T}.
     *
     * @param type The type from which the {@code String} should be built
     * @return An argument {@code String} containing only the given arguments type if not primitive.
     * @throws NullPointerException If given argument is {@code null}
     */
    public static String buildParameterTypeStringIfGeneric(@Nonnull final TypeEntity type) {
        Objects.requireNonNull(type);
        final StringBuilder builder = new StringBuilder("");
        if (!isPrimitiveType(type)) {
            builder.append(buildParameterTypeString(type));
        }
        return builder.toString();
    }

    /**
     * Builds an argument {@link String} containing only the given arguments type if primitive. A possible output would
     * be {@code byte}.
     *
     * @param type The type from which the {@code String} should be built
     * @return An argument {@code String} containing only the given arguments type if primitive.
     * @throws NullPointerException If given argument is {@code null}
     */
    public static String buildParameterTypeStringIfPrimitive(@Nonnull final TypeEntity type) {
        Objects.requireNonNull(type);
        final StringBuilder builder = new StringBuilder("");
        if (isPrimitiveType(type)) {
            builder.append(buildParameterTypeString(type));
        }
        return builder.toString();
    }

    /**
     * Builds an argument {@link String} containing only the given arguments name. A possible output would be {@code t}
     * or {@code value}.
     *
     * @param type The type from which the {@code String} should be built
     * @return An argument {@code String} containing only the given arguments type.
     * @throws NullPointerException If given argument is {@code null}
     */
    public static String buildParameterNameString(@Nonnull final TypeEntity type) {
        Objects.requireNonNull(type);
        final StringBuilder builder = new StringBuilder("");
        if (isPrimitiveType(type)) {
            builder.append("value");
        } else {
            builder.append(type.getTypeName().toLowerCase());
        }
        return builder.toString();
    }

    /**
     * Builds an argument {@link String} containing only the given arguments name if not primitive. A possible output
     * would be {@code t}.
     *
     * @param type The type from which the {@code String} should be built
     * @return An argument {@code String} containing only the given arguments type if not primitive.
     * @throws NullPointerException If given argument is {@code null}
     */
    public static String buildParameterNameStringIfGeneric(@Nonnull final TypeEntity type) {
        final StringBuilder builder = new StringBuilder("");
        if (!isPrimitiveType(type)) {
            builder.append(buildParameterNameString(type));
        }
        return builder.toString();
    }

    /**
     * Builds an argument {@link String} containing only the given arguments name if primitive. A possible output would
     * be {@code value}.
     *
     * @param type The type from which the {@code String} should be built
     * @return An argument {@code String} containing only the given arguments type if primitive.
     * @throws NullPointerException If given argument is {@code null}
     */
    public static String buildParameterNameStringIfPrimitive(@Nonnull final TypeEntity type) {
        final StringBuilder builder = new StringBuilder("");
        if (isPrimitiveType(type)) {
            builder.append(buildParameterNameString(type));
        }
        return builder.toString();
    }

    public static String buildGenericTypeString(final Lambda lambda) {
        return buildGenericString(lambda, false);
    }

    public static String buildGenericTypeStringWithErasure(@Nonnull final Lambda lambda) {
        return buildGenericString(lambda, true);
    }

    private static String buildGenericString(@Nonnull final Lambda lambda, boolean erasureOn) {
        final StringBuilder builder = new StringBuilder("");
        List<TypeEntity> typeList = new LinkedList<>();
        typeList.add(lambda.getFirstInputType());
        typeList.add(lambda.getSecondInputType());
        typeList.add(lambda.getThirdInputType());
        typeList.add(lambda.getReturnType());

        // Filter all lambda types for null and primitiveness
        typeList = typeList.stream()
                .filter(Objects::nonNull)
                .filter(type -> !isPrimitiveType(type))
                .collect(Collectors.toList());

        // Loop over all left types
        for (final ListIterator<TypeEntity> it = typeList.listIterator(); it.hasNext(); ) {

            // If first element then append "<"
            if (!it.hasPrevious()) {
                builder.append("<");
            }

            // Get actual element
            TypeEntity type = it.next();

            // If type erasure is enabled append it
            if (erasureOn) {
                builder.append("?");
                if (!lambda.getReturnType().equals(type)) {
                    builder.append(" super ");
                } else {
                    builder.append(" extends ");
                }
            }

            // Append type itself
            builder.append(type.getTypeName());

            // If there is a next element then append ","; otherwise append ">"
            if (it.hasNext()) {
                builder.append(", ");
            } else {
                builder.append(">");
            }
        }

        return builder.toString();
    }
}
