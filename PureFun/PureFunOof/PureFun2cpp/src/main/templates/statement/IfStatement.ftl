<#assign ifStatement=ast.get()>
if(${defineHookPoint("<Expression>")})
${include("BlockStatement.ftl",ifStatement.getThenStatement())}
<#if ifStatement.getElseStatementOpt().isPresent()>
    else <#if ifStatement.getElseStatement().getBlockStatementOpt().isPresent()>${include("BlockStatement.ftl",ifStatement.getElseStatement().getBlockStatement())} <#else> ${include("IfStatement.ftl",ifStatement.getElseStatement().getIfStatement())}  </#if>
</#if>