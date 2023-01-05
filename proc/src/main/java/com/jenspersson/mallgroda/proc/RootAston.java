package com.jenspersson.mallgroda.proc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RootAston extends Aston {

    private Set<Import> imports = new HashSet<>();

    public Set<Import> imports() {
        return imports;
    }

    public List<Fragment> flatten() {
        List<Fragment> frags = new ArrayList<>();
        collect(f -> frags::add);
        Iterator<Fragment> it = frags.iterator();
        Fragment last = it.next();
        String preindent = "";
        while (it.hasNext()) {
            Fragment frag = it.next();
            if (frag.removeInitialNewline()) {
                last.appendNewline();
                //last.prepend(preindent);
                preindent = frag.removeTrailingIndent();
            } else {
                frag.prepend(preindent);
            }
            last = frag;
        }
        return frags;
    }

}
