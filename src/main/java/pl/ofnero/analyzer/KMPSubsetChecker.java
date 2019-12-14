package pl.ofnero.analyzer;

public class KMPSubsetChecker implements SubsetChecker {
    
    @Override
    public boolean isSubset(byte[] patternBytes, byte[] fileBytes) {
        int[] prefixFunc = prefixFunction(patternBytes);
        int j = 0;
        for (byte fileByte : fileBytes) {
            while (j > 0 && fileByte != patternBytes[j]) {
                j = prefixFunc[j - 1];
            }
        
            if (fileByte == patternBytes[j]) {
                j += 1;
            }
        
            if (j == patternBytes.length) {
                return true;
            }
        }
        return false;
    }
    
    private int[] prefixFunction(byte[] array) {
        int[] prefixFunc = new int[array.length];
        
        for (int i = 1; i < array.length; i++) {
            int j = prefixFunc[i - 1];
            
            while (j > 0 && array[i] != array[j]) {
                j = prefixFunc[j - 1];
            }
            if (array[i] == array[j]) {
                j += 1;
            }
            prefixFunc[i] = j;
        }
        return prefixFunc;
    }
}
