package com.jenspersson.mallgroda.proc;

import com.jenspersson.mallgroda.runtime.Out;

public class EndBraceFragment extends Fragment {

    private Fragment prev;

    @Override
    public void prev(Fragment prev) {
        this.prev = prev;
    }

    @Override
    public void write(Indent indent, Out out) {
        out.w(indent, "}\n");
    }
    
    @Override
    public void appendNewline() {
        prev.appendNewline();
    }
}