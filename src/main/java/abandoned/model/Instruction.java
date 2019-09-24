package abandoned.model;

public class Instruction extends Word {
    // Specifies one of 64 possible instructions; Not all may be defined in this project
    private int opc;
    private int[] Opcode = new int[6];

    public Instruction() {
        super();

        System.arraycopy(this.getMem(), 0, this.Opcode, 0, 6);
        this.opc = this.binaryArrayToInt(this.Opcode);
    }

    public Instruction(Word word) {
        this.setMem(word.getMem());

        System.arraycopy(this.getMem(), 0, this.Opcode, 0, 6);
        this.opc = this.binaryArrayToInt(this.Opcode);
    }

    public int getOpc() {
        return opc;
    }

    public int[] getOpcode() {
        return Opcode;
    }
}
