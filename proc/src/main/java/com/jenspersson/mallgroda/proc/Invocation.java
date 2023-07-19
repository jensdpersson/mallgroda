package com.jenspersson.mallgroda.proc;

public class Invocation {

    private final String varname;
    private final String methodName;

    private Invocation(String varname, String methodName) {
        this.varname = varname;
        this.methodName = methodName;
    }

    static Invocation parse(String expr) {
        String[] parts = expr.split("\\.");
        if (parts.length != 2 || !expr.endsWith("()")) {
            throw new RuntimeException("Cannot match ["+expr+"] to pattern [$TOKEN.$METHOD()] as invocation");
        }
        String varname = parts[0].trim();
        String methodName = parts[1].substring(0, parts[1].length() - 2).trim();
        return new Invocation(varname, methodName);
    }

    public String getVarname() {
        return varname;
    }

    public String getMethodName() {
        return methodName;
    }

    public String format() {
        return varname + "." + methodName + "()";
    }


}
