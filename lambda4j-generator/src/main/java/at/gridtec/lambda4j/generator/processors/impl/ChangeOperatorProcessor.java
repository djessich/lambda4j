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
 * Lambda#getReturnType()}) and input types ({@link Lambda#getFirstInputType()}, {@link Lambda#getSecondInputType()},
 * {@link Lambda#getThirdInputType()}).
 */
public final class ChangeOperatorProcessor extends Processor {

    @Override
    protected boolean processable(@Nonnull final Lambda lambda) {
        boolean processable = lambda.getReturnType() != null;
        if (lambda.getArity() >= 1) {
            processable = processable && lambda.getFirstInputType() != null;
        }
        if (lambda.getArity() >= 2) {
            processable = processable && lambda.getSecondInputType() != null;
        }
        if (lambda.getArity() >= 3) {
            processable = processable && lambda.getThirdInputType() != null;
        }
        return processable;
    }

    @Override
    @Nonnull
    protected List<Lambda> process(@Nonnull final Lambda lambda) {
        final List<Lambda> lambdas = new LinkedList<>();

        // Check if lambda is operator depending on its arity
        boolean isOperator = false;
        boolean isPrimitiveOperator = false;
        if (lambda.getArity() >= 1) {
            isOperator = lambda.getReturnType().equals(lambda.getFirstInputType());
            isPrimitiveOperator = isOperator && lambda.getFirstInputType().isPrimitive() && lambda.getReturnType()
                    .isPrimitive();
        }
        if (lambda.getArity() >= 2) {
            isOperator = isOperator && lambda.getReturnType().equals(lambda.getSecondInputType());
            isPrimitiveOperator = isOperator && lambda.getSecondInputType().isPrimitive() && lambda.getReturnType()
                    .isPrimitive();
        }
        if (lambda.getArity() >= 3) {
            isOperator = isOperator && lambda.getReturnType().equals(lambda.getThirdInputType());
            isPrimitiveOperator = isOperator && lambda.getThirdInputType().isPrimitive() && lambda.getReturnType()
                    .isPrimitive();
        }

        // If lambda fulfills operator requirements, then set type and do primitive operator checking
        if (isPrimitiveOperator) {
            lambda.setType(LambdaTypeEnum.OPERATOR);
        } else if (isOperator) {
            final Lambda copy = LambdaUtils.copy(lambda);
            copy.setType(LambdaTypeEnum.OPERATOR);

            // If lambda is a generical operator, we need to change its input and return type names
            if (!lambda.getReturnType().isPrimitive()) {
                copy.getReturnType().setTypeName("T");
                if (copy.getArity() >= 1) {
                    copy.getFirstInputType().setTypeName("T");
                }
                if (copy.getArity() >= 2) {
                    copy.getSecondInputType().setTypeName("T");
                }
                if (copy.getArity() >= 3) {
                    copy.getThirdInputType().setTypeName("T");
                }
            }
            lambdas.addAll(next(copy));
        }

        lambdas.addAll(next(lambda));
        return lambdas;
    }
}
