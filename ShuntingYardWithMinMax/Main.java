// Rushan Shafeev
// Shunting-yard algorithm with min/max
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ReversePolishNotation(sc.nextLine());
    }

    public static boolean isDigit(String str) {
        return str != null && str.matches("[0-9]");
    }

    public static int eval(int a, int b, String ev) {
        return switch (ev) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            case "min" -> (a < b) ? a : b;
            case "max" -> (a > b) ? a : b;
            default -> 0;
        };
    }

    public static int shuttingYard(String[] words) {
        ArrayStack<String> Stack = new ArrayStack<>();
        ArrayStack<Integer> StackForDigits = new ArrayStack<>();
        int i = 0;
        while (i < words.length) {
            if (isDigit(words[i])) {
                StackForDigits.push(Integer.parseInt(words[i++]));
            } else if (words[i].matches("min|max")) {
                Stack.push(words[i++]);
                Stack.push(words[i++]);
            } else if (words[i].equals("(")) {
                Stack.push(words[i++]);
            } else if (words[i].equals(")")) {
                while (!Stack.isEmpty() && !Stack.peek().equals("(")) {
                    int b = StackForDigits.pop();
                    int a = StackForDigits.pop();
                    StackForDigits.push(eval(a, b, Stack.pop()));
                }
                if (!Stack.isEmpty()) {
                    Stack.pop();
                    if (!Stack.isEmpty() && (Stack.peek().matches("min|max"))) {
                        int b = StackForDigits.pop();
                        int a = StackForDigits.pop();
                        StackForDigits.push(eval(a, b, Stack.pop()));
                    }
                }
                i++;
            } else if (words[i].equals(",")) {
                while (!Stack.isEmpty() && !Stack.peek().equals("(")) {
                    int b = StackForDigits.pop();
                    int a = StackForDigits.pop();
                    StackForDigits.push(eval(a, b, Stack.pop()));
                }
                i++;
            } else if (words[i].matches("\\*|/")) {
                if (!Stack.isEmpty() && (Stack.peek().matches("\\*|/"))) {
                    while (!Stack.isEmpty() && (Stack.peek().matches("\\*|/"))) {
                        int b = StackForDigits.pop();
                        int a = StackForDigits.pop();
                        StackForDigits.push(eval(a, b, Stack.pop()));
                    }
                }
                Stack.push(words[i++]);
            } else if (words[i].matches("\\+|-")) {
                if (!Stack.isEmpty() && (Stack.peek().matches("\\*|/"))) {
                    while (!Stack.isEmpty() && (Stack.peek().matches("\\*|/"))) {
                        int b = StackForDigits.pop();
                        int a = StackForDigits.pop();
                        StackForDigits.push(eval(a, b, Stack.pop()));
                    }
                }
                if (!Stack.isEmpty() && (Stack.peek().matches("\\+|-"))) {
                    while (!Stack.isEmpty() && (Stack.peek().matches("\\+|-"))) {
                        int b = StackForDigits.pop();
                        int a = StackForDigits.pop();
                        StackForDigits.push(eval(a, b, Stack.pop()));
                    }
                }
                Stack.push(words[i++]);
            } else {
                i++;
            }
        }
        while (!Stack.isEmpty()) {
            int b = StackForDigits.pop();
            int a = StackForDigits.pop();
            StackForDigits.push(eval(a, b, Stack.pop()));
        }
        return StackForDigits.peek();
    }

    public static void ReversePolishNotation(String line) {
        System.out.println(shuttingYard(line.split(" ")));
    }

    interface Stack<T> {
        void push(T item);
        T pop();
        T peek();
        int size();
        boolean isEmpty();
    }

    public static class ArrayList <T> {
        private Object[] array;
        private int size;
        private int capacity = 20;

        public ArrayList() {
            array = new Object[capacity];
            size = 0;
        }

        public void expandsize() {
            capacity *= 2;
            Object[] newArray = new Object[capacity];
            System.arraycopy(array, 0, newArray, 0, size);
            this.array = newArray;
        }

        public void add(T value) {
            if (size == capacity) {
                expandsize();
            }
            array[size++] = value;
        }

        public T get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            return (T) array[index];
        }

        public T remove(int index) {
            T value = this.get(index);

            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            array[--size] = null;
            return value;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    public static class ArrayStack<T> implements Stack<T> {
        ArrayList<T> items;
        int stackSize;

        public ArrayStack() {
            this.items = new ArrayList<>();
            this.stackSize = 0;
        }

        @Override
        public int size() {
            return this.stackSize;
        }

        @Override
        public boolean isEmpty() {
            return (this.stackSize == 0);
        }

        @Override
        public void push(T item) {
            this.items.add(item);
            this.stackSize++;
        }

        @Override
        public T pop() {
            if (this.stackSize <= 0) {
                throw new RuntimeException("Cannot pop from an empty stack");
                // throw new EmptyStackException();
            }
            this.stackSize--;
            T item = this.items.get(this.stackSize);
            this.items.remove(this.stackSize);
            return item;
        }

        @Override
        public T peek() {
            return this.items.get(this.stackSize - 1);
        }
    }

}