package com.jenspersson.mallgroda.proc;

import java.util.Iterator;
import java.util.List;

import com.jenspersson.mallgroda.runtime.Out;

public class XmlAston extends Aston {

    String name;
    String preindent = "";
    StringBuilder attrs = new StringBuilder();

    XmlAston(String prefix, String localName) {        
        this.name = prefix.isEmpty() ? localName : prefix + ":" + localName;
    }

    @Override
    public void write(Indent indent, Out out) {     
        List<Fragment> children = children();
        if (children.isEmpty()) {
            if (Html5.isVoid(name)) {
                children.add(new TextFragment("<" + name + attrs + "/>"));
            } else {
                children.add(new TextFragment("<" + name + attrs + "></" + name + ">"));
            }
        } else {
            children().add(0, new TextFragment(preindent + "<" + name + attrs + ">"));
            children().add(new TextFragment("</" + name + ">"));
           
        }    
        super.write(indent, out);
    }

    @Override
    public void prepend(String preindent) {
        this.preindent = preindent;
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
