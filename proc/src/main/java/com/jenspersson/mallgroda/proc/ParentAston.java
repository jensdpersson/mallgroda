package com.jenspersson.mallgroda.proc;

import java.util.ArrayList;
import java.util.List;

public class ParentAston implements Aston {
    
    protected List<Aston> children = new ArrayList<>();

    @Override
    public void apply(WidgetTemplate template) {
        children.forEach(c -> c.apply(template));
    }

    public Aston add(Aston child) {
        this.children.add(child);
        return this;
    }

    @Override
    public Aston clear() {
        children.clear();
        return this;
    }    
    
}
