<#compress>
${signature("variable", "prefix")}
<#assign gh = glex.getGlobalVar("pfHelper")> <#-- GeneratorHelper -->
<#if variable.isPresentExpression() && gh.isAsyncExpression(variable.getExpression())>
<#assign rt = gh.printType(variable.getType())>
    std::shared_future<${rt}> ${variable.getName()} = <#--std::async(std::launch::async, [=](){
        return ${gh.printExpression(variable.getExpression())};
    });--> ${gh.printExpression(variable.getExpression())};
<#else>
    ${gh.printType(variable.getType())} ${prefix}${variable.getName()}<#if variable.isPresentExpression() && gh.isNotContainerType(variable) && gh.isNotClassType(variable) && variable.isPresentExpression()> = ${gh.printExpression(variable.getExpression())}</#if>;
</#if>
</#compress>