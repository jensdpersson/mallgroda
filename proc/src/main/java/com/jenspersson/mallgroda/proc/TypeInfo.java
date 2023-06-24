package com.jenspersson.mallgroda.proc;

import java.util.List;
import java.util.ArrayList;

public class TypeInfo {
    
    private String name;
    private List<MethodInfo> methods = new ArrayList<>();
    
    void addMethod(String meth) {
        this.methods.add(new MethodInfo(meth));
    }
    
    class MethodInfo {
        String signature;
        MethodInfo(String signature) {
            this.signature = signature;
        }
        @Override
        public String toString() {
            return signature;
        }
    }
    
    @Override
    public String toString() {
        return name + ":" + methods;
    }
    
}