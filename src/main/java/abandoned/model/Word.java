package abandoned.model;

import java.util.Arrays;

public class Word {
    // Main content with 16 binary integers
    private int[] mem;

    public Word() {
        mem = new int[16];
        for (int i = 0; i < 16; i++) {
            mem[i] = 0;
        }
    }

    public int[] getMem() {
        return mem;
    }

    public void setMem(int[] mem) {
        this.mem = mem;
    }

    public void loadValue(int value) {
        String binaryValue = "";
        while (value != 0) {
            binaryValue = Integer.toString(value % 2) + binaryValue;
            value = value / 2;
        }
        for (int i = 0; i < 16; i++) {
            if (16 - i > binaryValue.length()) {
                mem[i] = 0;
            } else {
                mem[i] = (int)binaryValue.charAt(binaryValue.length() + i - 16) - 48;
            }
        }
    }

    public int getValue() {
        int value = 0;
        int base = 1;
        for (int i = 15; i >= 0; i--) {
            value += mem[i] * base;
            base *= 2;
        }
        return value;
    }

    @Override
    public String toString() {
        return Arrays.toString(mem);
    }

    public int binaryArrayToInt(int[] array) {
        int length = array.length;
        int value = 0;
        int base = 1;
        for (int i = length - 1; i >= 0; i--) {
            value += array[i] * base;
            base *= 2;
        }
        return value;
    }
}
