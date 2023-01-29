package com.jenspersson.mallgroda.proc;

public class EvalAston implements Aston {

    private String code;

    public EvalAston(String code) {
        this.code = code;
    }

    @Override
    public void apply(WidgetTemplate template) {
       template.addFragment(new EvalFragment(code));
    }

    @Override
    public Aston add(Aston child) throws AstonIsLeafException {
        throw new AstonIsLeafException("EvalAston");
    }

    @Override
    public Aston clear() {
       return this;
    }

}
