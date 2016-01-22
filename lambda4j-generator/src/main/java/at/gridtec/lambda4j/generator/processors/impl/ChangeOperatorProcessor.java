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
package at.gridtec.lambda4j.generator.processors.impl;

import at.gridtec.lambda4j.generator.Lambda;
import at.gridtec.lambda4j.generator.LambdaTypeEnum;
import at.gridtec.lambda4j.generator.processors.Processor;
import at.gridtec.lambda4j.generator.util.LambdaUtils;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a {@link Processor} which creates copies of the given lambda for all lambdas logically representing an
 * operator. For each copy the {@link Lambda#type} is changed accordingly. These copies are handed over to next {@code
 * Processor} to do further processing. The result from next step is returned by this step.
 * <p>
 * Requirements by this step are the lambdas arity ({@link Lambda#getArity()}), return type ({@link
 * Lambda#getReturnType()}) and input types ({@link Lambda#getInputOneType()}, {@link Lambda#getInputTwoType()}, {@link
 * Lambda#getInputThreeType()}).
 */
public final class ChangeOperatorProcessor extends Processor {

    @Override
    protected boolean processable(@Nonnull final Lambda lambda) {
        boolean processable = lambda.getReturnType() != null;
        if (lambda.getArity() >= 1) {
            processable = processable && lambda.getInputOneType() != null;
        }
        if (lambda.getArity() >= 2) {
            processable = processable && lambda.getInputTwoType() != null;
        }
        if (lambda.getArity() >= 3) {
            processable = processable && lambda.getInputThreeType() != null;
        }
        return processable;
    }

    @Override
    @Nonnull
    protected List<Lambda> process(@Nonnull final Lambda lambda) {
        final List<Lambda> lambdas = new LinkedList<>();

        // If lambda is an operator check if its valid; otherwise return lambda as-is
        boolean isValidPrimitive = false;
        boolean isValidGenerical = false;

        // Lambdas arity is at least 1
        if (lambda.getArity() >= 1) {
            isValidPrimitive = lambda.getReturnType().equals(lambda.getInputOneType());
            isValidGenerical = lambda.getReturnType().equals("R") && lambda.getInputOneType().equals("T");
        }

        // Lambdas arity is at least 2
        if (lambda.getArity() >= 2) {
            isValidPrimitive = isValidPrimitive && lambda.getReturnType().equals(lambda.getInputTwoType());
            isValidGenerical = isValidGenerical && lambda.getInputTwoType().equals("U");
        }

        // Lambdas arity is at least 3
        if (lambda.getArity() >= 3) {
            isValidPrimitive = isValidPrimitive && lambda.getReturnType().equals(lambda.getInputThreeType());
            isValidGenerical = isValidGenerical && lambda.getInputThreeType().equals("V");
        }

        // Before adding to list change lambda if operator; otherwise add as-is
        if (isValidPrimitive) {
            lambda.setType(LambdaTypeEnum.OPERATOR);
        } else if (isValidGenerical) {
            final Lambda copy = LambdaUtils.copy(lambda);
            copy.setType(LambdaTypeEnum.OPERATOR);
            copy.setReturnType("T");
            if (copy.getArity() >= 1) {
                copy.setInputOneType("T");
            }
            if (copy.getArity() >= 2) {
                copy.setInputTwoType("T");
            }
            if (copy.getArity() >= 3) {
                copy.setInputThreeType("T");
            }
            lambdas.addAll(next(lambda));
        }
        lambdas.addAll(next(lambda));
        return lambdas;
    }
}
