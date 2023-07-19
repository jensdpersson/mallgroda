package com.jenspersson.mallgroda.proc;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class TypeInfo {
    
    private String name;
    private List<MethodInfo> methods = new ArrayList<>();
    
    public TypeInfo(String name) {
        this.name = name;
    }
    
    MethodInfo addMethod(MethodInfo methodInfo) {
        this.methods.add(methodInfo);
        return methodInfo;
    }
    
    @Override
    public String toString() {
        return name + ":" + methods;
    }

    public MethodInfo findMethod(String methodName) {
        for (MethodInfo methodInfo : methods) {
            if (methodInfo.getName().equals(methodName)) {
                return methodInfo;
            }
        }
        throw new RuntimeException("Cannot find method [" + methodName + "] in ["+this.name+"]");
    }
    
}