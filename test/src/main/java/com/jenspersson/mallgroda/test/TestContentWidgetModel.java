package com.jenspersson.mallgroda.test;

import com.jenspersson.mallgroda.runtime.WidgetModel;

@WidgetModel(
    generatee = "com.jenspersson.mallgroda.test.gen.TestContentWidget",
    template = "test/src/main/resources/templates/content.html"
)
public class TestContentWidgetModel {
    
    private String shopname;
    
    public TestContentWidgetModel(String shopname) {
        this.shopname = shopname;
    }
    
    public String shopname() {
        return this.shopname;
    }
}