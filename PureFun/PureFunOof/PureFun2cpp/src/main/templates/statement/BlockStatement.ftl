<#assign gh = glex.getGlobalVar("PFHelper")> <#-- GeneratorHelper -->
<#assign BlockElements=ast.get().getBlockElementList()>
{
<#list BlockElements as BlockElement>
    <#if BlockElement.getExpressionOpt().isPresent()>
         ${defineHookPoint("<Expression>")}
    </#if>
    <#if BlockElement.getVariableOpt().isPresent()>
        ${include("./../definition/Variable.ftl",BlockElement.getVariable(),"")}
    </#if>
    <#if BlockElement.getStatementOpt().isPresent()>
        <#assign Statement=BlockElement.getStatement()>
        <#if gh.isAsyncStatement(Statement)>
            ${include("AsyncStatement.ftl",Statement)}
        </#if>
        <#if gh.isForStatement(Statement)>
            ${include("ForLoop.ftl",Statement)}
        </#if>
        <#if gh.isIfStatement(Statement)>
            ${include("IfStatement.ftl",Statement)}
        </#if>
        <#if gh.isReturnStatement(Statement)>
            ${include("ReturnStatement.ftl",Statement)}
        </#if>
        <#if gh.isWhileStatement(Statement)>
            ${include("WhileLoop.ftl",Statement)}
        </#if>
    </#if>

</#list>

}