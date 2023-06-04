package com.jenspersson.mallgroda.test.gen;

import com.jenspersson.mallgroda.test.ExampleWidgetModel;
import com.jenspersson.mallgroda.runtime.Widget;
import java.io.Writer;
import java.io.IOException;

public class ExampleWidget implements Widget<ExampleWidgetModel> {

    @Override
    public void render(ExampleWidgetModel model, Writer w) throws IOException {
      // render file
      w.write("<html>\n");
      w.write("    <head>\n");
      w.write("        <title>");
      w.write("Barbershop");
      w.write("</title>\n");
      w.write("        <style type=\"text/css\"></style>\n");
      w.write("    </head>\n");
      w.write("    <body>\n");
      w.write("        This is text.\n");
      w.write("        <div>");
      w.write(model.shopname());
      w.write("</div>\n");
      w.write("    </body>\n");
      w.write("</html>");
    }
}