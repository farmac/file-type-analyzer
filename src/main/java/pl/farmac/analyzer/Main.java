package pl.farmac.analyzer;


import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Exactly 2 parameters required!");
        }
        Analyzer analyzer = new Analyzer(args[0], args[1]);
        analyzer.analyzeFiles();
    }
}
