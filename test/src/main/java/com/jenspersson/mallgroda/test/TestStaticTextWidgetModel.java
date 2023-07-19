package com.jenspersson.mallgroda.test;

import com.jenspersson.mallgroda.runtime.WidgetModel;

@WidgetModel(
    generatee = "com.jenspersson.mallgroda.test.gen.TestStaticTextWidget",
    template = "test/src/main/resources/templates/static_text.html"
)
public class TestStaticTextWidgetModel {
    public String bollhav() {
        return "apa";
    }
}