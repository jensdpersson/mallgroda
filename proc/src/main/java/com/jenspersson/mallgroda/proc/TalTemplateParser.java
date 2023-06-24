package com.jenspersson.mallgroda.proc;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class TalTemplateParser implements TemplateParser {

    private static final XMLInputFactory staxFactory = XMLInputFactory.newFactory();

    @Override
    public Aston parse(InputStream input) throws Exception {
        XMLEventReader xreader = staxFactory.createXMLEventReader(input);
        AstonStack stack = new AstonStack();
        Aston root = new ParentAston();
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
                    HtmlAston xfrag = new HtmlAston(prefix, local);
                    Aston frag = xfrag;
                    Aston top = stack.peek();
                    List<Directive> dires = new ArrayList<>();
                    for(Iterator<Attribute> it = start.getAttributes(); it.hasNext();) {
                        Attribute attr = it.next();
                        String name = attr.getName().getLocalPart();
                        DirectiveEnum dire = null;
                        for (DirectiveEnum denum : DirectiveEnum.values()) {
                            if (denum.name().equals(name)) {
                                dire = denum;
                                break;
                            }
                        }
                        if (dire != null) {
                            dires.add(dire.create(attr.getValue()));
                        } else {
                            xfrag.attr(name, attr.getValue());
                        }
                    }
                    dires.sort(null);
                    for (Directive dire : dires) {
                        frag = dire.apply(frag);
                    }
                    
                    top.add(frag);
                    stack.push(frag);
                break;
                case XMLEvent.ATTRIBUTE: 
                break;
                case XMLEvent.CDATA:  
                case XMLEvent.CHARACTERS:
                    String text = evt.asCharacters().toString();
                    //if (text.startsWith("\n")) {
                        // move newline to last and indent to next
                    //}
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

    enum DirectiveEnum {
        repeat, condition, text, replace, content, include;
        Directive create(String value) {            
            return new Directive(this, value);
        }
    }

    static class Directive implements Comparable<Directive> {
        private DirectiveEnum dire;
        private String value;
        Directive(DirectiveEnum dire, String value) {
            this.dire = dire;
            this.value = value;
        }
        @Override
        public int compareTo(Directive that) {
            return this.dire.compareTo(that.dire);
        }
        public Aston apply(Aston aston) {
            switch (dire) {
                case content: return aston.clear().add(new EvalAston(value));
                case repeat: return aston.clear().add(new RepeatAston(value));
                default: return aston;
            }            
        }
    }

}
