package pl.ofnero.analyzer;


public class Main {
    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Exactly 4 parameters required!");
        }
        BytesChecker bytesChecker = new BytesChecker(args[0], args[1], args[2], args[3]);
        bytesChecker.fileMatchesPattern();
    }
}
