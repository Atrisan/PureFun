${signature("ifStatement")}
if(${defineHookPoint("<Expression>")})
${includeArgs("statement/BlockStatement.ftl",ifStatement.getThenStatement())}
<#if ifStatement.getElseStatementOpt().isPresent()>
    else <#if ifStatement.getElseStatement().getBlockStatementOpt().isPresent()>${includeArgs("statement/BlockStatement.ftl",ifStatement.getElseStatement().getBlockStatement())} <#else> ${includeArgs("statement/IfStatement.ftl",ifStatement.getElseStatement().getIfStatement())}  </#if>
</#if>