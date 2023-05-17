package book.toby1.learningtest;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReflectionTest {
    @Test
    public void invokeMethod() throws Exception {
        String name = "pposik";
        assertEquals(6, name.length());

        Method lengthMethod = String.class.getMethod("length");
        assertEquals(6, lengthMethod.invoke(name));

        assertEquals('p', name.charAt(0));

        Method charAtMethod = String.class.getMethod("charAt", int.class);
        assertEquals(name.charAt(0), charAtMethod.invoke(name, 0));
    }
}
