package com.example.simplecalculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {  //this code in emulator ! not views to user !

    @Test
    public void perform() {  //test different operations
        assertEquals(4, 2 + 2);
    }
}