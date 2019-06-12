<#assign loop=ast.get()>
while ( ${defineHookPoint("<Expression>")} ) ${include("BlockStatement.ftl",loop.getBlockStatement())}