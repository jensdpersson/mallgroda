package com.jenspersson.mallgroda.proc;

import java.io.InputStream;

public interface TemplateParser {

    public RootAston parse(InputStream input) throws Exception;
}
