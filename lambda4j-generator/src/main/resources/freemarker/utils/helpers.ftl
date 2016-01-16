<#-- checks if given type represents a primitive type -->
<#function isPrimitive type>
    <#return (type??) && LambdaUtils.isPrimitiveType(type)>
</#function>

<#-- prints number string for first argument if lambdas arity is greater than 1 -->
<#function first>
    <#if (lambda.arity > 1)>
        <#return "first">
    </#if>
    <#return "">
</#function>
