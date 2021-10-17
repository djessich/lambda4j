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

import org.lambda4j.generator.entities.LambdaEntity;
import org.lambda4j.generator.entities.TypeEntity;
import org.lambda4j.generator.processors.Processor;
import org.lambda4j.generator.util.LambdaUtils;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a {@link Processor} which creates copies of the given lambda to set all possible lambda return types to
 * the lambda. These copies are handed over to next {@code Processor} to do further processing. The result from next
 * step is returned by this step.
 * <p>
 * Requirements by this step is the lambdas type ({@link LambdaEntity#getType()}) only.
 */
public final class ReturnTypeProcessor extends Processor {

    @Override
    protected boolean processable(@Nonnull final LambdaEntity lambda) {
        return lambda.getType() != null;
    }

    @Override
    @Nonnull
    protected List<LambdaEntity> process(@Nonnull final LambdaEntity lambda) {
        final List<LambdaEntity> lambdas = new LinkedList<>();

        // Special Rule: Lambda is a Comparator it must return int
        if (LambdaUtils.isOfTypeComparator(lambda)) {
            final LambdaEntity copy = LambdaUtils.copy(lambda);
            TypeEntity type = new TypeEntity(int.class, int.class.getSimpleName(), "ret");
            copy.setReturnType(type);
            lambdas.addAll(next(copy));
        }

        // Special Rule: Lambda is a Consumer it must return nothing (void)
        else if (LambdaUtils.isOfTypeConsumer(lambda)) {
            final LambdaEntity copy = LambdaUtils.copy(lambda);
            TypeEntity type = new TypeEntity(void.class, void.class.getSimpleName(), "ret");
            copy.setReturnType(type);
            lambdas.addAll(next(copy));
        }

        // Special Rule: Lambda is a Predicate it must return boolean
        else if (LambdaUtils.isOfTypePredicate(lambda)) {
            final LambdaEntity copy = LambdaUtils.copy(lambda);
            TypeEntity type = new TypeEntity(boolean.class, boolean.class.getSimpleName(), "ret");
            copy.setReturnType(type);
            lambdas.addAll(next(copy));
        }

        // Special Rule: Lambda is a Runnable it must return nothing (void) and does not allow input -> end call stack
        else if (LambdaUtils.isOfTypeRunnable(lambda)) {
            final LambdaEntity copy = LambdaUtils.copy(lambda);
            TypeEntity type = new TypeEntity(void.class, void.class.getSimpleName(), "ret");
            copy.setReturnType(type);
            lambdas.addAll(next(copy));
        }

        // All other types will return normally, so evaluate them
        else {
            // Loop over left primitives without boolean (we do not like the other lambda types to return boolean,
            // as this is only allowed for predicates)
            List<Class<?>> primitivesWithoutBoolean = PRIMITIVES;
            if (!LambdaUtils.isOfTypeSupplier(lambda)) {
                primitivesWithoutBoolean = PRIMITIVES.stream()
                        .filter(clazz -> !clazz.equals(boolean.class))
                        .collect(Collectors.toList());
            }
            for (final Class<?> typeClass : primitivesWithoutBoolean) {
                final LambdaEntity primitive = LambdaUtils.copy(lambda);
                TypeEntity type = new TypeEntity(typeClass, typeClass.getSimpleName(), "ret");
                primitive.setReturnType(type);
                lambdas.addAll(next(primitive));
            }

            // Lambda returns generic; Special Rule: rename generic if lambda type is Supplier or a Operator
            final LambdaEntity generical = LambdaUtils.copy(lambda);
            if (LambdaUtils.isOfTypeSupplier(lambda) && LambdaUtils.isOfTypeOperator(lambda)) {
                TypeEntity type = new TypeEntity(Object.class, "T", "ret");
                generical.setReturnType(type);
            } else {
                TypeEntity type = new TypeEntity(Object.class, "R", "ret");
                generical.setReturnType(type);
            }
            lambdas.addAll(next(generical));
        }

        return lambdas;
    }
}
