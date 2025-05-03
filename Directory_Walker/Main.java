package Directory_Walker;

import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;


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
            sc.close();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        SizeVisitor sizeVisitor = new SizeVisitor();
        fileSystem.getRoot().accept(sizeVisitor);

        BigDecimal totalSize = sizeVisitor.getTotalSize();
        totalSize = totalSize.setScale((totalSize.scale() <= 0) ? 0 : 1, RoundingMode.HALF_UP).stripTrailingZeros();


        System.out.println("total: " + totalSize + "KB");
        fileSystem.printFileSystem();
    }
}