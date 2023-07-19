package com.jenspersson.mallgroda.proc;

import java.io.InputStream;

public interface TemplateParser {

    public WidgetTemplatePopulator parse(InputStream input) throws Exception;
}
