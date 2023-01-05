package com.jenspersson.mallgroda.proc;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class TalishTemplateParser implements TemplateParser {

    private static final XMLInputFactory staxFactory = XMLInputFactory.newFactory();

    @Override
    public RootAston parse(InputStream input) throws Exception {
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
                    XmlAston xfrag = new XmlAston(prefix, local);
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
                    stack.peek().add(new TextFragment(evt.asCharacters().toString()));
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
        public Fragment apply(Fragment frag) {
            switch (dire) {
                case content: return frag.content(value);
                default: return frag;
            }            
        }
    }

}
