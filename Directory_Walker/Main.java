package Directory_Walker;

import java.util.Scanner;
import java.math.BigDecimal;


public class Main {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();

        try (Scanner sc = new Scanner(System.in)) {
            int n = Integer.parseInt(sc.nextLine());

            for (int i=0; i<n; i++) {
                if (!fileSystem.parseLine(sc.nextLine().split(" "))) {
                    return;
                }
            }
        } catch (NumberFormatException e) {
            return;
        }

        SizeVisitor sizeVisitor = new SizeVisitor();
        fileSystem.getRoot().accept(sizeVisitor);

        BigDecimal totalSize = sizeVisitor.getTotalSize().stripTrailingZeros();

        System.out.println("total: " + totalSize.toPlainString() + "KB");
        fileSystem.printFileSystem();
    }
}