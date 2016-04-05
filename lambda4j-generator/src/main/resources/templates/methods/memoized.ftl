<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/tuple.ftl" as tuple>
<#import "../utils/types.ftl" as types>

<#-- parse only if lambda gets input and returns something (lambda therefore cannot be of type consumer, supplier or runnable) -->
<#if !LambdaUtils.isOfTypeConsumer(lambda) && !LambdaUtils.isOfTypeSupplier(lambda) && !LambdaUtils.isOfTypeRunnable(lambda) && lambda.returnType?has_content>
    <#-- prepare all arguments for memoized method -->
    <#assign returnType = types.buildParameterType(lambda.returnType) />
    <#assign cacheGenericString = .namespace.buildCacheGenericStringKeyPart() + ", " + types.buildGenericParameterType(lambda.returnType) />
    <#assign cacheComputeIfAbsentKey = .namespace.buildComputeIfAbsentKey(types.buildParameterName(lambda.firstInputType!""), types.buildParameterName(lambda.secondInputType!""), types.buildParameterName(lambda.thirdInputType!"")) />
    <#assign cacheComputeIfAbsentMappingFunction = lambda.throwable?then("ThrowableFunction.of(", "") + .namespace.buildComputeIfAbsentMappingFunction() + lambda.throwable?then(")", "")/>
    <#-- print memoized method -->
    <@.namespace.memoizedMethod returnType cacheGenericString cacheComputeIfAbsentKey cacheComputeIfAbsentMappingFunction/>
</#if>

<#-- a helper macro to centralize mmemoized method and to avoid unnecessary indenting -->
<#macro memoizedMethod returnType cacheGenericString cacheComputeIfAbsentKey cacheComputeIfAbsentMappingFunction>
/**
 * Returns a memoized (caching) version of this {@link ${lambda.name}}. Whenever it is called, the mapping between the
 * input parameter${helpers.s()} and the return value is preserved in a cache, making subsequent calls returning the memoized
 * value instead of computing the return value again.
 * <p>
 * Unless the ${lambda.type.simpleName} and therefore the used cache will be garbage-collected, it will keep all memoized values
 * forever.
 *
 * @return A memoized (caching) version of this {@code ${lambda.name}}.
 * @implSpec This implementation does not allow the input parameter${helpers.s()} or return value to be {@code null} for the
 * resulting memoized ${lambda.type.simpleName}, as the cache used internally does not permit {@code null} keys or values.
 * @implNote The returned memoized ${lambda.type.simpleName} can be safely used concurrently from multiple threads which makes it
 * thread-safe.
 */
${annotation.nonnull}
default ${lambda.name}${genericParameterTypeString} memoized() {
    if (this instanceof Memoized) {
        return this;
    } else {
        final Map<${cacheGenericString}> cache = new ConcurrentHashMap<${cacheGenericString}>();
        final Object lock = new Object();
        return (${lambda.name}${genericParameterTypeString} & Memoized) (${parameterNameString}) -> {
            final ${returnType} returnValue;
            synchronized (lock) {
                returnValue = cache.computeIfAbsent(${cacheComputeIfAbsentKey}, ${cacheComputeIfAbsentMappingFunction});
            }
            return returnValue;
        };
    }
}
</#macro>

<#-- a helper function which builds the generic cache string key part -->
<#function buildCacheGenericStringKeyPart target = lambda>
    <#local ret = "">
    <#if (target.arity == 1)>
        <#local ret = types.buildGenericParameterType(target.firstInputType)>
    <#elseif (target.arity == 2)>
        <#local ret = "Pair<" + types.buildGenericParameterType(target.firstInputType) + ", " + types.buildGenericParameterType(target.secondInputType) + ">">
    <#elseif (target.arity == 3)>
        <#local ret = "Triple<" + types.buildGenericParameterType(target.firstInputType) + ", " + types.buildGenericParameterType(target.secondInputType) + ", " + types.buildGenericParameterType(target.thirdInputType) + ">">
    </#if>
    <#return ret>
</#function>

<#-- a helper function to generate a string representing the key for cache.computeIfAbsent method call -->
<#function buildComputeIfAbsentKey input1 input2 input3 target = lambda>
    <#local ret = "">
    <#if (target.arity == 1)>
        <#local ret = input1>
    <#elseif (target.arity == 2)>
        <#local ret = "Pair.of(" + input1 + ", " + input2 + ")">
    <#elseif (target.arity == 3)>
        <#local ret = "Triple.of(" + input1 + ", " + input2 + "," + input3 + ")">
    </#if>
    <#return ret>
</#function>

<#-- a helper function to generate a string representing the mapping function for cache.computeIfAbsent method call -->
<#function buildComputeIfAbsentMappingFunction target = lambda>
    <#local ret = "">
    <#if (target.arity == 1)>
        <#local ret = "this::" + target.method>
    <#elseif (target.arity == 2)>
        <#local ret = "key -> " + target.method + "(key.getLeft(), key.getRight())">
    <#elseif (target.arity == 3)>
        <#local ret = "key -> " + target.method + "(key.getLeft(), key.getMiddle(), key.getRight())">
    </#if>
    <#return ret>
</#function>
<#-- @formatter:on -->