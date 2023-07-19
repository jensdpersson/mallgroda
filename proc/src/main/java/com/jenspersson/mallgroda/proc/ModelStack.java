package com.jenspersson.mallgroda.proc;

public class ModelStack {
    
    private String key;
    private ModelStack parent;
    private TypeInfo typeInfo;
    
    ModelStack(String key, TypeInfo typeInfo, ModelStack parent) {
        this.key = key;
        this.typeInfo = typeInfo;
        this.parent = parent;
    }

    public TypeInfo findType(String varname) {
        if (varname.equals(this.key)) {
            return this.typeInfo;
        }
        if (parent == null) {
            throw new RuntimeException("Cannot find var [" + varname + "] in context");
        };
        return parent.findType(varname);
    }

    
}