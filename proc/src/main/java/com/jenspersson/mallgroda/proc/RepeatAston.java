package com.jenspersson.mallgroda.proc;

public class RepeatAston extends ParentAston {

    private String varname;
    private String expr;

    public RepeatAston(String value) {
        int space = value.indexOf(" ");
        if (space == -1) {
            this.varname = "it";
            this.expr = value;
        } else {
            this.varname = value.substring(0, space);
            this.expr = value.substring(space);
        }
    }

    @Override
    public void apply(WidgetTemplate template) {
       template.addFragment(new RepeatFragment("Object ", varname, expr));
       super.apply(template);
       template.addFragment(new EndBraceFragment());
    }

}