package pl.farmac.analyzer;


import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Pass directory as a parameter!");
        }
        Analyzer analyzer = new Analyzer(args[0]);
        analyzer.analyzeFiles();
    }
}
