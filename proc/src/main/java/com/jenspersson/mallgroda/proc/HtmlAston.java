package com.jenspersson.mallgroda.proc;

public class HtmlAston extends ParentAston {

    String name;
    StringBuilder attrs = new StringBuilder();

    HtmlAston(String prefix, String localName) {        
        this.name = prefix.isEmpty() ? localName : prefix + ":" + localName;
    }

    @Override
    public void apply(WidgetTemplate template) {
        if (children.isEmpty()) {
            if (Html5.isVoid(name)) {
                template.addFragment(new LineFragment("<" + name + attrs + "/>"));
            } else {
                template.addFragment(new LineFragment("<" + name + attrs + ">&nbsp;</" + name + ">"));
            }
        } else {
            template.addFragment(new LineFragment("<" + name + attrs + ">"));
            children.forEach(c -> c.apply(template));
            template.addFragment(new LineFragment("</" + name + ">"));
        }  
    }

    public void attr(String name2, String value) {
        this.attrs
            .append(" ")
            .append(name2)
            .append("=\\\"")
            .append(value.replace("\"", "&quot;"))
            .append("\\\"");
    }

}
