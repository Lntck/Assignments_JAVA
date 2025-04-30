package Directory_Walker;

import java.util.ArrayDeque;

public class DirectoryIterator implements Iterator {
    private ArrayDeque<Node> stack;

    public DirectoryIterator(Directory directory) {
        this.stack = new ArrayDeque<>();
        this.stack.push(directory);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Node next() {
        if (!hasNext()) {
            return null;
        }

        Node currentNode = stack.pop();
        if (currentNode instanceof Directory) {
            Directory currentDirectory = (Directory) currentNode;
            stack.addAll(currentDirectory.getDirectories());
            stack.addAll(currentDirectory.getFiles());
        }
        return currentNode;
    }
}
