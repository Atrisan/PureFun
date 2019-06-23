<#assign gh = glex.getGlobalVar("pfHelper")>
${signature("returnStatement")}
return <#if returnStatement.getReturnExpressionOpt().isPresent()> ${gh.printExpression(returnStatement.getReturnExpression())} </#if>