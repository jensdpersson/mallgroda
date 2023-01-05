package com.jenspersson.mallgroda.proc;

public class Import {

    private String fqcn;

    public Import(String fqcn) {
        this.fqcn = fqcn;
    }

    @Override
    public String toString() {
        return fqcn;
    }
}
