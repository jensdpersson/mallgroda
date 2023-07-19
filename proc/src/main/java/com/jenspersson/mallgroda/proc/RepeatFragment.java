package com.jenspersson.mallgroda.proc;

import com.jenspersson.mallgroda.runtime.Out;

public class RepeatFragment extends Fragment {

    private String type;
    private String varname;
    private String expr;

    public RepeatFragment(String type, String varname, String expr) {
        this.type = type;
        this.varname = varname;
        this.expr = expr;
    }


    @Override
    public void write(Indent indent, Out out) {
        out.w(indent, "for (", type, " ", varname, " : ", expr, ") {\n");
    }
    
    private String prepend;
    
    @Override
    public void prepend(String prepend) {
        this.prepend = prepend;
    }
    
    @Override
    public String removeTrailingIndent() {
        return this.prepend;
    }
}