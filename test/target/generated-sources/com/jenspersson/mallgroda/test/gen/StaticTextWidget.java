package com.jenspersson.mallgroda.test.gen;

import java.io.Writer;
import com.jenspersson.mallgroda.runtime.Widget;
import java.io.IOException;
import com.jenspersson.mallgroda.test.StaticTextWidgetModel;

public class StaticTextWidget implements Widget<StaticTextWidgetModel> {

    @Override
    public void render(StaticTextWidgetModel model, Writer w) throws IOException {
      // render file
      w.write("<html>");
      w.write("\n    ");
      w.write("<head></head>");
      w.write("\n    ");
      w.write("<body>");
      w.write("\n        Lorem ipsum etc\n    ");
      w.write("</body>");
      w.write("\n");
      w.write("</html>");
    }
}