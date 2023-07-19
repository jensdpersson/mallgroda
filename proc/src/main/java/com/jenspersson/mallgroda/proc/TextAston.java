package com.jenspersson.mallgroda.proc;

public class TextAston implements Aston {

    private String text;

    public TextAston(String text) {
        this.text = text;
    }

    @Override
    public void populate(WidgetTemplate template, ModelStack modelStack) {
     template.addFragment(new LineFragment(text));   
    }

    @Override
    public Aston add(Aston child) throws AstonIsLeafException {
        throw new AstonIsLeafException("TextAston");
    }

    @Override
    public Aston clear() {        
        return this;
    }
    
    
    @Override
    public Directive directive() {
        return Directive.text;
    }

}
