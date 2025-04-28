package Directory_Walker;

import java.math.BigDecimal;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        // Parsing Data
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        try {
            for (int i=0; i<n; i++) {
                String[] line = sc.nextLine().split(" ");
                int curs = 0;
                String command = line[curs++];

                if (command.equals("DIR")) {
                    int id = Integer.parseInt(line[curs++]);
                    int parent_id = -1;
                    if (line[curs].matches("\\d+")) { parent_id = Integer.parseInt(line[curs++]);}
                    String name = line[curs++];

                    System.out.println(id + " " + parent_id + " " + name);
                } else if (command.equals("FILE")) {
                    int id = Integer.parseInt(line[curs++]);
                    boolean isReadOnly = line[curs++].equals("T");
                    String owner = line[curs++];
                    String group = line[curs++];
                    BigDecimal size = new BigDecimal(line[curs++]);
                    String file = line[curs++];
                    String name = file.replaceFirst("[.][^.]+$", "");
                    String extension = file.substring(file.lastIndexOf('.') + 1);

                    System.out.println(id + " " + isReadOnly + " " + size + " " + name + " " + extension);
                } else {
                    System.out.println("Invalid command");
                    return;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid number of elements in command");
            return;
        }
    }
}