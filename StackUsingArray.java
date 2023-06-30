public class StackUsingArray<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public StackUsingArray() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public void push(E obj) {
        this.addNewElement(obj);
    }

    public E peek() {
        return (E) this.elementData[this.size - 1];
    }

    public E pop() {
        E obj = this.peek();
        this.elementData[this.size - 1] = null;
        this.size--;

        return obj;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    private void addNewElement(E e) {
        if (this.elementData.length == this.size) {
            this.elementData = increaseCapacity();
        }

        this.elementData[this.size++] = e;
    }

    private Object[] increaseCapacity() {
        int dataLength = this.elementData.length;
        Object[] newObject = new Object[dataLength * 2];

        for (int i = 0; i < newObject.length; i++) {
            if (i < dataLength) {
                newObject[i] = this.elementData[i];
            } else {
                newObject[i] = null;
            }
        }

        return newObject;
    }
}

class TestStackUsingArray {

    public static void main(String[] args) {
        StackUsingArray<Integer> stackUsingArray = new StackUsingArray<>();
        stackUsingArray.push(1);
        stackUsingArray.push(2);
        stackUsingArray.push(3);

        int sizeBeforePop = stackUsingArray.size();
        int topElement = stackUsingArray.peek();
        int poppedElement = stackUsingArray.pop();
        int sizeAfterPop = stackUsingArray.size();

        if (topElement != poppedElement) {
            System.out.println("Top element is incorrect");
        }

        if (sizeAfterPop >= sizeBeforePop && sizeAfterPop + 1 != sizeBeforePop) {
            System.out.println("Stack size after popping is incorrect");
        }
    }
}
