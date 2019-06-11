<assign functions=ast.getFunctionList()>
<#list functions as function>
    ${function.getType()} ${function.getName()}(${include("FunctionParameters.ftl",function.getFunctionParameters())})${include("./../statement/BlockStatement.ftl",function.getFunctionBody())}

<\#list>