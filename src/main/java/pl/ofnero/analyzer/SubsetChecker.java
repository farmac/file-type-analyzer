package pl.ofnero.analyzer;

public interface SubsetChecker {
    boolean isSubset(byte[] patternBytes, byte[] fileBytes);
}
