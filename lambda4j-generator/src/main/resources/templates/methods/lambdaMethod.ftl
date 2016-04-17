<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/throwable.ftl" as throwable>
<#import "../utils/tuple.ftl" as tuple>
<#import "../utils/types.ftl" as types>

/**
 * Applies this ${lambda.type.simpleName} to the given argument${helpers.s()}.
 *
<#include "../javadoc/paramArgumentInput.ftl">
<@.namespace.javadocReturnIfNotVoidLambdaMethod/>
<#include "../javadoc/throwsThrowable.ftl">
 */
${types.buildParameterType(lambda.returnType)} ${lambda.method}(${parameterString}) <@throwable.printThrowableDeclaration/>;

<#-- print overridden method of JDK lambda, if lambda is a JDK lambda -->
<#if lambda.throwable && lambda.fromJDK>
<#assign jdkLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, lambda.returnType, false, true)>
/**
 * Applies this ${lambda.type.simpleName} to the given argument${helpers.s()}.
 *
<#include "../javadoc/paramArgumentInput.ftl">
<@.namespace.javadocReturnIfNotVoidLambdaMethod/>
 * @apiNote This method mainly exists to use this {@link ${lambda.name}} in JRE specific methods only accepting {@link ${jdkLambda.name}}.
 * If this ${lambda.type.simpleName} should be applied, then the {@link #${lambda.method}(${parameterSimpleTypeString})} method should be used.
 * @implSpec Overrides the {@link ${jdkLambda.name}#${jdkLambda.method}(${parameterSimpleTypeString})} method by using a redefinition as default method. This
 * implementation calls the {@link #${lambda.method}(${parameterSimpleTypeString})} method of this function and catches the eventually thrown
 * {@link Throwable} from it. If it is of type {@link RuntimeException} or {@link Error} it is rethrown as is. Other
 * {@code Throwable} types are wrapped in a {@link ThrownByFunctionalInterfaceException}.
 */
@Override
default ${types.buildParameterType(jdkLambda.returnType)} ${jdkLambda.method}(${parameterString}) {
    // TODO: Remove commented code below
    /*try {
         ${helpers.printReturnIfNotVoid()} this.${lambda.method}(${parameterNameString});
    } catch (RuntimeException | Error e) {
        throw e;
    } catch (Throwable throwable) {
        throw new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable);
    }*/
    ${helpers.printReturnIfNotVoid()} nest().${jdkLambda.method}(${parameterNameString});
}
</#if>

<#-- print tupled apply only if lambda has arity greater than 2 and at least two generics -->
<#if (lambda.arity >= 2) && !helpers.isPrimitive(lambda.firstInputType) && !helpers.isPrimitive(lambda.secondInputType)>
/**
 * Applies this ${lambda.type.simpleName} to the given tuple.
 *
 * @param tuple The tuple to be applied to the ${lambda.type.simpleName}
<@.namespace.javadocParamForTupleAndPrimitiveInput/>
<@.namespace.javadocReturnIfNotVoidLambdaMethod/>
<#include "../javadoc/throwsNullPointerException.ftl">
<#include "../javadoc/throwsThrowable.ftl">
<#include "../javadoc/seeTupleClass.ftl">
 */
default ${types.buildParameterType(lambda.returnType)} ${lambda.method}(${annotation.nonnull} ${tuple.printTuple()} tuple ${helpers.isPrimitive(lambda.thirdInputType)?then(", " + types.buildParameter(lambda.thirdInputType), "")}) <@throwable.printThrowableDeclaration/> {
    Objects.requireNonNull(tuple);
    ${helpers.printReturnIfNotVoid()} ${lambda.method}(${tuple.printTupleAccess()} ${helpers.isPrimitive(lambda.thirdInputType)?then(", " + types.buildParameterName(lambda.thirdInputType), "")});
}
</#if>

<#-- a helper macro which prints javadoc param tag, if lambda has tupled and primitive input -->
<#macro javadocParamForTupleAndPrimitiveInput target = lambda>
<#if !helpers.isPrimitive(target.firstInputType) && !helpers.isPrimitive(target.secondInputType) && helpers.isPrimitive(lambda.thirdInputType)>
 * @param ${types.buildParameterName(lambda.thirdInputType)} The primitive value to be applied to the ${lambda.type.simpleName}
</#if>
</#macro>

<#-- a helper macro which prints javadoc return tag, if lambda is not of type consumer or runnable (no output) -->
<#macro javadocReturnIfNotVoidLambdaMethod target = lambda>
<#if !LambdaUtils.isOfTypeConsumer(target) && !LambdaUtils.isOfTypeRunnable(target)>
 * @return The return value from the ${target.type.simpleName}, which is its result.
</#if>
</#macro>

<#-- -->
<#macro javadocLambdaMethodLink target = lambda>
@link ${target.method}
</#macro>

<#-- @formatter:on -->