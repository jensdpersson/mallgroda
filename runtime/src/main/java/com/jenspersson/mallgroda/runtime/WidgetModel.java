package com.jenspersson.mallgroda.runtime;

public @interface WidgetModel {
    public Syntax syntax() default Syntax.TALISH;
    public String generatee();
    String template();
}
