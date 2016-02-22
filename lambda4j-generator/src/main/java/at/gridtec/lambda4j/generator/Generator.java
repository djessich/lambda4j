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
package at.gridtec.lambda4j.generator;

import at.gridtec.lambda4j.generator.cache.LambdaCache;
import at.gridtec.lambda4j.generator.entities.AnnotationEntity;
import at.gridtec.lambda4j.generator.processors.Processor;
import at.gridtec.lambda4j.generator.processors.ProcessorChain;
import at.gridtec.lambda4j.generator.processors.impl.ArityProcessor;
import at.gridtec.lambda4j.generator.processors.impl.ChangeOperatorProcessor;
import at.gridtec.lambda4j.generator.processors.impl.InputTypeOneProcessor;
import at.gridtec.lambda4j.generator.processors.impl.InputTypeThreeProcessor;
import at.gridtec.lambda4j.generator.processors.impl.MethodProcessor;
import at.gridtec.lambda4j.generator.processors.impl.NameProcessor;
import at.gridtec.lambda4j.generator.processors.impl.PackageProcessor;
import at.gridtec.lambda4j.generator.processors.impl.ReturnTypeProcessor;
import at.gridtec.lambda4j.generator.processors.impl.ThrowableProcessor;
import at.gridtec.lambda4j.generator.processors.impl.TypeProcessor;
import at.gridtec.lambda4j.generator.processors.impl.InputTypeTwoProcessor;
import at.gridtec.lambda4j.generator.util.LambdaUtils;
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
        final List<Lambda> lambdas = ProcessorChain.getInstance()
                .invoke()
                .stream()
                .filter(Objects::nonNull)
                .filter(lambda -> !lambda.getType().equals(LambdaTypeEnum.COMPARATOR))
                .filter(lambda -> !lambda.getType().equals(LambdaTypeEnum.RUNNABLE))
                .sorted(Comparator.comparing(Lambda::getType).thenComparing(Lambda::getPackageName)).peek(lambda -> {
                    final StringBuilder builder = new StringBuilder();
                    if (lambda.getArity() >= 1) {
                        builder.append(lambda.getFirstInputType().getTypeCount());
                    }
                    if (lambda.getArity() >= 2) {
                        builder.append(", " + lambda.getSecondInputType().getTypeCount());
                    }
                    if (lambda.getArity() >= 3) {
                        builder.append(", " + lambda.getThirdInputType().getTypeCount());
                    }
                    System.out.println(builder.toString());
                })
                .collect(Collectors.toList());
        LambdaCache.getInstance().setLambdas(lambdas);

        System.out.println(lambdas.size());
        System.out.println(LambdaCache.getInstance().getLambdas().size());
        //        final Lambda lambda = lambdas.stream().filter(p -> p.getName().equals("BiObjByteFunction")).findFirst().get();
        //        System.out.println(lambda);

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
        // During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // TODO only for lambda utils; really necessary?
        BeansWrapper wrapper = new BeansWrapperBuilder(Configuration.getVersion()).build();
        TemplateHashModel staticModels = wrapper.getStaticModels();
        TemplateHashModel lambdaUtilsStatics = (TemplateHashModel) staticModels.get(LambdaUtils.class.getName());

        for (final Lambda lambda : LambdaCache.getInstance().getLambdas()) {
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

        // Build chain turned around (start adding last step first)
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
