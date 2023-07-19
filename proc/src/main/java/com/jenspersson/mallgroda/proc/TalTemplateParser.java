package com.jenspersson.mallgroda.proc;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class TalTemplateParser implements TemplateParser {

    private static final XMLInputFactory staxFactory = XMLInputFactory.newFactory();

    @Override
    public WidgetTemplatePopulator parse(InputStream input) throws Exception {
        XMLEventReader xreader = staxFactory.createXMLEventReader(input);
        AstonStack stack = new AstonStack();
        RootAston root = new RootAston();
        stack.push(root);
        while(xreader.hasNext()) {
            XMLEvent evt = xreader.nextEvent();            
            switch (evt.getEventType()) {
                case XMLEvent.START_DOCUMENT:
                    
                break;
                case XMLEvent.START_ELEMENT:                    
                    StartElement start = evt.asStartElement();
                    QName qname = start.getName();
                    String prefix = qname.getPrefix();
                    String local = qname.getLocalPart();
                    
                    List<Aston> astons = new ArrayList<>();
                    
                    HtmlAston html = new HtmlAston(prefix, local);
                    astons.add(html);
                    
                    for(Iterator<Attribute> it = start.getAttributes(); it.hasNext();) {
                        Attribute attr = it.next();
                        String name = attr.getName().getLocalPart();
                        String value = attr.getValue();
                        Aston mapped = map(name, value);
                        if (mapped != null) {
                            astons.add(mapped);
                        } else {
                            html.attr(name, attr.getValue());
                        }
                    }
                    
                    astons.sort(Comparator.comparing(a -> a.directive()));
                    
                    Aston aston = stack.peek();
                    for (Aston next : astons) {
                        aston.add(next);
                        aston = next;
                    }
                    stack.push(aston);
                break;
                case XMLEvent.ATTRIBUTE: 
                break;
                case XMLEvent.CDATA:  
                case XMLEvent.CHARACTERS:
                    String text = evt.asCharacters().toString();
                    stack.peek().add(new TextAston(text));
                break;
                case XMLEvent.COMMENT: break;                
                case XMLEvent.END_ELEMENT: 
                    stack.pop();
                break;
                case XMLEvent.END_DOCUMENT: break;
            }            
        }
        return root;
    }

   
   private static Map<String, Function<String,Aston>> map = new HashMap<>();
   static {
       map.put(Directive.content.name(), EvalAston::new);
       map.put(Directive.repeat.name(), RepeatAston::new);
   }
   
    
    Aston map(String name, String value) {
        Function<String,Aston> astor = map.get(name);
        if (astor != null) {
            return astor.apply(value);
        } else {
            return null;
        }
    }

}
