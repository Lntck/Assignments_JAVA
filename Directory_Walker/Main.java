package Directory_Walker;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        for (int i=0; i<n; i++) {
            if (!fileSystem.parseLine(sc.nextLine().split(" "))) {
                return;
            }
        }
        sc.close();
        fileSystem.printFileSystem();
    }
}