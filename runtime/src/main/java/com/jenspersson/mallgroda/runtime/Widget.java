package com.jenspersson.mallgroda.runtime;

import java.io.IOException;
import java.io.Writer;

public interface Widget<MODEL> {
    
    public void render(MODEL model, Writer w) throws IOException;

}
