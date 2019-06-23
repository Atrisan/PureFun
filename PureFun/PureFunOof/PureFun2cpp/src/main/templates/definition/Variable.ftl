<#compress>
${signature("variable", "prefix")}
<#assign gh = glex.getGlobalVar("pfHelper")> <#-- GeneratorHelper -->
${gh.printType(variable.getType())} ${prefix}${variable.getName()}<#if variable.isPresentExpression() && existsHookPoint("<Expression>") && gh.isNotContainerType(variable.getType())> = </#if>${defineHookPoint("<Expression>")};
</#compress>