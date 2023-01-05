package com.jenspersson.mallgroda.proc;

public class Indent {

    private static final String INDENT = "  ";
    private static final int LENG = INDENT.length();
    
    private String lineStart;

    public Indent() {
        this.lineStart = INDENT;
    }

    Indent more() {
        this.lineStart += INDENT;
        return this;
    }
    Indent less() {
        this.lineStart = this.lineStart.substring(0, this.lineStart.length() - LENG);
        return this;
    }
    
    @Override
    public String toString() {
        return lineStart;
    }
    
}
