package Directory_Walker;

import java.util.Scanner;
import java.math.BigDecimal;

/**
 * Main class for the Directory Walker application.
 * Builds a file system from input, calculates total size, and prints results.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize file system
        FileSystem fileSystem = new FileSystem();

        // Read and parse input
        try (Scanner sc = new Scanner(System.in)) {
            // Read number of input lines
            int n = Integer.parseInt(sc.nextLine());
            for (int i = 0; i < n; i++) {
                // Parse line; exit on failure
                if (!fileSystem.parseLine(sc.nextLine().split(" "))) {
                    return;
                }
            }
        } catch (NumberFormatException e) {
            // Exit on invalid number format
            return;
        }

        // Calculate total size using visitor pattern
        SizeVisitor sizeVisitor = new SizeVisitor();
        fileSystem.getRoot().accept(sizeVisitor);

        // Format total size in KB, removing trailing zeros
        BigDecimal totalSize = sizeVisitor.getTotalSize().stripTrailingZeros();
        System.out.println("total: " + totalSize.toPlainString() + "KB");

        // Print file system structure
        fileSystem.printFileSystem();
    }
}