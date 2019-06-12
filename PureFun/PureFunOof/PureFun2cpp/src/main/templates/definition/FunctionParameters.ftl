<#assign genHelper = glex.getGlobalVar("PFHelper")> <#-- GeneratorHelper -->
<#assign parameterString="">
<#assign parameters=ast.getParameterList()>
<#list parameters as parameterSet>
    <#if parameterSet_has_next>
        <#assign parameterString="${parameterString}${printType(parameterSet.getType())} ${parameterSet.getName()}, "
    <#else>
        <#assign parameterString="${parameterString}${printType(parameterSet.getType())} ${parameterSet.getName()}"
    <\#if>
<\#list>
${parameterString}