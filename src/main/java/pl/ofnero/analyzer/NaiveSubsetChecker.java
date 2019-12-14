package pl.ofnero.analyzer;

public class NaiveSubsetChecker implements SubsetChecker {
    @Override
    public boolean isSubset(byte[] patternBytes, byte[] fileBytes) {
        for (int i = 0; i <= fileBytes.length - patternBytes.length; i++) {
            for (int j = 0; j < patternBytes.length; j++) {
                if (patternBytes[j] != fileBytes[i + j]) {
                    break;
                }
                if (j == patternBytes.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
