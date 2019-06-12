${signature(variable, prefix, sym)}

${sym.getType(variable)} ${prefix}${variable.Name}<#if variable.isPresentExpression() && existsHookPoint("<Expression>")> = </#if>${defineHookPoint("<Expression>")};