package com.jenspersson.mallgroda.proc;

import java.util.List;

public class ModelStack {
    
    private String key;
    private ModelStack parent;
    private TypeInfo typeInfo;
    
    ModelStack(String key, TypeInfo typeInfo, ModelStack parent) {
        this.key = key;
        this.typeInfo = typeInfo;
        this.parent = parent;
    }
}