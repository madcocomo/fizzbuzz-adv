package io.tdd.kata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FizzBuzzTest {
    @Test
    public void testExample() {
        assertEquals(1, 1);
    }


    @Test
    public void catch_exception_or_test() {
        try {
            throw new IllegalArgumentException("aaa");

        } catch (IllegalArgumentException | NullPointerException e) {

        }
    }
}
