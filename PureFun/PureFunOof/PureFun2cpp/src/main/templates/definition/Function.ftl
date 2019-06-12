<#assign genHelper = glex.getGlobalVar("PFHelper")> <#-- GeneratorHelper -->
<assign functions=ast.getFunctionList()>
<#list functions as function>
    ${printType(function.getType())} ${function.getName()}(${include("FunctionParameters.ftl",function.getFunctionParameters())})${include("./../statement/BlockStatement.ftl",function.getFunctionBody())}

<\#list>