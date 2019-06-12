<#assign genHelper = glex.getGlobalVar("PFHelper")> <#-- GeneratorHelper -->
<#assign parameterString="">
<#assign parameters=ast.getParameterList()>
<#list parameters as parameterSet>
    <#assign parameterString="${parameterString}${printType(parameterSet.getType())} ${parameterSet.getName()}">
    <#if parameterSet_has_next>
        <#assign parameterString="${parameterString}, ">
    </#if>
</#list>
${parameterString}