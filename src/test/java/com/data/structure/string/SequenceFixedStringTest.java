package com.data.structure.string;

import com.data.structure.string.impl.SequenceFixedStringImpl;
import org.junit.Test;

public class SequenceFixedStringTest {
    @Test
    public void testInit(){
        char[] chars = {'a','1','4','b','f'};
        SequenceFixedStringImpl i = new SequenceFixedStringImpl(chars);
        i.strLength();
    }
}
