<#-- checks if given type represents a primitive type -->
<#function isPrimitive type = "">
    <#return (type?hasContent) && type.typePrimitive>
</#function>

<#-- checks if the given lambda has primitive types -->
<#function isPrimitiveLambda target = lambda>
    <#return (target?hasContent) && (.namespace.isPrimitive(target.firstInputType) || .namespace.isPrimitive(target.secondInputType) || .namespace.isPrimitive(target.thirdInputType) || .namespace.isPrimitive(target.returnType))>
</#function>

<#-- checks if the given lambda has primitive types (excluding return) -->
<#function isPrimitiveLambdaInput target = lambda>
    <#return (target?hasContent) && (.namespace.isPrimitive(target.firstInputType) || .namespace.isPrimitive(target.secondInputType) || .namespace.isPrimitive(target.thirdInputType))>
</#function>

<#-- prints number string for first argument if lambdas arity is greater than 1 -->
<#function first target = lambda>
    <#return (target.arity > 1)?then("first ", "")>
</#function>
