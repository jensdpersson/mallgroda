package com.jenspersson.mallgroda.proc;

import java.io.FileInputStream;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import static javax.tools.Diagnostic.Kind;

import com.jenspersson.mallgroda.runtime.Syntax;
import com.jenspersson.mallgroda.runtime.WidgetModel;

@SupportedAnnotationTypes("com.jenspersson.mallgroda.runtime.WidgetModel")
public class WidgetModelProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annos, RoundEnvironment round) {
        for (TypeElement anno : annos) {
            Set<? extends Element> annotatedElements 
                = round.getElementsAnnotatedWith(anno);
            log(Kind.NOTE, "Checking ", annotatedElements);
            for (Element elem : annotatedElements) {
                WidgetModel model = elem.getAnnotation(WidgetModel.class);
                Syntax syntax = model.syntax();
                TemplateParser parser = TemplateParserRegistry.lookup(syntax);
                String template = model.template();                
                try {
                    FileInputStream fist = new FileInputStream(template);
                    Aston ast = parser.parse(fist);
                    WidgetTemplate widgetTemplate = new WidgetTemplate();
                    ast.apply(widgetTemplate);
                    widgetTemplate.reline();
                    WidgetJavaFile out = new WidgetJavaFile(model.generatee());
                    out.write(widgetTemplate, elem, processingEnv);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    log(Kind.ERROR, ex);
                }
            }
        }
        return true;
    }

    private void log(Kind kind, Object... msg) {
        StringBuilder buf = new StringBuilder();
        for(Object o: msg) {
            buf.append(String.valueOf(o));
        }
        buf.append("\n");
        processingEnv.getMessager().printMessage(kind, buf.toString());
    }
    
}
