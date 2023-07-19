package com.jenspersson.mallgroda.proc;

import java.util.List;

public class RepeatAston extends ParentAston {

    private String varname;
    private Invocation invocation;

    public RepeatAston(String value) {
        int space = value.indexOf(" ");
        if (space == -1) {
            this.varname = "it";
            this.invocation = Invocation.parse(value);
        } else {
            this.varname = value.substring(0, space);
            this.invocation = Invocation.parse(value.substring(space));
        }
    }

    @Override
    public void populate(WidgetTemplate template, ModelStack modelStack) {
       TypeInfo typeInfo = modelStack.findType(invocation.getVarname());   
       MethodInfo methodInfo = typeInfo.findMethod(invocation.getMethodName());
       //String returnType = methodInfo.getReturnType();
       List<String> returnTypeParameters = methodInfo.getReturnTypeParameters();
       if (returnTypeParameters.size() != 1) {
            throw new RuntimeException("Cannot repeat over data with more or less than one type parameter"
            + returnTypeParameters);
       }
       String iteratorType = returnTypeParameters.get(0);
       template.addImport(new Import(methodInfo.getReturnTypePackage() + "." + iteratorType));
       TypeInfo subType = null; //TODO lookup
       template.addFragment(new RepeatFragment(iteratorType, varname, invocation.format()));
       ModelStack substack = new ModelStack(varname, subType, modelStack);
       super.populate(template, substack);
       template.addFragment(new EndBraceFragment());
    }
    
    
    @Override
    public Directive directive() {
        return Directive.repeat;
    }

}