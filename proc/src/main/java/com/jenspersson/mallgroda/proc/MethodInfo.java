package com.jenspersson.mallgroda.proc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MethodInfo {
    String methodName;
    String returnType;
    List<String> returnTypeParameters = new ArrayList<>();
    MethodInfo(String methodName) {
        this.methodName = methodName;
    }
    @Override
    public String toString() {
        return returnType + " " + methodName;
    }
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
    public String getName() {
        return methodName;
    }
    public String getReturnType() {
        StringBuilder buf = new StringBuilder(returnType);
        if (returnTypeParameters.size() > 0) {
            buf.append("<");
            for (Iterator<String> it = returnTypeParameters.iterator(); it.hasNext();) {
                buf.append(it.next());
                if (it.hasNext()) {
                    buf.append(",");
                }
            }
            buf.append(">");
        }
        return buf.toString();
    }
    public void addReturnTypeParameter(String string) {
        returnTypeParameters.add(string);
    }

    public List<String> getReturnTypeParameters() {
       return returnTypeParameters;
    }
    private String returnTypePackage;
    public void setReturnTypePackage(String returnTypePackage) {
        this.returnTypePackage = returnTypePackage;
    }
    public String getReturnTypePackage() {
        return returnTypePackage;
    }
}