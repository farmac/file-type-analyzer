package pl.farmac.analyzer;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Pass directory as a parameter!");
        }
        Analyzer analyzer = new Analyzer(args[0]);
        analyzer.analyzeFiles();
    }
}
