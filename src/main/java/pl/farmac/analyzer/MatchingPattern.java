package pl.farmac.analyzer;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MatchingPattern {
    private int priority;
    private byte[] bytes;
    private String info;
    
    public MatchingPattern(int priority, String pattern, String info) {
        this.priority = priority;
        this.bytes = pattern.getBytes();
        this.info = info;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public byte[] getBytes() {
        return bytes;
    }
    
    public String getInfo() {
        return info;
    }
    public static List<MatchingPattern> parsePatterns(String pattern) {
        List<MatchingPattern> matchingPatterns = new ArrayList<>();
    
        List<String> list;
        try {
            list = new ArrayList<>(Files.readAllLines(Paths.get("patterns.db")));
        } catch (IOException e) {
            throw new IllegalStateException("Can not read file: " + pattern);
        }
    
        for (String line : list) {
            String[] temp = line.split(";");
            int priority = Integer.parseInt(temp[0]);
            String pat = temp[1].replace("\"", "");
            String info = temp[2].replace("\"", "");
            matchingPatterns.add(new MatchingPattern(priority, pat, info));
        }
        
        matchingPatterns.sort(Comparator.comparingInt(MatchingPattern::getPriority).reversed());
        return matchingPatterns;
        
    }
}
