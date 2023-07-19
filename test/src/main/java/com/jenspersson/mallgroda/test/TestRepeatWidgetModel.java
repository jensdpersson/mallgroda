package com.jenspersson.mallgroda.test;

import java.util.List;
import java.util.Arrays;

import com.jenspersson.mallgroda.runtime.WidgetModel;

@WidgetModel(
    generatee = "com.jenspersson.mallgroda.test.gen.TestRepeatWidget",
    template = "test/src/main/resources/templates/repeat.html"
)
public class TestRepeatWidgetModel {
    
    private final List<Dungeon> dungeons;
    
    public TestRepeatWidgetModel(Dungeon... dungeons) {
        this.dungeons = Arrays.asList(dungeons);
    }
    
    public List<Dungeon> dungeons() {
        return this.dungeons;   
    }
    
    public static class Dungeon {
        private String name = "N/A";
        private String item = "N/A";
        private String boss = "N/A";
        
        public Dungeon name(String name) {
            this.name = name;
            return this;
        }
        public String name() {
            return this.name;
        }
        
        public Dungeon item(String item) {
            this.item = item;
            return this;
        }

        public String item() {
            return this.item;
        }
        
        public Dungeon boss(String boss) {
            this.boss = boss;
            return this;
        }

        public String boss() {
            return this.boss;
        }
    }
    
}