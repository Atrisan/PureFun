//Data Structure ${ast.Name}

${signature(modulename, sym)}

namespace ${modulename} {

class ${ast.Name} {
public:
    ${ast.Name}() {}
    virtual ~${ast.Name}{}

<#list ast.getVariableList() as variable>
    ${sym.Type} const & get_${variable.Name}() const { return ${variable.Name}; }
</#list>

private:
<#list ast.getVariableList() as variable>
    ${includeArgs("Variable.ftl", variable, sym)}
</#list>
};

}