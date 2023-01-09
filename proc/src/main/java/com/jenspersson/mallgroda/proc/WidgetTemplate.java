package com.jenspersson.mallgroda.proc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class WidgetTemplate {

   private Set<Import> imports = new HashSet<>();
   private List<Fragment> fragments = new ArrayList<>();

   public Set<Import> getImports() {
    return this.imports;
   }

   public List<Fragment> getFragments() {
    return this.fragments;
   }

   public WidgetTemplate addFragment(Fragment frag) {
    this.fragments.add(frag);
    return this;
   }

   public WidgetTemplate addImport(Import imp) {
    this.imports.add(imp);
    return this;
   }

  public void reline() {    
    Iterator<Fragment> it = fragments.iterator();
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
  }

}
