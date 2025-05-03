package Directory_Walker;

import java.math.BigDecimal;
import java.util.Stack;


public class TreePrinter {
    public TreePrinter() {}

    public void printTree(Directory root) {
        Iterator iterator = root.createIterator();
        Stack<Integer> depthStack = new Stack<>();
        Stack<Integer> siblingsStack = new Stack<>();
        boolean[] line = new boolean[100];
 
        siblingsStack.add(root.getChildren().size());
        depthStack.add(0);
        line[0] = root.getChildren().size() == 1;
 
        System.out.print(iterator.next().getName());
 
        while (iterator.hasNext()) {
            Node currentNode = iterator.next();
            boolean isLast = (siblingsStack.peek() == 1);
            int depth = depthStack.peek();
 
            printSpace(depth, line);
 
            if (siblingsStack.peek() == 1) {
                siblingsStack.pop();
                depthStack.pop();
            } else {
                siblingsStack.push(siblingsStack.pop() - 1);
            }
 
            if (currentNode instanceof Directory) {
                int childrenSize = ((Directory) currentNode).getChildren().size();
                if (childrenSize > 0) {
                    siblingsStack.push(childrenSize);
                    depthStack.push(depth + 1);
                }
                System.out.print((isLast ? "└── " : "├── ") + currentNode.getName());
            } else {
                File currentFile = ((File)currentNode);
 
                BigDecimal size = currentFile.getSize().stripTrailingZeros();
 
                System.out.print((isLast ? "└── " : "├── ") + currentFile.getName() + "." + currentFile.getFileProperties().getExtension() + " (" + size.toPlainString() + "KB)");
            }

            line[depth] = isLast; 
        }
    }
 
    private void printSpace(int depth, boolean[] line) {
        System.out.println();
        for (int i = 0; i < depth; i++) {
            if (!line[i]) {
                System.out.print("│   ");
            } else {
                System.out.print("    ");
            }
        }
    }
}

