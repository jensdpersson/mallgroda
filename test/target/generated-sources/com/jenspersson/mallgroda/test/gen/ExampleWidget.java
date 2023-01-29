package com.jenspersson.mallgroda.test.gen;

import com.jenspersson.mallgroda.runtime.Widget;
import java.io.IOException;
import java.io.Writer;
import com.jenspersson.mallgroda.test.ExampleWidgetModel;

public class ExampleWidget implements Widget<ExampleWidgetModel> {

    @Override
    public void render(ExampleWidgetModel model, Writer w) throws IOException {
      // render file
      w.write("<html>");
      w.write("\n    ");
      w.write("<head>");
      w.write("\n        ");
      w.write("<title>");
      w.write("Barbershop");
      w.write("</title>");
      w.write("\n        ");
      w.write("<style type=\"text/css\"></style>");
      w.write("\n    ");
      w.write("</head>");
      w.write("\n    ");
      w.write("<body>");
      w.write("\n        This is text.\n        ");
      w.write("<div>");
      w.write(model.shopname());
      w.write("</div>");
      w.write("\n    ");
      w.write("</body>");
      w.write("\n");
      w.write("</html>");
    }
}