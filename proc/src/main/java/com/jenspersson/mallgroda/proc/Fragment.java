package com.jenspersson.mallgroda.proc;

import java.util.ArrayList;
import java.util.List;

import com.jenspersson.mallgroda.proc.TalishTemplateParser.Directive;
import com.jenspersson.mallgroda.runtime.Out;

public class Fragment {

    private List<Fragment> children = new ArrayList<>();
    private String content;

    public void add(Fragment frag) {
        children.add(frag);
    }

    protected List<Fragment> children() {
        return children;
    }

    public void write(Indent indent, Out out) {
        if (content != null) {
            out.w(indent, "w.write(model.", content, ");\n");
        } else {
           for (Fragment child : children) {
                child.write(indent, out);
            }   
        }
    }

    public boolean removeInitialNewline() {
        return false;
    }

    public void appendNewline() {}

    public String removeTrailingIndent() {
        return "";
    }

    public void prepend(String preindent) {}

    public Fragment content(String content) {
        this.content = content;
        return this;
    }

   

}
