<#assign gh = glex.getGlobalVar("pfHelper")> <#-- GeneratorHelper -->
${signature("module")}
/*********************************************************************/
/*        This is a generated C++ Header file.                       */
/*        Generated by PureFun Oof.                                  */
/*        Contains the function declarations for the source file     */
/*********************************************************************/
<#assign functions=gh.getFunctionList(module)>
<#list functions as func>
    ${gh.printType(func.getType())} ${func.name}(${includeArgs("definition/FunctionParameters.ftl", func.getFunctionParameters())});

</#list>