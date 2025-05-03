package Directory_Walker;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * Utility class to print the file system tree structure.
 * Uses an iterator for depth-first traversal and formats output with ASCII characters.
 */
public class TreePrinter {
    /**
     * Constructs a TreePrinter instance.
     */
    public TreePrinter() {}

    /**
     * Prints the file system tree starting from the root directory.
     * Displays directories and files with sizes and extensions in a hierarchical format.
     * @param root the root directory to print
     */
    public void printTree(Directory root) {
        // Initialize iterator for depth-first traversal
        Iterator iterator = root.createIterator();
        // Stack to track depth of nodes
        Stack<Integer> depthStack = new Stack<>();
        // Stack to track remaining siblings at each level
        Stack<Integer> siblingsStack = new Stack<>();
        // Array to track if a node is the last sibling at its depth
        boolean[] line = new boolean[100];

        // Initialize stacks and line array for root
        siblingsStack.add(root.getChildren().size());
        depthStack.add(0);
        line[0] = root.getChildren().size() == 1;

        // Print root node name
        System.out.print(iterator.next().getName());

        // Traverse remaining nodes
        while (iterator.hasNext()) {
            Node currentNode = iterator.next();
            boolean isLast = (siblingsStack.peek() == 1);
            int depth = depthStack.peek();

            // Print indentation and connection lines
            printSpace(depth, line);

            // Update stacks for sibling and depth tracking
            if (siblingsStack.peek() == 1) {
                siblingsStack.pop();
                depthStack.pop();
            } else {
                siblingsStack.push(siblingsStack.pop() - 1);
            }

            // Handle directory nodes
            if (currentNode instanceof Directory) {
                int childrenSize = ((Directory) currentNode).getChildren().size();
                if (childrenSize > 0) {
                    siblingsStack.push(childrenSize);
                    depthStack.push(depth + 1);
                }
                System.out.print((isLast ? "└── " : "├── ") + currentNode.getName());
            } else {
                // Handle file nodes
                File currentFile = ((File) currentNode);
                BigDecimal size = currentFile.getSize().stripTrailingZeros();

                // Print file with extension and size
                System.out.print((isLast ? "└── " : "├── ") + currentFile.getName() + "." + currentFile.getFileProperties().getExtension() + " (" + size.toPlainString() + "KB)");
            }

            // Mark if current node is the last sibling at its depth
            line[depth] = isLast;
        }
    }

    /**
     * Prints indentation and connection lines for the current depth.
     * @param depth current depth in the tree
     * @param line array tracking last siblings at each depth
     */
    private void printSpace(int depth, boolean[] line) {
        System.out.println();
        // Print indentation with vertical lines or spaces
        for (int i = 0; i < depth; i++) {
            if (!line[i]) {
                System.out.print("│   ");
            } else {
                System.out.print("    ");
            }
        }
    }
}