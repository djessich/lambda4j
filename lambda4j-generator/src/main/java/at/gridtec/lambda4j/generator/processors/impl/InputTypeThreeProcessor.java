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
package at.gridtec.lambda4j.generator.processors.impl;

import at.gridtec.lambda4j.generator.entities.LambdaEntity;
import at.gridtec.lambda4j.generator.entities.TypeEntity;
import at.gridtec.lambda4j.generator.processors.Processor;
import at.gridtec.lambda4j.generator.util.LambdaUtils;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a {@link Processor} which creates copies of the given lambda to set all possible lambda input types for
 * the {@code second} argument to the lambda. These copies are handed over to next {@code Processor} to do further
 * processing. The result from next step is returned by this step.
 * <p>
 * Requirements by this step are the lambdas type ({@link LambdaEntity#getType()}), arity ({@link LambdaEntity#getArity()}) and
 * second input type {@link LambdaEntity#getSecondInputType()}.
 */
public final class InputTypeThreeProcessor extends Processor {

    @Override
    protected boolean processable(@Nonnull final LambdaEntity lambda) {
        boolean processable = lambda.getType() != null;
        if (lambda.getArity() >= 2) {
            processable = processable && lambda.getSecondInputType() != null;
        }
        return processable;
    }

    @Override
    @Nonnull
    protected List<LambdaEntity> process(@Nonnull final LambdaEntity lambda) {
        final List<LambdaEntity> lambdas = new LinkedList<>();

        // Check if it has arity 3; otherwise end call stack
        if (lambda.getArity() >= 3) {

            // Create new list to generate variants for the given lambda
            final List<LambdaEntity> genLambdas = new LinkedList<>();

            // Lambda has generical arg 2 so apply further generical and primitive input for arg 3
            if (!PRIMITIVES.contains(lambda.getSecondInputType().getTypeClass())) {

                // Apply generical input for arg 3
                final LambdaEntity generical = LambdaUtils.copy(lambda);
                TypeEntity type = new TypeEntity(Object.class, "V", "v", 3);
                generical.setThirdInputType(type);
                genLambdas.add(generical);

                // Apply primitive input for arg 3
                for (final Class<?> typeClass : PRIMITIVES) {
                    final LambdaEntity primitive = LambdaUtils.copy(lambda);
                    type = new TypeEntity(typeClass, typeClass.getSimpleName(), "value", 1);
                    primitive.setThirdInputType(type);
                    genLambdas.add(primitive);
                }
            }

            // Lambda has primitive arg 2 so apply same primitive type to arg 3
            else {
                final LambdaEntity primitive = LambdaUtils.copy(lambda);
                TypeEntity entity = primitive.getSecondInputType();
                if (!primitive.getFirstInputType().isPrimitive()) {
                    primitive.setThirdInputType(
                            new TypeEntity(entity.getTypeClass(), entity.getTypeName(), entity.getName(), 2));
                } else {
                    primitive.setThirdInputType(
                            new TypeEntity(entity.getTypeClass(), entity.getTypeName(), entity.getName(), 3));
                }
                genLambdas.add(primitive);
            }

            // For each generated given lambda variant
            for (LambdaEntity genLambda : genLambdas) {
                lambdas.addAll(next(genLambda));
            }
        } else {
            final LambdaEntity copy = LambdaUtils.copy(lambda);
            lambdas.addAll(next(copy));
        }

        return lambdas;
    }
}
