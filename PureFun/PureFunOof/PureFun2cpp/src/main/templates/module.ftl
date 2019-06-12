/*********************************************************************/
/*        This is a generated C++ source file.                       */
/*        Generated by PureFun Oof.                                  */
/*********************************************************************/

#include <map>
#include <vector>
#include <tupel>
#include <cstdint>
<#assign gh=glex.getGlobalVar("pfHelper", "TEST")> <#-- GeneratorHelper -->
<#assign mods=ast.getDefinitionList()>


/*********************************************************************/
/*        Data Structure inclusions                                  */
/*        Generated by PureFun Oof.                                  */
/*********************************************************************/
<#list mods as definition>
    <#if gh.isDataStruct(definition)>
//#include <${definition.getName()}.hxx>
        ${includeArgs("definition/DataStructures.ftl", definition, ast.getName())}
    </#if>
</#list>



/*********************************************************************/
/*        Global Variables                                           */
/*        Generated by PureFun Oof.                                  */
/*********************************************************************/
<#list mods as variable>
    <#if gh.isGlobalVar(variable)>
    ${includeArgs("definition/Variable.ftl", variable, "")}
    </#if>
</#list>


/*********************************************************************/
/*        Function Declarations                                      */
/*        Generated by PureFun Oof.                                  */
/*********************************************************************/
<#list mods as function>
    <#if gh.isFunction(function)>
    ${includeArgs("definition/Function.ftl", function)}
    </#if>
</#list>