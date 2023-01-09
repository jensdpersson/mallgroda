package com.jenspersson.mallgroda.proc;

import java.util.EnumMap;

import com.jenspersson.mallgroda.runtime.Syntax;

public class TemplateParserRegistry {

    private static final EnumMap<Syntax, TemplateParser> registry = 
        new EnumMap<Syntax, TemplateParser>(Syntax.class);
    static {
        registry.put(Syntax.TAL, new TalTemplateParser());
    }
    public static TemplateParser lookup(Syntax syntax) {
        return registry.get(syntax);
    }

}
