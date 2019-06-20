${signature("returnStatment")}
return <#if returnStatment.getReturnExpressionOpt().isPresent()> ${defineHookPoint("<Expression>")} </#if>