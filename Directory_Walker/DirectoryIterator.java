package Directory_Walker;

import java.util.ArrayList;
import java.util.Stack;

public class DirectoryIterator implements Iterator {
    private Stack<Node> stack;

    public DirectoryIterator(Directory directory) {
        this.stack = new Stack<>();
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
            ArrayList<Node> Childrens = ((Directory) currentNode).getChildren();

            for(int i=Childrens.size()-1; i>=0; i--) {
                stack.add(Childrens.get(i));
            }
        }
        return currentNode;
    }
}
