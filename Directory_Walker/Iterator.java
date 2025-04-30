package Directory_Walker;

public interface Iterator {
    boolean hasNext(); // Check if there is a next element in the iteration

    Node next(); // Get the next element in the iteration
}
