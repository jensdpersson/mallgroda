## MALLGRODA

Type-safe server-side templating

The basic idea is that... 
the Mallgroda annotation processor finds classes provided by you, e.g.

    @WidgetModel(
        template = "path/to/hello.html",
        generatee = "org.example.HelloWidget"
    )
    public class HelloWidgetModel {        
        private String greetee;

        public String greetee() {
            return greetee;
        }

        public HelloWidgetModel(String greetee) {
            this.greetee = greetee;
        }
    }   

...and looks up the template html given in the annotation `template` parameter, for instance ...

    <html>
      <head>
        <title>
            Hello
            <span 
              tal:replace="greetee()">Earth</span>
        </title>
      </head>
      <body>           
        Hello, 
        <span tal:content="greetee()">Earth
        </span>!
      </body>
    </html>

... and generates, still at compile-time, a renderer widget class ...

    public class HelloWidget 
      implements Widget<HelloWidgetModel> {
        public void render(
            HelloWidgetModel model, 
            Writer w) {
          w.write("<html>\n");
          w.write("  <head>\n");
          w.write("    <title>\n");
          w.write("Hello ");
          w.write(model.greeter());
          w.write("    </title>");
          w.write("  </head>\n");
          w.write("  <body>\n");
          w.write("    Hello, ");
          w.write("    <span>");          
          w.write(model.greeter());
          w.write("</span>\n");
          w.write("  </body>\n");
          w.write("</html>");
        }
    }
... which will, at runtime, when given an instance of its model class, such as ...

    HelloWidgetModel model = new 
      HelloWidgetModel("World");       
    new HelloWidget().render(model, someWriterProllyAWebResponse);     

... render a templated fragment ...

    <html>
      <head>
        <title>
            Hello World
        </title>
      </head>
      <body>           
        Hello, <span>World</span>
      </body>
    </html>

The plan is to start with support for a TAL-like syntax for HTML but build
to enable other templating syntaxen, perhaps mustache, and other output formats, perhaps json or sql.

Currently, this is not quite there yet. Check back later