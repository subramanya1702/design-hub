public class Queue<E> {

    private static class QueueNode<E> {
        private E data;
        private QueueNode<E> next;

        public QueueNode(E data) {
            this.data = data;
        }
    }

    private QueueNode<E> head;
    private QueueNode<E> tail;
    private int size;
    private int capacity = Integer.MAX_VALUE;

    public Queue() {
    }

    public Queue(final int maxCapacity) {
        this.capacity = maxCapacity;
    }

    public boolean add(E obj) {
        if (!this.insertNode(obj)) {
            throw new MaximumCapacityReachedException();
        }

        return true;
    }

    public boolean offer(E obj) {
        return this.insertNode(obj);
    }

    public E element() {
        E obj = this.getHead();

        if (obj == null) {
            throw new EmptyQueueException();
        }

        return obj;
    }

    public E peek() {
        return this.getHead();
    }

    public E remove() {
        E obj = this.element();
        this.head = this.head.next;
        this.size--;

        return obj;
    }

    public E poll() {
        E obj = this.getHead();

        if (obj == null) {
            return null;
        }
        this.head = this.head.next;
        this.size--;

        return obj;
    }

    public int size() {
        return this.size;
    }

    private boolean insertNode(E obj) {
        if (this.size == this.capacity) {
            return false;
        }

        if (this.head == null && this.tail == null) {
            this.head = new QueueNode<>(obj);
            this.tail = this.head;
        } else {
            this.tail.next = new QueueNode<>(obj);
            this.tail = this.tail.next;
        }

        this.size++;

        return true;
    }

    private E getHead() {
        if (this.head == null) {
            return null;
        }

        return this.head.data;
    }
}

class TestQueue {

    public static void main(String[] args) {
        // Test UnBounded Queue
        Queue<Integer> unBoundedQueue = new Queue<>();
        unBoundedQueue.add(1);
        unBoundedQueue.add(2);
        unBoundedQueue.add(3);

        int testElement = unBoundedQueue.element();
        int testPeek = unBoundedQueue.peek();

        if (testElement != testPeek) {
            System.out.println("Element's and Peek's data doesn't match");
        }

        int sizeBeforePoll = unBoundedQueue.size();
        int testRemove = unBoundedQueue.poll();
        int sizeAfterPoll = unBoundedQueue.size();

        if (testRemove != 1) {
            System.out.println("Polled data doesn't match");
        }

        if (sizeAfterPoll >= sizeBeforePoll && sizeAfterPoll + 1 != sizeBeforePoll) {
            System.out.println("Queue sizes doesn't match");
        }

        // Test Bounded Queue
        Queue<Integer> boundedQueue = new Queue<>(10);
        boolean isSuccessful;

        for (int i = 1; i <= 10; i++) {
            isSuccessful = boundedQueue.offer(i);
            System.out.println("Offer should be successful: " + isSuccessful);
        }

        isSuccessful = boundedQueue.offer(11);
        if (isSuccessful) {
            System.out.println("Offer should not be successful");
        }

        try {
            boundedQueue.add(12);
        } catch (MaximumCapacityReachedException e) {
            System.out.println("Exception thrown");
        }
    }
}