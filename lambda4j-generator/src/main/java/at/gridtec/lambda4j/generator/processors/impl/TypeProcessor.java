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
import org.lambda4j.generator.processors.Processor;
import org.lambda4j.generator.util.LambdaUtils;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a {@link Processor} which creates copies of the given lambda to set all the specified types of {@link
 * LambdaTypeEnum} to the lambda. These copies are handed over to next {@code Processor} to do further processing. The
 * result from next step is returned by this step.
 * <p>
 * There are no requirements by this step.
 */
public final class TypeProcessor extends Processor {

    @Override
    public boolean processable(@Nonnull final LambdaEntity lambda) {
        return true;
    }

    @Override
    @Nonnull
    public List<LambdaEntity> process(@Nonnull final LambdaEntity lambda) {
        final List<LambdaEntity> lambdas = new LinkedList<>();

        // Exclude some lambda as those will be evaluated later
        final List<LambdaTypeEnum> types = Arrays.stream(LambdaTypeEnum.values())
                //                .filter(type -> !type.equals(LambdaTypeEnum.COMPARATOR))
                //                .filter(type -> !type.equals(LambdaTypeEnum.CONSUMER))
                //                .filter(type -> !type.equals(LambdaTypeEnum.PREDICATE))
                //                .filter(type -> !type.equals(LambdaTypeEnum.RUNNABLE))
                .filter(type -> !type.equals(LambdaTypeEnum.OPERATOR)).collect(Collectors.toList());

        // Loop of all other types
        for (LambdaTypeEnum type : types) {
            final LambdaEntity copy = LambdaUtils.copy(lambda);
            copy.setType(type);
            lambdas.addAll(next(copy));
        }
        return lambdas;
    }
}
