package pl.ofnero.analyzer;


public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Exactly 3 parameters required!");
        }
        BytesChecker bytesChecker = new BytesChecker(args[0], args[1], args[2]);
        bytesChecker.fileMatchesPattern();
    }
}
