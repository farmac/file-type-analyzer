package pl.farmac.analyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Analyzer {
    private List<File> files;
    private String fileType;
    private SubsetChecker subsetChecker;
    private byte[] patternBytes;
    
    
    public Analyzer(String pattern, String fileType, String foldersPath) throws IOException {
        this.files = getFiles(foldersPath);
        this.patternBytes = pattern.getBytes();
        this.fileType = fileType;
        this.subsetChecker = new KMPSubsetChecker();
    }
    
    public void analyzeFiles() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for(File file: files) {
            executorService.submit(() -> matchesPattern(file));
        }
        executorService.shutdown();
    }
    
    public void matchesPattern(File file) {
        byte[] fileBytes;
        try {
            fileBytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read the file: " + file);
        }
        boolean isSubset = subsetChecker.isSubset(patternBytes, fileBytes);
        System.out.format("%s: %s%n", file.getName(), isSubset ? fileType : "Unknown file type");
    }
    
    private List<File> getFiles(String foldersPath) throws IOException {
        return Files.walk(Paths.get(foldersPath))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
    }
}
