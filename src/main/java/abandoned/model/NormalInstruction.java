package abandoned.model;


public class NormalInstruction extends Instruction {
    // Specifies one of four general purpose registers; may be referred to by R0 – R3
    private int gpr;
    private int[] R = new int[2];

    // Specifies one of three index registers; may be referred to by X1 – X3. 0 value indicates no indexing.
    private int idr;
    private int[] IX = new int[2];

    // If I = 1, specifies indirect addressing; otherwise, no indirect addressing
    private int iad;
    private int I;

    // Specifies one of 32 locations
    private int addr;
    private int[] Address = new int[5];


    public NormalInstruction() {
        super();

        System.arraycopy(this.getMem(), 6, this.R, 0, 2);
        this.gpr = this.binaryArrayToInt(this.R);

        System.arraycopy(this.getMem(), 8, this.IX, 0, 2);
        this.idr = this.binaryArrayToInt(this.IX);

        this.iad = this.getMem()[10];
        this.I = this.getMem()[10];

        System.arraycopy(this.getMem(), 11, this.Address, 0, 5);
        this.addr = this.binaryArrayToInt(this.Address);
    }

    public NormalInstruction(Instruction instruction) {
        super(instruction);

        System.arraycopy(this.getMem(), 6, this.R, 0, 2);
        this.gpr = this.binaryArrayToInt(this.R);

        System.arraycopy(this.getMem(), 8, this.IX, 0, 2);
        this.idr = this.binaryArrayToInt(this.IX);

        this.iad = this.getMem()[10];
        this.I = this.getMem()[10];

        System.arraycopy(this.getMem(), 11, this.Address, 0, 5);
        this.addr = this.binaryArrayToInt(this.Address);
    }

    public int getGpr() {
        return gpr;
    }

    public int[] getR() {
        return R;
    }

    public int getIdr() {
        return idr;
    }

    public int[] getIX() {
        return IX;
    }

    public int getIad() {
        return iad;
    }

    public int getI() {
        return I;
    }

    public int getAddr() {
        return addr;
    }

    public int[] getAddress() {
        return Address;
    }
}
