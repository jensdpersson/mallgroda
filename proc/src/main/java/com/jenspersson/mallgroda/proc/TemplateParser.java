package com.jenspersson.mallgroda.proc;

import java.io.InputStream;

public interface TemplateParser {

    public Aston parse(InputStream input) throws Exception;
}
