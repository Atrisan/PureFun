<#assign gh = glex.getGlobalVar("pfHelper")> <#-- GeneratorHelper -->
${signature("func")}
${gh.printType(func.getType())} ${func.name}(${includeArgs("definition/FunctionParameters.ftl", func.getFunctionParameters())})${includeArgs("statement/BlockStatement.ftl", func.getBlockStatement())}