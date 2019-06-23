<#compress>
<#assign gh = glex.getGlobalVar("pfHelper")> <#-- GeneratorHelper -->
${signature("loop")}
<#assign loopControl=loop.getForControl()>
<#if gh.isCommonForControl(loopControl)>
<@compress single_line=true> for (
    <#if loopControl.isPresentForInit()>
        <#list loopControl.getForInit().getForInitExList() as vars>
            <#if vars.getExpressionOpt().isPresent()>
                ${defineHookPoint("<Expression>")}
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
        ${defineHookPoint("<Expression>")};
    </#if>
    ${defineHookPoint("<Expression>")})</@compress>
</#if>
<#if gh.isForEachControl(loopControl)>
    for(auto ${loopControl.getName()} : ${defineHookPoint("<Expression>")})
</#if>
</#compress>
${includeArgs("statement/BlockStatement.ftl", loop.getBlockStatement())}