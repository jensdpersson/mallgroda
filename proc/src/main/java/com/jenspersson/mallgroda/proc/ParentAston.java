package com.jenspersson.mallgroda.proc;

import java.util.ArrayList;
import java.util.List;

public abstract class ParentAston implements Aston {
    
    protected List<Aston> children = new ArrayList<>();

    @Override
    public void populate(WidgetTemplate template, ModelStack modelStack) {
        children.forEach(c -> c.populate(template, modelStack));
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
