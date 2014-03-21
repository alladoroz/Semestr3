package Alla.BinTree;

/**
 * @param generic Stack
 */
public class Stack<T> {
    private StackElement<T> head;

    public Stack() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void push(T value) {
        StackElement<T> newHead = new StackElement<T>(value, head);
        head = newHead;
    }

    public T pop() throws Exception{
        if (head == null)
            throw new Exception("Pop from empty stack");
        T result = head.getValue();
        head = head.getNext();
        return result;
    }

    public void clear() {
        head = null;
    }
}
