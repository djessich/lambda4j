<#-- checks if given type represents a primitive type -->
<#function isPrimitive type = "">
    <#return (type?hasContent) && LambdaUtils.isPrimitiveType(type)>
</#function>

<#-- checks if the given lambda has primitive types -->
<#function isPrimitiveLambda target = lambda>
    <#return (target?hasContent) && (LambdaUtils.isPrimitiveType(target.firstInputType)
    || LambdaUtils.isPrimitiveType(target.secondInputType)
    || LambdaUtils.isPrimitiveType(target.thirdInputType)
    || LambdaUtils.isPrimitiveType(target.returnType))>
</#function>

<#-- prints number string for first argument if lambdas arity is greater than 1 -->
<#function first target = lambda>
    <#if (target.arity > 1)>
        <#return "first">
    </#if>
    <#return "">
</#function>
