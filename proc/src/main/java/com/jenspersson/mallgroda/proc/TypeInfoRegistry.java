package com.jenspersson.mallgroda.proc;

import java.util.Map;
import java.util.HashMap;

import javax.lang.model.type.TypeMirror;

public class TypeInfoRegistry {
    
    private final Map<String, TypeInfo> typeInfos = new HashMap<>();
    private final ModelTypeVisitor modelTypeVisitor; 
    
    TypeInfoRegistry() {
        this.modelTypeVisitor = new ModelTypeVisitor();
    }
    
    public TypeInfo get(TypeMirror typeMirror) {
        String className = typeMirror.getClass().getName();
        TypeInfo typeInfo = typeInfos.get(className);
        if (typeInfo == null) {
            typeInfo = new TypeInfo();
            Void v = typeMirror.accept(modelTypeVisitor, typeInfo);
            typeInfos.put(className, typeInfo);
        }
        return typeInfo;
    }
    
    interface Listener {
        public void onMethod(String signature);
    }
    
}