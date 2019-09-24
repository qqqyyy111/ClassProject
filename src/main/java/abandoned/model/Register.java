package abandoned.model;

public class Register {
    enum Type {
        GPR, // General purpose register
        IDX, // Index register
        PC, // Program counter
        CC, // Condition code
        IR, // Instruction register
        MAR, // Memory address register
        MBR, // Memory buffer register
        MFR // Machine fault register
    }

    private int size; // Unit is bits
    private Type type;
    private int value;

    public Register(Register.Type type) {
        this.type = type;
        this.value = 0;

        if (type == Register.Type.CC || type == Register.Type.MFR) {
            size = 4;
        } else if (type == Register.Type.PC) {
            size = 12;
        } else if (type == Register.Type.GPR || type == Register.Type.IR || type == Register.Type.MAR || type == Register.Type.MBR || type == Register.Type.IDX) {
            size = 16;
        } else {
            System.out.println("Unknown register type: " + type);
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
