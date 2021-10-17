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
package org.lambda4j.generator.processors.impl;

import org.lambda4j.generator.LambdaTypeEnum;
import org.lambda4j.generator.entities.LambdaEntity;
import org.lambda4j.generator.entities.TypeEntity;
import org.lambda4j.generator.processors.Processor;
import org.lambda4j.generator.util.LambdaUtils;

import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a {@link Processor} which creates copies of the given lambda to set all possible lambda methods (depending
 * on lambda type) to the lambda. These copies are handed over to next {@code Processor} to do further processing. The
 * result from next step is returned by this step.
 * <p>
 * Requirements by this step are the lambdas type ({@link LambdaEntity#getType()}) and return type ({@link
 * LambdaEntity#getReturnType()}). As this processor relies on lambda throwable flag, this should be called <em>before</em>
 * this one is called.
 */
public final class MethodProcessor extends Processor {

    /**
     * Represents the method name constant for {@link LambdaTypeEnum#COMPARATOR} lambdas.
     */
    public static final String METHOD_COMPARATOR = "compare";

    /**
     * Represents the method name constant for {@link LambdaTypeEnum#CONSUMER} lambdas.
     */
    public static final String METHOD_CONSUMER = "accept";

    /**
     * Represents the method name constant for {@link LambdaTypeEnum#FUNCTION} lambdas.
     */
    public static final String METHOD_FUNCTION = "apply";

    /**
     * Represents the method name constant for {@link LambdaTypeEnum#OPERATOR} lambdas.
     */
    public static final String METHOD_OPERATOR = "apply";

    /**
     * Represents the method name constant for {@link LambdaTypeEnum#PREDICATE} lambdas.
     */
    public static final String METHOD_PREDICATE = "test";

    /**
     * Represents the method name constant for {@link LambdaTypeEnum#RUNNABLE} lambdas.
     */
    public static final String METHOD_RUNNABLE = "run";

    /**
     * Represents the method name constant for {@link LambdaTypeEnum#SUPPLIER} lambdas.
     */
    public static final String METHOD_SUPPLIER = "get";

    /**
     * Represents an identifier for method names where the return is primitive and therefore we need to include this
     * using the scheme {@code methodRootName + "As" + primitivveTypeName}. This is capitalized.
     */
    public static final String AS_IDENTIFIER = "As";

    /**
     * Represents an method identifier for all lambdas which are throwable.  This is capitalized.
     */
    public static final String THROWS_IDENTIFIER = "Throws";

    @Override
    protected boolean processable(@Nonnull LambdaEntity lambda) {
        return lambda.getType() != null && lambda.getReturnType() != null;
    }

    @Nonnull
    @Override
    protected List<LambdaEntity> process(@Nonnull LambdaEntity lambda) {
        final List<LambdaEntity> lambdas = new LinkedList<LambdaEntity>();
        final LambdaEntity copy = LambdaUtils.copy(lambda);
        final StringBuilder methodBuilder = new StringBuilder();

        // Set lambda method name depending on lambda type
        if (LambdaUtils.isOfTypeComparator(lambda)) {
            methodBuilder.append(METHOD_COMPARATOR);
        } else if (LambdaUtils.isOfTypeConsumer(lambda)) {
            methodBuilder.append(METHOD_CONSUMER);
        } else if (LambdaUtils.isOfTypeFunction(lambda)) {
            methodBuilder.append(METHOD_FUNCTION);
        } else if (LambdaUtils.isOfTypeOperator(lambda)) {
            methodBuilder.append(METHOD_OPERATOR);
        } else if (LambdaUtils.isOfTypePredicate(lambda)) {
            methodBuilder.append(METHOD_PREDICATE);
        } else if (LambdaUtils.isOfTypeRunnable(lambda)) {
            methodBuilder.append(METHOD_RUNNABLE);
        } else if (LambdaUtils.isOfTypeSupplier(lambda)) {
            methodBuilder.append(METHOD_SUPPLIER);
        }

        // Special Case: Lambdas of type function, operator and supplier will have "As" identifier if primitive return
        if (LambdaUtils.isOfTypeFunction(lambda)
                || LambdaUtils.isOfTypeOperator(lambda)
                || LambdaUtils.isOfTypeSupplier(lambda)) {
            asIdentifier(methodBuilder, lambda, lambda.getReturnType());
        }

        // If lambda now is throwable include the "Throws" identifier
        throwsIdentifier(methodBuilder, lambda);

        // Set to copy and handle to next processor for further processing
        copy.setMethod(methodBuilder.toString());
        return next(copy);
    }

    /**
     * A helper method to append {@link #AS_IDENTIFIER} to given {@link StringBuilder}. This happens only, if the given
     * lambda has a primitive return type.
     *
     * @param builder The {@code StringBuilder} to which to append the identifier and the primitive lambda return type
     * name
     * @param lambda The lambda to be used for checking
     * @param lambdaReturnType The return type to be checked for primitiveness
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implSpec The implementation required the return type be primitive. Only if this occasion is met, the identifier
     * will be appended.
     */
    private void asIdentifier(@Nonnull final StringBuilder builder, @Nonnull final LambdaEntity lambda,
            @Nonnull final TypeEntity lambdaReturnType) {
        Objects.requireNonNull(builder);
        Objects.requireNonNull(lambda);
        Objects.requireNonNull(lambdaReturnType);
        if (LambdaUtils.isPrimitiveType(lambdaReturnType)) {
            builder.append(AS_IDENTIFIER);
            builder.append(StringUtils.capitalize(lambdaReturnType.getTypeSimpleName()));
        }
    }

    /**
     * A helper method to append the {@link #THROWS_IDENTIFIER} to the given {@link StringBuilder}. This happens only,
     * if the given lambda represents a throwable lambda.
     *
     * @param builder The {@code StringBuilder} to which to append the identifier
     * @param lambda The lambda to be used for checking
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implSpec The implementation required the lambda to be a throwable lambda. Only if this occasion is met, the
     * identifier will be appended.
     */
    private void throwsIdentifier(@Nonnull final StringBuilder builder, @Nonnull final LambdaEntity lambda) {
        Objects.requireNonNull(builder);
        Objects.requireNonNull(lambda);
        if (lambda.isThrowable()) {
            builder.append(THROWS_IDENTIFIER);
        }
    }
}
