<#compress>
${signature("func")}
<#assign gh = glex.getGlobalVar("pfHelper")> <#-- GeneratorHelper -->
<#assign parameterString="">
<#assign parameters=func.getFunctionParameterList()>
<#list parameters as parameterSet>
    <#assign parameterString="${parameterString}${gh.printType(parameterSet.getType())} ${parameterSet.getName()}">
    <#if parameterSet_has_next>
        <#assign parameterString="${parameterString}, ">
    </#if>
</#list>
${parameterString}
</#compress>