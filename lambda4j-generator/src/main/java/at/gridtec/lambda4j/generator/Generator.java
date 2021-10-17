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
package org.lambda4j.generator;

import org.lambda4j.generator.cache.LambdaCache;
import org.lambda4j.generator.entities.AnnotationEntity;
import org.lambda4j.generator.entities.LambdaEntity;
import org.lambda4j.generator.processors.Processor;
import org.lambda4j.generator.processors.ProcessorChain;
import org.lambda4j.generator.processors.impl.ArityProcessor;
import org.lambda4j.generator.processors.impl.ChangeOperatorProcessor;
import org.lambda4j.generator.processors.impl.InputTypeOneProcessor;
import org.lambda4j.generator.processors.impl.InputTypeThreeProcessor;
import org.lambda4j.generator.processors.impl.InputTypeTwoProcessor;
import org.lambda4j.generator.processors.impl.JdkProcessor;
import org.lambda4j.generator.processors.impl.MethodProcessor;
import org.lambda4j.generator.processors.impl.NameProcessor;
import org.lambda4j.generator.processors.impl.PackageProcessor;
import org.lambda4j.generator.processors.impl.ReturnTypeProcessor;
import org.lambda4j.generator.processors.impl.ThrowableProcessor;
import org.lambda4j.generator.processors.impl.TypeProcessor;
import org.lambda4j.generator.util.LambdaUtils;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateHashModel;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Generator main file.
 */
public class Generator {

    public static void main(String[] args) throws Exception {

        final File file = new File(args[0]).getAbsoluteFile();
        file.mkdirs();
        System.out.println(file);

        // Prepare chain
        prepareChain();

        // Run chain and create all lambda descriptors
        final List<LambdaEntity> lambdas = ProcessorChain.getInstance()
                .invoke()
                .stream()
                .filter(Objects::nonNull)
                .filter(lambda -> !lambda.getType().equals(LambdaTypeEnum.COMPARATOR))
                .sorted(Comparator.comparing(LambdaEntity::getType).thenComparing(LambdaEntity::getPackageName))
                .map(lambda -> {
                    if (lambda.isFromJDK() && !lambda.isThrowable()) {
                        lambda.setName(lambda.getName() + "2");
                    }
                    return lambda;
                })
                .collect(Collectors.toList());
        LambdaCache.getInstance().setLambdas(lambdas);

        System.out.println(lambdas.size());
        System.out.println(LambdaCache.getInstance().getLambdas().size());

        // Create your Configuration instance, and specify if up to what FreeMarker
        // version (here 2.3.24) do you want to apply the fixes that are not 100%
        // backward-compatible. See the Configuration JavaDoc for details.
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);

        // Specify the source where the template files come from. Here I set a
        // plain directory for it, but non-file-system sources are possible too:
        cfg.setDirectoryForTemplateLoading(new File("lambda4j-generator/src/main/resources/templates"));

        // Set the preferred charset template files are stored in. UTF-8 is
        // a good choice in most applications:
        cfg.setDefaultEncoding("UTF-8");

        // Sets how errors will appear.
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // Allows to statically ad methods from LambdaUtils.class to Freemarker
        BeansWrapper wrapper = new BeansWrapperBuilder(Configuration.getVersion()).build();
        TemplateHashModel staticModels = wrapper.getStaticModels();
        TemplateHashModel lambdaUtilsStatics = (TemplateHashModel) staticModels.get(LambdaUtils.class.getName());

        for (final LambdaEntity lambda : LambdaCache.getInstance().getLambdas()) {
            System.out.println("Processing the following lambda: " + lambda);

            // Prepare context for Freemarker
            Map<String, Object> context = new HashMap<>();
            context.put("lambda", lambda);
            context.put("annotation", new AnnotationEntity());
            context.put("LambdaUtils", lambdaUtilsStatics);

            // Get Freemarker template
            Template temp = cfg.getTemplate("lambda.ftl");

            // Create path where to create file
            final String packagePath = lambda.getPackageName().replace(".", "/");
            final File outputDir = new File(file + "/" + packagePath).getAbsoluteFile();
            outputDir.mkdirs();

            // Merge template with lambda data model
            Writer out = new FileWriter(new File(outputDir.getAbsoluteFile(), lambda.getName() + ".java"));
            temp.process(context, out);
        }
    }

    /**
     * Prepares {@link ProcessorChain} by creating all {@link Processor}s and adding them to the chain (in reverse
     * order).
     */
    private static void prepareChain() {

        // Create instances for all processors
        Processor typeProcessor = new TypeProcessor();
        Processor arityProcessor = new ArityProcessor();
        Processor returnTypeProcessor = new ReturnTypeProcessor();
        Processor inputTypeOneProcessor = new InputTypeOneProcessor();
        Processor inputTypeTwoProcessor = new InputTypeTwoProcessor();
        Processor inputTypeThreeProcessor = new InputTypeThreeProcessor();
        Processor changeOperatorProcessor = new ChangeOperatorProcessor();
        Processor packageProcessor = new PackageProcessor();
        Processor throwableProcessor = new ThrowableProcessor();
        Processor methodProcessor = new MethodProcessor();
        Processor nameProcessor = new NameProcessor();
        Processor jdkProcessor = new JdkProcessor();

        // Build chain turned around (start adding last step first)
        ProcessorChain.getInstance().addProcessor(jdkProcessor);
        ProcessorChain.getInstance().addProcessor(nameProcessor);
        ProcessorChain.getInstance().addProcessor(methodProcessor);
        ProcessorChain.getInstance().addProcessor(throwableProcessor);
        ProcessorChain.getInstance().addProcessor(packageProcessor);
        ProcessorChain.getInstance().addProcessor(changeOperatorProcessor);
        ProcessorChain.getInstance().addProcessor(inputTypeThreeProcessor);
        ProcessorChain.getInstance().addProcessor(inputTypeTwoProcessor);
        ProcessorChain.getInstance().addProcessor(inputTypeOneProcessor);
        ProcessorChain.getInstance().addProcessor(returnTypeProcessor);
        ProcessorChain.getInstance().addProcessor(arityProcessor);
        ProcessorChain.getInstance().addProcessor(typeProcessor);
    }
}
