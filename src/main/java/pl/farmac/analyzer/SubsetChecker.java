package pl.farmac.analyzer;

public interface SubsetChecker {
    boolean isSubset(byte[] patternBytes, byte[] fileBytes);
}
