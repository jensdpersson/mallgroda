package com.jenspersson.mallgroda.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Arrays;

import org.junit.Test;

import com.jenspersson.mallgroda.runtime.Widget;
import com.jenspersson.mallgroda.runtime.WidgetModel;

public class RoundtripTest {
    
    /**
     * @param <T>
     * @param test
     */
    private <T> void doTest(Testcase<T> test) {
        Class<WidgetModel> c = WidgetModel.class;
        WidgetModel widgetModel = test.model.getClass().getAnnotation(c);
        if (widgetModel == null) {
            fail("Anno WidgetModel not found on " + test.model.getClass());
        }
        String generatee = widgetModel.generatee();
        try {
            Class rendererClass = 
                Class.forName(generatee);
            Widget widget = (Widget) rendererClass.newInstance();
            StringWriter w = new StringWriter();
            widget.render(test.model, w);
            String result = w.toString();
            Iterator<String> actual = Arrays.asList(result.split("\n\r?")).iterator();
            List<String> facit = Files.readAllLines(Paths.get(test.facit));
            for(String line : facit) {
                assertEquals(line, actual.next());
            }
        } catch (ClassNotFoundException cfex) {
            fail("Could not find renderer class [" + generatee + "] perhaps it was not generated?" );
        } catch (InstantiationException iex) {
            fail("Could not instantiate renderer class [" + generatee + "]" + iex.getMessage());
        } catch (IllegalAccessException iex) {
            fail("Could not instantiate renderer class [" + generatee + "]" + iex.getMessage());
        } catch (IOException iex) {
            fail(iex.getMessage());
        }
    }

    public static class Testcase<T> {
        T model;
        String facit;    
        Testcase(T model, String facit) {
            this.model = model;
            this.facit = "src/test/resources/templates/" + facit + "/output.html";
        }
    }

    @Test
    public void static_text() {
        doTest(new Testcase(new TestStaticTextWidgetModel(), "static_text"));
    }

    @Test
    public void content() {
        doTest(new Testcase(new TestContentWidgetModel(), "content"));
    }
    
    @Test
    public void repeat() {
        doTest(new Testcase(new TestRepeatWidgetModel(
            new TestRepeatWidgetModel.Dungeon().name("Deku Tree").item("slingshot").boss("Queen Gohma"),
            new TestRepeatWidgetModel.Dungeon().name("Dodongo's Cavern").item("Bomb Bag").boss("King Dodongo"),
            new TestRepeatWidgetModel.Dungeon().name("Lord Jabu-Jabu's Belly").item("Boomerang").boss("Dr. Eggman")
        ), "repeat"));
    }

}
