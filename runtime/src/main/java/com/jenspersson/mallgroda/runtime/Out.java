package com.jenspersson.mallgroda.runtime;

import java.io.IOException;
import java.io.Writer;

public class Out {
    
    private Writer writer;

    public Out(Writer writer) {
        this.writer = writer;
    }

    public void w(Object... objects) {
        try {
            for(Object o : objects) {
                writer.write(String.valueOf(o));
            }
        } catch (RuntimeException rex) {
            throw rex;
        } catch (IOException iex) {
            throw new RuntimeException(iex);
        }
    }

    public void close() throws IOException {
        this.writer.flush();
        this.writer.close();
    }

}
