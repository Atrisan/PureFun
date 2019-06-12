/*********************************************************************/
/*        This is a generated C++ source file.                       */
/*        Generated by PureFun Oof.                                  */
/*********************************************************************/

#include <map>
#include <vector>
#include <tupel>
#include <cstdint>
<#assign genHelper = glex.getGlobalVar("PFHelper")> <#-- GeneratorHelper -->
<#assign mods=ast.getDefinitionList()>


/*********************************************************************/
/*        Data Structure inclusions                                  */
/*        Generated by PureFun Oof.                                  */
/*********************************************************************/
<#list Definition in mods>
    <#if isDataStruct(Definition)>
    #include <${Definition.getName()}.hxx>
    </#if>
</#list>



/*********************************************************************/
/*        Global Variables                                           */
/*        Generated by PureFun Oof.                                  */
/*********************************************************************/
<#list variable in mods>
    <#if isGlobalVar(variable)>
    ${include("definition/VariableDefinition.ftl",variable)}
    </#if>
</#list>


/*********************************************************************/
/*        Function Declarations                                      */
/*        Generated by PureFun Oof.                                  */
/*********************************************************************/
<#list function in mods>
    <#if isFunction(function)>
    ${include("definition/Function.ftl",function)}
    </#if>
</#list>