package pl.farmac.analyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class BytesChecker {
    private List<File> files;
    private String fileType;
    private SubsetChecker subsetChecker;
    private byte[] patternBytes;
    
    
    public BytesChecker(String pattern, String fileType, String foldersPath) throws IOException {
        this.files = getFiles(foldersPath);
        this.patternBytes = pattern.getBytes();
        this.fileType = fileType;
        this.subsetChecker = new KMPSubsetChecker();
    }
    
    private List<File> getFiles(String foldersPath) throws IOException {
        return Files.walk(Paths.get(foldersPath))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
    }
    
    public void matchesPattern(File file) {
        byte[] fileBytes;
        try {
            fileBytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read the file: " + file);
        }
        boolean isSubset = subsetChecker.isSubset(patternBytes, fileBytes);
        System.out.println(isSubset ? fileType : "Unknown file type");
    }
}
