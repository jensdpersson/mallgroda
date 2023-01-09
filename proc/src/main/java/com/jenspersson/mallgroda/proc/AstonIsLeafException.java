package com.jenspersson.mallgroda.proc;

public class AstonIsLeafException extends RuntimeException {

    public AstonIsLeafException(String astontype) {
        super("The aston type [" + astontype + "] cannot have children added");
    }

}
