package Directory_Walker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;


public class TreePrinter {
    public TreePrinter() {}

    public void printTree(Directory root) {
        Iterator iterator = root.createIterator();
        Stack<Integer> depthStack = new Stack<>();
        Stack<Integer> siblingsStack = new Stack<>();

        siblingsStack.add(root.getChildren().size());
        depthStack.add(0);

        System.out.print(iterator.next().getName());

        while (iterator.hasNext()) {
            Node currentNode = iterator.next();
            boolean isLast = (siblingsStack.peek() == 1);
            int depth = depthStack.peek();

            printSpace(depthStack.peek());

            if (siblingsStack.peek() == 1) {
                siblingsStack.pop();
                if (!depthStack.isEmpty()) depthStack.pop();
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

                BigDecimal size = currentFile.getSize();
                size = size.setScale((size.scale() <= 0) ? 0 : 1, RoundingMode.HALF_UP).stripTrailingZeros();

                System.out.print((isLast ? "└── " : "├── ") + currentFile.getName() + "." + currentFile.getFileProperties().getExtension() + " (" + size + "KB)");
            }
        }
    }

    public void printSpace(int depth) {
        System.out.println();
        if (depth != 0) {
            System.out.print("│");
            for (int i=0; i<depth; i++) {
                System.out.print("   ");
            }
        }
    }
}
