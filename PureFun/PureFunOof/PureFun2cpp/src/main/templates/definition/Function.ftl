<#assign genHelper = glex.getGlobalVar("PFHelper")> <#-- GeneratorHelper -->
<assign function=ast.get()>
${printType(function.getType())} ${function.getName()}(${include("FunctionParameters.ftl",function.getFunctionParameters())})${include("./../statement/BlockStatement.ftl",function.getFunctionBody())}