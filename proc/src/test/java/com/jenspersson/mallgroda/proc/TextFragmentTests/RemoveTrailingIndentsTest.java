package com.jenspersson.mallgroda.proc.TextFragmentTests;

import org.junit.Assert;
import org.junit.Test;

import com.jenspersson.mallgroda.proc.LineFragment;

public class RemoveTrailingIndentsTest {
    
    public void test(String text, String remains, String indent) {
        LineFragment ut = new LineFragment(text);
        Assert.assertEquals("Wrong indent", ut.removeTrailingIndent(), indent);
        Assert.assertEquals("Wrong remainder", ut.text(), remains);
    }

    @Test
    public void someSpaces() {
        test("apa  ", "apa", "  "); 
    }
}
