/*********************************************************************/
/*        This is a generated C++ source file.                       */
/*        Generated by PureFun Oof.                                  */
/*********************************************************************/

#include <map>
#include <vector>
#include <tuple>
#include <cstdint>
#include <string>
#include <iostream>
#include "../../../splcxx/purefun.hxx"
<#assign gh=glex.getGlobalVar("pfHelper")> <#-- GeneratorHelper -->
<#assign mods=ast.getDefinitionList()>


/*********************************************************************/
/*        Data Structure inclusions                                  */
/*        Generated by PureFun Oof.                                  */
/*********************************************************************/
<#list mods as definition>
    <#if gh.isDataStruct(definition)>
#include "includes/${definition.getName()}.hxx"
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