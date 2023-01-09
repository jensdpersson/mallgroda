package com.jenspersson.mallgroda.runtime;

public @interface WidgetModel {
    public Syntax syntax() default Syntax.TAL;
    public String generatee();
    String template();
}
