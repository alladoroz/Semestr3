package Alla.BinTree;

/**
 * @param generic StackElement
 */
public class StackElement<T> {
    private T value;
    private StackElement<T> next;

    public StackElement(T value) {
        this.value = value;
        next = null;
    }

    public StackElement(T value, StackElement<T> next) {
        this.value = value;
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public StackElement<T> getNext() {
        return next;
    }
}