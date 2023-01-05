package com.jenspersson.mallgroda.proc;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.tools.JavaFileObject;

import com.jenspersson.mallgroda.runtime.Out;

public class WidgetJavaFile {

    //private Writer writer;
    private String name;
    private String pkg;

    public WidgetJavaFile(String fqcname) {
        int lastDot = fqcname.lastIndexOf(".");
        if (lastDot == -1) {
            this.name = fqcname;
        } else if (lastDot == fqcname.length() -1) {
            this.pkg = fqcname;
            this.name = "";
        } else {
            this.pkg = fqcname.substring(0, lastDot);
            this.name = fqcname.substring(lastDot + 1);
        }
    }

    public void write(RootAston ast, Element widgetModel, 
            ProcessingEnvironment processingEnv) throws Exception {
        Filer filer = processingEnv.getFiler();
        JavaFileObject javaFileObject = filer.createSourceFile(pkg + "." + name, widgetModel);
        //this.writer = javaFileObject.openWriter();
        
        Set<Import> imports = ast.imports();
        imports.add(new Import(widgetModel.toString()));

        imports.add(new Import("java.io.Writer"));
        imports.add(new Import("java.io.IOException"));

        imports.add(new Import("com.jenspersson.mallgroda.runtime.Widget"));

        Out out = new Out(javaFileObject.openWriter());

        out.w("package ", pkg, ";\n");
        out.w("\n");
        
        for (Import imp : imports) {
            out.w("import ", imp, ";\n");
        }
        out.w("\n");        
        
        out.w("public class ", name, " implements Widget<", widgetModel.getSimpleName() ,"> {\n");
        out.w("\n");        
        
        Indent indent = new Indent();
        out.w(indent.more(), "@Override\n");
        out.w(indent, "public void render(", widgetModel.getSimpleName(),
             " model, Writer w) throws IOException {\n");
        out.w(indent.more(), "// render file\n");

        for (Fragment frag : ast.flatten()) {
            frag.write(indent, out);
        }

        out.w(indent.less(), "}\n");

        out.w("}");

        out.close();
        
    }

    

    

}
