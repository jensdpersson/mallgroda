package com.jenspersson.mallgroda.proc;

import com.jenspersson.mallgroda.runtime.Out;

public class EndBraceFragment extends Fragment {

    @Override
    public void write(Indent indent, Out out) {
        out.w(indent, "}\n");
    }
}