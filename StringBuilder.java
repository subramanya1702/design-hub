import java.nio.charset.StandardCharsets;

public class StringBuilder {

    private final static int DEFAULT_CAPACITY = 16;
    private byte[] value;
    private int length;

    public StringBuilder() {
        this.value = new byte[DEFAULT_CAPACITY];
    }

    public StringBuilder(int initialCapacity) {
        this.value = new byte[initialCapacity];
    }

    public void append(Object object) {
        this.appendCharacters(String.valueOf(object));
    }

    public String toString() {
        return new String(copyRequiredBytes(), StandardCharsets.UTF_8);
    }

    public int length() {
        return this.length;
    }

    private void appendCharacters(String characters) {
        byte[] chars = characters.getBytes(StandardCharsets.UTF_8);

        for (byte c : chars) {
            if (this.value.length == this.length) {
                this.value = this.increaseCapacity();
            }

            this.value[this.length++] = c;
        }
    }

    private byte[] increaseCapacity() {
        int valueLength = this.value.length;
        byte[] newValue = new byte[valueLength * 2];

        for (int i = 0; i < newValue.length; i++) {

            if (i < valueLength) {
                newValue[i] = this.value[i];
            } else {
                newValue[i] = '\0';
            }
        }

        return newValue;
    }

    private byte[] copyRequiredBytes() {
        byte[] copyValue = new byte[this.length];

        System.arraycopy(this.value, 0, copyValue, 0, this.length);

        return copyValue;
    }
}

class TestStringBuilder {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("This is my ");
        stringBuilder.append("custom string builder ");
        stringBuilder.append("implementation!");

        System.out.println(stringBuilder);
        System.out.println("Length is: " + stringBuilder.length());

        stringBuilder = new StringBuilder();
        stringBuilder.append("There are ");
        stringBuilder.append(100);
        stringBuilder.append(" buildings in the city");
        stringBuilder.append('!');

        System.out.println(stringBuilder);
        System.out.println("Length is: " + stringBuilder.length());
    }
}
