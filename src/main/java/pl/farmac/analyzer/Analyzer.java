package pl.farmac.analyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Analyzer {
    private List<File> files;
    private String fileType;
    private SubsetChecker subsetChecker;
    private byte[] patternBytes;
    
    public Analyzer(String foldersPath, String pattern, String fileType) throws IOException {
        this.files = getFiles(foldersPath);
        this.patternBytes = pattern.getBytes();
        this.fileType = fileType;
        this.subsetChecker = new KMPSubsetChecker();
    }
    
    
    public void analyzeFiles() throws InterruptedException {
        List<Callable<String>> callableList = new ArrayList<>();
        files.forEach(file -> callableList.add(() -> matchesPattern(file)));
        
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<String>> output = executorService.invokeAll(callableList);
        executorService.shutdown();
        
        List<String> collect = output.stream()
                .map(i -> {
                    try {
                        return i.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new IllegalThreadStateException("Thread has been interrupted or some other problems occurred.");
                    }
                })
                .collect(Collectors.toList());
        
        collect.forEach(System.out::println);
        
        
    }
    
    private String matchesPattern(File file) {
        byte[] fileBytes;
        try {
            fileBytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read the file: " + file);
        }
        boolean isSubset = subsetChecker.isSubset(patternBytes, fileBytes);
        return String.format("%s: %s", file.getName(), isSubset ? fileType : "Unknown file type");
    }
    
    private List<File> getFiles(String foldersPath) throws IOException {
        return Files.walk(Paths.get(foldersPath))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .sorted()
                .collect(Collectors.toList());
    }
}
