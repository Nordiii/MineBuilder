package de.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


public class BlockTest {


    final int repeat = 3;
    final int minExp = 10;
    final int maxExp = 100;
    Block block;

    @BeforeEach
    void init() {
        block = new Block("dirt", repeat, minExp, maxExp);
    }

    @Test
    void testGetExp() {
        for (int i = 1; i < repeat; i++)
            Assertions.assertEquals(0, block.getExp());
    }

    @RepeatedTest(100)
    void testGetExpRangeCorrect() {
        for (int i = 1; i < repeat; i++)
            Assertions.assertEquals(0, block.getExp());
        int exp = block.getExp();
        Assertions.assertTrue(exp <= maxExp);
        Assertions.assertTrue(exp >= minExp);
    }
}
