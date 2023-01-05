package com.jenspersson.mallgroda.proc;

public class AstonStack {

    private Frame top;

    public Aston peek() {
        return top.aston;
    }

    public Aston pop() {
        Aston topfrag = top.aston;
        top = top.prev;
        return topfrag;
    }

    public void push(Aston aston) {
        Frame frame = new Frame();
        frame.aston = aston;
        if (top != null) {
            frame.prev = top;
        }
        top = frame;
    }

    private class Frame {
        Aston aston;
        Frame prev;
    }

}
