public class ArrayList<E> {

    private final static int DEFAULT_CAPACITY = 10;
    private int capacity = Integer.MAX_VALUE;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int maximumCapacity) {
        this.elementData = new Object[maximumCapacity];
        this.capacity = maximumCapacity;
    }

    public void add(E e) {
        this.addNewElement(e);
    }

    public int size() {
        return this.size;
    }

    public E get(int index) {
        return (E) this.elementData[index];
    }

    public void remove(int index) {
        if (this.size - (index + 1) >= 0) {
            System.arraycopy(
                    this.elementData,
                    index + 1,
                    this.elementData,
                    index + 1 - 1,
                    this.size - (index + 1)
            );
        }

        this.elementData[--this.size] = null;
    }

    private void addNewElement(E e) {
        if (this.size == this.capacity) {
            throw new MaximumCapacityReachedException();
        }

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

class Klass {
    private int varOne;
    private String varTwo;
    private double varThree;
    private byte[] varFour;
    private float[] varFive;
    private ArrayList<String> varSix;

    public Klass() {
        this.varOne = 1;
        this.varTwo = "Two";
        this.varThree = 3.006;
        this.varFour = new byte[]{97, 98, 99, 100};
        this.varFive = new float[]{1, 2, 3, 4, 5};
        this.varSix = new ArrayList<>();
        this.varSix.add("Six 1");
        this.varSix.add("Six 2");
    }

    public int getVarOne() {
        return varOne;
    }

    public String getVarTwo() {
        return varTwo;
    }

    public double getVarThree() {
        return varThree;
    }

    public byte[] getVarFour() {
        return varFour;
    }

    public float[] getVarFive() {
        return varFive;
    }

    public ArrayList<String> getVarSix() {
        return varSix;
    }
}

class TestArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> testList = new ArrayList<>();

        for (int i = 1; i <= 11; i++) {
            testList.add(i);
        }

        System.out.println("Before remove");
        for (int i = 0; i < testList.size(); i++) {
            System.out.println(testList.get(i));
        }

        testList.remove(5);

        System.out.println("After remove");
        for (int i = 0; i < testList.size(); i++) {
            System.out.println(testList.get(i));
        }

        ArrayList<String> testStringList = new ArrayList<>(100);
        testStringList.add("This is my custom ArrayList implementation.");
        testStringList.add("Testing different data types.");

        for (int i = 0; i < testStringList.size(); i++) {
            System.out.println(testStringList.get(i));
        }

        ArrayList<Klass> testObjectKlassList = new ArrayList<>();
        testObjectKlassList.add(new Klass());

        Klass klass = testObjectKlassList.get(0);
        System.out.println(klass.getVarOne());
    }
}