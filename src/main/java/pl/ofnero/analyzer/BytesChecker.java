package pl.ofnero.analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BytesChecker {
    private String fileName;
    private String pattern;
    private String fileType;
    private SubsetChecker subsetChecker;
    
    public BytesChecker(String subsetChecker, String fileName, String pattern, String fileType) {
        this.fileName = fileName;
        this.pattern = pattern;
        this.fileType = fileType;
        this.subsetChecker = subsetChecker.equals("--KMP") ? new KMPSubsetChecker() : new NaiveSubsetChecker();
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
        long start = System.currentTimeMillis();
        boolean isSubset = subsetChecker.isSubset(patternBytes, fileBytes);
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println(isSubset ? fileType : "Unknown file type");
        System.out.format("It took %.3f seconds%n", timeElapsed / 1000.0);
    }
    
}

