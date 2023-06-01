import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class FileDifferenceChecker {

    /**
     * Class to store the mismatched line number, corresponding lines from first and second file
     */
    static class Tuple {
        private final int lineNumber;
        private final String line1;
        private final String line2;

        public Tuple(final int lineNumber, final String line1, final String line2) {
            this.lineNumber = lineNumber;
            this.line1 = line1;
            this.line2 = line2;
        }

        public int getLineNumber() {
            return this.lineNumber;
        }

        public String getLine1() {
            return this.line1;
        }

        public String getLine2() {
            return this.line2;
        }
    }

    private final String firstFileName;
    private final String secondFileName;

    public FileDifferenceChecker(final String firstFileName, final String secondFileName) {
        this.firstFileName = firstFileName;
        this.secondFileName = secondFileName;
    }

    /**
     * Prints line number and mismatched lines from the two files
     *
     * @param tuple An object of class Tuple
     */
    private void printDifference(final Tuple tuple) {
        System.out.println("Difference on Line: " + tuple.getLineNumber());
        System.out.println("First File: " + tuple.getLine1());
        System.out.println("Second File: " + tuple.getLine2());
        System.out.println();
    }

    /**
     * Compares the two files and prints the difference
     */
    public void compare() throws IOException {
        try (final BufferedReader firstFileReader = new BufferedReader(new FileReader(this.firstFileName));
             final BufferedReader secondFileReader = new BufferedReader(new FileReader(this.secondFileName))) {
            String firstLine = firstFileReader.readLine();
            String secondLine = secondFileReader.readLine();
            int lineNumber = 1;

            while (firstLine != null && secondLine != null) {
                if (firstLine.length() != secondLine.length()) {
                    printDifference(new Tuple(lineNumber, firstLine, secondLine));
                } else {
                    if (!firstLine.equals(secondLine)) {
                        printDifference(new Tuple(lineNumber, firstLine, secondLine));
                    }
                }

                firstLine = firstFileReader.readLine();
                secondLine = secondFileReader.readLine();
                lineNumber++;
            }

            if (firstLine != null) {
                while (firstLine != null) {
                    printDifference(new Tuple(lineNumber, firstLine, ""));
                    firstLine = firstFileReader.readLine();
                    lineNumber++;
                }
            } else if (secondLine != null) {
                while (secondLine != null) {
                    printDifference(new Tuple(lineNumber, "", secondLine));
                    secondLine = secondFileReader.readLine();
                    lineNumber++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        FileDifferenceChecker fileDifferenceChecker = new FileDifferenceChecker("src/file1.txt", "src/file2.txt");
        fileDifferenceChecker.compare();

        System.out.println("Time taken for comparison: " + String.valueOf((System.nanoTime() - start) * Math.pow(10, 6)) + "ms");
    }

}
