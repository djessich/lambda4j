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
 * Represents a {@link Processor} which creates copies of the given lambda to set the lambdas throwable flag. These
 * copies are handed over to next {@code Processor} to do further processing. The result from next step is returned by
 * this step.
 * <p>
 * There are no requirements by this step.
 */
public final class ThrowableProcessor extends Processor {

    @Override
    protected boolean processable(@Nonnull final LambdaEntity lambda) {
        return true;
    }

    @Override
    @Nonnull
    protected List<LambdaEntity> process(@Nonnull LambdaEntity lambda) {
        final List<LambdaEntity> lambdas = new LinkedList<>();
        final LambdaEntity copyNotThrowable = LambdaUtils.copy(lambda);
        final LambdaEntity copyThrowable = LambdaUtils.copy(lambda);
        copyNotThrowable.setThrowable(false);
        copyThrowable.setThrowable(true);
        copyThrowable.setThrowableType(new TypeEntity(Throwable.class, "X", "throwable", 4));
        lambdas.addAll(next(copyNotThrowable));
        lambdas.addAll(next(copyThrowable));
        return lambdas;
    }
}
