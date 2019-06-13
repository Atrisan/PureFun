<#compress>
<#assign gh = glex.getGlobalVar("pfHelper")> <#-- GeneratorHelper -->
${signature("variable", "prefix")}
${gh.printType(variable.getType())} ${prefix}${variable.getName()}<#if variable.isPresentExpression() && existsHookPoint("<Expression>")> = </#if>${defineHookPoint("<Expression>")};
</#compress>