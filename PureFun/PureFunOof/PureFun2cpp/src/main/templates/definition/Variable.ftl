<#assign genHelper = glex.getGlobalVar("pfHelper")> <#-- GeneratorHelper -->
${signature(variable, prefix, sym)}

${printType(sym.getType(variable))} ${prefix}${variable.Name}<#if variable.isPresentExpression() && existsHookPoint("<Expression>")> = </#if>${defineHookPoint("<Expression>")};