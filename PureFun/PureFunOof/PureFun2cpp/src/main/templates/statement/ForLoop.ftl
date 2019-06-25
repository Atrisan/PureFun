<#compress>
<#assign gh = glex.getGlobalVar("pfHelper")> <#-- GeneratorHelper -->
${signature("loop")}
<#assign loopControl=loop.getForControl()>
<#if gh.isCommonForControl(loopControl)>
<@compress single_line=true> for (
    <#if loopControl.isPresentForInit()>
        <#list loopControl.getForInit().getForInitExList() as vars>
            <#if vars.getExpressionOpt().isPresent()>
                ${gh.printExpression(vars.getExpression())}
            </#if>
            <#if vars.getVariableOpt().isPresent()>
                ${includeArgs("definition/Variable.ftl", vars.getVariable(), "")}
            </#if>
            <#if vars_has_next>
                ,
            </#if>
        </#list>
        <#assign controlString="">
    </#if>
    <#if loopControl.isPresentCondition()>
        ${gh.printExpression(loopControl.getCondition())};
    </#if>
    <#list loopControl.getExpressionList() as exp>
        ${gh.printExpression(exp)}
    </#list>
    </@compress>)
</#if>
<#if gh.isForEachControl(loopControl)>
    for(auto ${loopControl.getName()} : ${gh.printExpression(loopControl.getExpression())})
</#if>
</#compress>
${includeArgs("statement/BlockStatement.ftl", loop.getBlockStatement())}