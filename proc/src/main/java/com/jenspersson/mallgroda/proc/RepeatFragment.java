package com.jenspersson.mallgroda.proc;

import com.jenspersson.mallgroda.runtime.Out;

public class RepeatFragment extends Fragment {

    private String code;

    public RepeatFragment(String code) {
        this.code = code;
    }

    @Override
    public void write(Indent indent, Out out) {
        out.w(indent, "w.write(model.", code, ");\n");
    }
}