import java.util.EmptyStackException;

public class StackUsingLinkedList<E> {

    private static class StackNode<E> {
        E data;
        StackNode<E> next;

        StackNode(E data) {
            this.data = data;
        }
    }

    private StackNode<E> top;
    private int size;

    public void push(E obj) {
        StackNode<E> node = new StackNode<>(obj);

        if (this.top == null) {
            this.top = node;
        } else {
            StackNode<E> temp = this.top;
            this.top = node;
            this.top.next = temp;
        }
        this.size++;
    }

    public E peek() {
        if (this.top == null) {
            throw new EmptyStackException();
        }

        return this.top.data;
    }

    public E pop() {
        if (this.top == null) {
            throw new EmptyStackException();
        }

        E obj = this.top.data;
        this.top = this.top.next;
        this.size--;

        return obj;
    }

    public boolean isEmpty() {
        return this.top == null;
    }

    public int size() {
        return this.size;
    }
}

class TestStackUsingLinkedList {
    public static void main(String[] args) {
        StackUsingLinkedList<Integer> stackUsingLinkedList = new StackUsingLinkedList<>();
        stackUsingLinkedList.push(1);
        stackUsingLinkedList.push(2);
        stackUsingLinkedList.push(3);

        int sizeBeforePop = stackUsingLinkedList.size();
        int topElement = stackUsingLinkedList.peek();
        int poppedElement = stackUsingLinkedList.pop();
        int sizeAfterPop = stackUsingLinkedList.size();

        if (topElement != poppedElement) {
            System.out.println("Top element is incorrect");
        }

        if (sizeAfterPop >= sizeBeforePop && sizeAfterPop + 1 != sizeBeforePop) {
            System.out.println("Stack size after popping is incorrect");
        }
    }
}
