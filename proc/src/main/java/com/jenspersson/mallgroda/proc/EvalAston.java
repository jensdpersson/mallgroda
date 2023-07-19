package com.jenspersson.mallgroda.proc;

public class EvalAston implements Aston {

    private String code;

    public EvalAston(String code) {
        this.code = code;
    }

    @Override
    public void populate(WidgetTemplate template, ModelStack modelStack) {
       template.addFragment(new EvalFragment(code));
    }

    @Override
    public Aston add(Aston child) throws AstonIsLeafException {
       // throw new AstonIsLeafException("EvalAston");
       return this;
    }

    @Override
    public Aston clear() {
       return this;
    }
    
    @Override
    public Directive directive() {
        return Directive.content;
    }

}
