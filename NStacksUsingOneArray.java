public class NStacksUsingOneArray<E> {

    private static final int DEFAULT_CAPACITY = 12;
    private final int n;
    private Object[] elementData;
    private int[] stackSizes;
    private int[] stackMaxSizes;
    private int[] stackBegPointers;

    public NStacksUsingOneArray(int n) {
        this.n = n;
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.stackSizes = new int[n];
        this.stackMaxSizes = new int[n];
        this.stackBegPointers = new int[n];

        for (int i = 0; i < this.n; i++) {
            this.stackMaxSizes[i] = DEFAULT_CAPACITY / this.n;
            this.stackBegPointers[i] = (DEFAULT_CAPACITY / this.n) * i;
        }
    }

    public void push(int stackNumber, E obj) {
        this.addElement(stackNumber, obj);
    }

    public E peek(int stackNumber) {
        return getTopObj(stackNumber);
    }

    public E pop(int stackNumber) {
        E obj = getTopObj(stackNumber);
        this.elementData[this.stackBegPointers[stackNumber] + this.stackSizes[stackNumber] - 1] = null;
        this.stackSizes[stackNumber]--;

        return obj;
    }

    public boolean isEmpty(int stackNumber) {
        return this.size(stackNumber) == 0;
    }

    public int size(int stackNumber) {
        return this.stackSizes[stackNumber];
    }

    private void addElement(int stackNumber, E obj) {
        if (this.stackMaxSizes[stackNumber] == this.stackSizes[stackNumber]) {
            this.elementData = increaseCapacity(stackNumber);
        }

        this.elementData[this.stackBegPointers[stackNumber] + this.stackSizes[stackNumber]] = obj;
        this.stackSizes[stackNumber]++;
    }

    private Object[] increaseCapacity(int stackNumber) {
        int length = this.elementData.length;
        int newLength = length + this.stackMaxSizes[stackNumber];
        Object[] newStackData = new Object[newLength];

        if (stackBegPointers[stackNumber] + stackMaxSizes[stackNumber] >= 0) {
            System.arraycopy(this.elementData,
                    0,
                    newStackData,
                    0,
                    stackBegPointers[stackNumber] + stackMaxSizes[stackNumber]
            );
        }

        if (stackNumber < this.n - 1) {
            for (int i = stackNumber + 1; i < this.n; i++) {
                int offset = this.stackMaxSizes[stackNumber];

                if (stackMaxSizes[i] - stackBegPointers[i] >= 0) {
                    System.arraycopy(
                            this.elementData,
                            stackBegPointers[i],
                            newStackData,
                            stackBegPointers[i] + offset, stackMaxSizes[i] - stackBegPointers[i]
                    );
                }

                this.stackBegPointers[i] += offset;
            }
        }

        this.stackMaxSizes[stackNumber] = this.stackMaxSizes[stackNumber] * 2;

        return newStackData;
    }

    private E getTopObj(int stackNumber) {
        return (E) this.elementData[this.stackBegPointers[stackNumber] + this.stackSizes[stackNumber] - 1];
    }
}

class TestNStacksUsingOneArray {

    public static void main(String[] args) {
        NStacksUsingOneArray<Integer> nStacksUsingOneArray = new NStacksUsingOneArray<>(3);

        // Push
        for (int n = 0; n < 3; n++) {
            for (int i = 1; i <= 10; i++) {
                nStacksUsingOneArray.push(n, i);
            }

            if (nStacksUsingOneArray.size(n) != 10) {
                System.out.println("Size of stack number: " + n + " is incorrect");
            }

        }

        for (int n = 0; n < 3; n++) {
            // Peek
            int peekedObj = nStacksUsingOneArray.peek(n);
            if (peekedObj != 10) {
                System.out.println("Peeked element is incorrect");
            }

            // Pop
            int stackSizeBeforePop = nStacksUsingOneArray.size(n);
            int poppedObj = nStacksUsingOneArray.pop(n);
            if (poppedObj != 10) {
                System.out.println("Popped element is incorrect");
            }
            int stackSizeAfterPop = nStacksUsingOneArray.size(n);

            if (stackSizeAfterPop + 1 != stackSizeBeforePop) {
                System.out.println("Stack sizes doesn't match after popping");
            }
        }
    }
}