package com.jenspersson.mallgroda.test;

import com.jenspersson.mallgroda.runtime.WidgetModel;

@WidgetModel(
    generatee = "com.jenspersson.mallgroda.test.gen.ExampleWidget",
    template = "test/src/main/resources/templates/barber.html"
)
public class ExampleWidgetModel {
    
    public String shopname() {
        return "Example Barber";
    }
}
