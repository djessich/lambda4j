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
package at.gridtec.lambda4j.generator;

import at.gridtec.lambda4j.generator.cache.LambdaCache;
import at.gridtec.lambda4j.generator.entities.AnnotationEntity;
import at.gridtec.lambda4j.generator.processors.Processor;
import at.gridtec.lambda4j.generator.processors.ProcessorChain;
import at.gridtec.lambda4j.generator.processors.impl.ArityProcessor;
import at.gridtec.lambda4j.generator.processors.impl.ChangeOperatorProcessor;
import at.gridtec.lambda4j.generator.processors.impl.InputTypeOneProcessor;
import at.gridtec.lambda4j.generator.processors.impl.InputTypeThreeProcessor;
import at.gridtec.lambda4j.generator.processors.impl.InputTypeTwoProcessor;
import at.gridtec.lambda4j.generator.processors.impl.NameProcessor;
import at.gridtec.lambda4j.generator.processors.impl.PrimitiveProcessor;
import at.gridtec.lambda4j.generator.processors.impl.ReturnTypeProcessor;
import at.gridtec.lambda4j.generator.processors.impl.ThrowableProcessor;
import at.gridtec.lambda4j.generator.processors.impl.TypeProcessor;
import at.gridtec.lambda4j.generator.util.LambdaUtils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.ToolManager;
import org.apache.velocity.util.StringUtils;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.List;

/**
 * Generator main file.
 */
// TODO As this is for velocity which will be removed from generator project, remove this class
public final class Generator {

    public static void main(String[] args) {

        // Prepare chain
        prepareChain();

        // Run chain and create all lambda descriptors
        final List<Lambda> lambdas = ProcessorChain.getInstance().invoke();
        LambdaCache.getInstance().setLambdas(lambdas);

        try (OutputStream ostream = new FileOutputStream("names.txt")) {
            for (Lambda lambda : lambdas) {
                String lambdaAsString = lambda.toString();
                lambdaAsString += "\n";
                ostream.write(lambdaAsString.getBytes());
            }
        } catch (IOException e) {
            System.err.println("Unable to write to file");
            System.exit(-1);
        }

        System.out.println(lambdas.size());

        // Initialize velocity
        Velocity.init();

        // Get templates
        Template template = Velocity.getTemplate("lambda4j-generator/src/main/resources/lambda.vm");

        final Lambda lambda = lambdas.stream().filter(p -> p.getName().equals("TriFunction")).findFirst().get();
        System.out.println(lambda);

        // Create context with velocity tools
        ToolManager velocityToolManager = new ToolManager();
        VelocityContext context = new VelocityContext(velocityToolManager.createContext());
        context.put("StringUtils", StringUtils.class);
        context.put("LambdaUtils", LambdaUtils.class);
        //context.put("LambdaTypeEnum", LambdaTypeEnum.class);
        context.put("basePath", "lambda4j-generator/src/main/resources");
        context.put("annotation", new AnnotationEntity());
        context.put("lambda", lambda);

        // Merge template to string writer
        StringWriter sw = new StringWriter();
        template.merge(context, sw);

        // Write to file
        try (FileWriter fw = new FileWriter("target/" + lambda.getName() + ".java")) {
            fw.write(sw.toString());
        } catch (IOException e) {
            System.err.println("Unable to write to file");
            System.exit(-1);
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
        Processor primitveProcessor = new PrimitiveProcessor();
        Processor throwableProcessor = new ThrowableProcessor();
        Processor nameProcessor = new NameProcessor();

        // Build chain turned around (start adding last step first)
        ProcessorChain.getInstance().addProcessor(nameProcessor);
        ProcessorChain.getInstance().addProcessor(throwableProcessor);
        ProcessorChain.getInstance().addProcessor(primitveProcessor);
        ProcessorChain.getInstance().addProcessor(changeOperatorProcessor);
        ProcessorChain.getInstance().addProcessor(inputTypeThreeProcessor);
        ProcessorChain.getInstance().addProcessor(inputTypeTwoProcessor);
        ProcessorChain.getInstance().addProcessor(inputTypeOneProcessor);
        ProcessorChain.getInstance().addProcessor(returnTypeProcessor);
        ProcessorChain.getInstance().addProcessor(arityProcessor);
        ProcessorChain.getInstance().addProcessor(typeProcessor);
    }
}
