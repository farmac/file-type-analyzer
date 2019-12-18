package pl.farmac.analyzer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NaiveSubsetCheckerTest {
    private SubsetChecker subsetChecker = new NaiveSubsetChecker();
    
    @Test
    void shouldTellThatFirstIsSubsetOfSecond() {
        byte[] pattern = {1, 2, 4};
        byte[] file = {16, 12, -51, 12, 1, 5, 4, 2, 1, 2, 4};
        assertTrue(subsetChecker.isSubset(pattern, file));
    }
    
    @Test
    void shouldTellThatFirstIsNotSubsetOfSecond() {
        byte[] pattern = {1, 2, 4};
        byte[] file = {};
        byte[] file2 = {1, 4, 2};
        assertFalse(subsetChecker.isSubset(pattern, file));
        assertFalse(subsetChecker.isSubset(pattern, file2));
    }
}