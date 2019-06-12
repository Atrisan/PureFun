<#assign returnStatment=ast.get()>
return <#if returnStatment.getReturnExpressionOpt().isPresent()> ${defineHookPoint("<Expression>")} </#if>