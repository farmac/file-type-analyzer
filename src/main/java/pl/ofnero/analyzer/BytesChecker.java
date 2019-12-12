package pl.ofnero.analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BytesChecker {
    private String fileName;
    private String pattern;
    private String fileType;
    
    public BytesChecker() {}
    
    public BytesChecker(String fileName, String pattern, String fileType) {
        this.fileName = fileName;
        this.pattern = pattern;
        this.fileType = fileType;
    }
    
    public void fileMatchesPattern() {
        Path fileLocation = Paths.get(fileName);
        byte[] patternBytes = pattern.getBytes();
        byte[] fileBytes;
        try {
            fileBytes = Files.readAllBytes(fileLocation);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read the file: " + fileName);
        }
        System.out.println(isSubsetNaive(patternBytes, fileBytes) ? fileType : "Unknown file type");
    }
    
    public boolean isSubsetNaive(byte[] patternBytes, byte[] fileBytes) {
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

