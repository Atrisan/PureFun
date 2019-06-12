<#assign genHelper = glex.getGlobalVar("pfHelper")> <#-- GeneratorHelper -->
<#assign loop=ast.get()>
<#assign loopControl=loop.getForControl()>
<#if genHelper.isCommonForControl(loopControl)>
    for (
    <#if loopControl.isPresentForInit()>
        <#list loopControl.getForInit().getForInitExList() as vars>
            <#if vars.getExpressionOpt().isPresent()>
                ${defineHookPoint("<Expression>")}
            </#if>
            <#if vars.getVariableOpt().isPresent()>
                ${include("definition/Variable.ftl",vars.getVariable())}
            </#if>
            <#if vars_has_next>
                ,
            </#if>
        </#list>
        <#assign controlString=
    </#if>
    ;
    <#if loopControl.isPresentCondition()>
        ${defineHookPoint("<Expression>")}
    </#if>
    ;
    ${defineHookPoint("<Expression>")}
    )
</#if>
<#if genHelper.isForEachControl(loopControl)>
    template<class T>
    for(T ${loopControl.getName()} : ${defineHookPoint("<Expression>")})
</#if>

${include("BlockStatement.ftl",loop.getBlockStatement())}