## MALLGRODA

Type-safe server-side templating

The Mallgroda annotation processor finds classes provided by you:

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
