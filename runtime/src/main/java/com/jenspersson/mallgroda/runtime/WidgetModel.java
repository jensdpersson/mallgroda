package com.jenspersson.mallgroda.runtime;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface WidgetModel {
    public Syntax syntax() default Syntax.TAL;
    public String generatee();
    String template();
}
