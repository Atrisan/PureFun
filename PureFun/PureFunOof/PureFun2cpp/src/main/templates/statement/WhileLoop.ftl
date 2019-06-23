<#assign loop=ast.get()>
while ( ${gh.printExpression(loop.getCondition())} ) ${include("BlockStatement.ftl",loop.getBlockStatement())}