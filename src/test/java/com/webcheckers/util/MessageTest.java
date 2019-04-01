package com.webcheckers.util;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;

@Tag("Utility")
public class MessageTest {
    @Test
    public void testGetText(){
        final String testString = "testString";
        final Message CuT = Message.info(testString);

        assertEquals(testString, CuT.getText());
    }

    @Test
    public void testGetType(){
        final String testString = "testString";
        final Message CuT = Message.info(testString);

        assertEquals(Message.Type.INFO, CuT.getType());
    }

    @Test
    public void testIsSuccessful_Success(){
        final String testString = "testString";
        final Message CuT = Message.info(testString);

        assertTrue(CuT.isSuccessful());
    }

    @Test
    public void testIsSuccessful_Unsuccessful(){
        final String testString = "testString";
        final Message CuT = Message.error(testString);

        assertFalse(CuT.isSuccessful());
    }
}
