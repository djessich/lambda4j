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
    public boolean processable(@Nonnull final Lambda lambda) {
        return true;
    }

    @Override
    @Nonnull
    public List<Lambda> process(@Nonnull final Lambda lambda) {
        final List<Lambda> lambdas = new LinkedList<>();

        // Exclude some lambda as those will be evaluated later
        final List<LambdaTypeEnum> types = Arrays.stream(LambdaTypeEnum.values())
                //                .filter(type -> !type.equals(LambdaTypeEnum.COMPARATOR))
                //                .filter(type -> !type.equals(LambdaTypeEnum.CONSUMER))
                //                .filter(type -> !type.equals(LambdaTypeEnum.PREDICATE))
                //                .filter(type -> !type.equals(LambdaTypeEnum.RUNNABLE))
                .filter(type -> !type.equals(LambdaTypeEnum.OPERATOR)).collect(Collectors.toList());

        // Loop of all other types
        for (LambdaTypeEnum type : types) {
            final Lambda copy = LambdaUtils.copy(lambda);
            copy.setType(type);
            lambdas.addAll(next(copy));
        }
        return lambdas;
    }
}
