package com.codestates;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class AssumptionTest {
    @DisplayName("Assumption Test")
    @Test
    public void assumptionTest() {
        assumeTrue(System.getProperty("os.name").startsWith("Windows"));
//        assumeTrue(System.getProperty("os.name").startsWith("Linux"));
        System.out.println("execute?");
        assertTrue(processOnlyWindowsTask());
        System.out.println("proceessOnly");
    }

    private boolean processOnlyWindowsTask() {
        return true;
//        return false;
    }
}
