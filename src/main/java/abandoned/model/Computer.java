package abandoned.model;

import abandoned.common.IllegalMemoryException;

public class Computer {
    private Register r0, r1, r2, r3;
    private Register x1, x2, x3;
    private Register pc, cc, ir, mar, mbr, mfr;

    private int memorySize = 2048;
    private int[] RESERVED_LOCATIONS = {0, 1, 2, 3, 4, 5};;
    private Word[] memory;
    private int bootProgramSize;


    public Computer() {
        // Initialize registers
        r0 = new Register(Register.Type.GPR);
        r1 = new Register(Register.Type.GPR);
        r2 = new Register(Register.Type.GPR);
        r3 = new Register(Register.Type.GPR);
        x1 = new Register(Register.Type.IDX);
        x2 = new Register(Register.Type.IDX);
        x3 = new Register(Register.Type.IDX);
        pc = new Register(Register.Type.PC);
        cc = new Register(Register.Type.CC);
        ir = new Register(Register.Type.IR);
        mar = new Register(Register.Type.MAR);
        mbr = new Register(Register.Type.MBR);
        mfr = new Register(Register.Type.MFR);

        // Initialize memory
        memory = new Word[memorySize];
        for (int i = 0; i < memorySize; i++) {
            Word temp = new Word();
            memory[i] = temp;
        }
    }

    public Register getR0() {
        return r0;
    }

    public void setR0(Register r0) {
        this.r0 = r0;
    }

    public Register getR1() {
        return r1;
    }

    public void setR1(Register r1) {
        this.r1 = r1;
    }

    public Register getR2() {
        return r2;
    }

    public void setR2(Register r2) {
        this.r2 = r2;
    }

    public Register getR3() {
        return r3;
    }

    public void setR3(Register r3) {
        this.r3 = r3;
    }

    public Register getX1() {
        return x1;
    }

    public void setX1(Register x1) {
        this.x1 = x1;
    }

    public Register getX2() {
        return x2;
    }

    public void setX2(Register x2) {
        this.x2 = x2;
    }

    public Register getX3() {
        return x3;
    }

    public void setX3(Register x3) {
        this.x3 = x3;
    }

    public Register getPc() {
        return pc;
    }

    public void setPc(Register pc) {
        this.pc = pc;
    }

    public Register getCc() {
        return cc;
    }

    public void setCc(Register cc) {
        this.cc = cc;
    }

    public Register getIr() {
        return ir;
    }

    public void setIr(Register ir) {
        this.ir = ir;
    }

    public Register getMar() {
        return mar;
    }

    public void setMar(Register mar) {
        this.mar = mar;
    }

    public Register getMbr() {
        return mbr;
    }

    public void setMbr(Register mbr) {
        this.mbr = mbr;
    }

    public Register getMfr() {
        return mfr;
    }

    public void setMfr(Register mfr) {
        this.mfr = mfr;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    public int[] getReservedLocations() {
        return RESERVED_LOCATIONS;
    }

    public void setReservedLocations(int[] reservedLocations) {
        RESERVED_LOCATIONS = reservedLocations;
    }

    public Word[] getMemory() {
        return memory;
    }

    public void setMemory(Word[] memory) {
        this.memory = memory;
    }

    public Word getMemoryByAddress(int address) throws IllegalMemoryException {
        if (address < 0 || address >= this.getMemorySize()) {
            throw new IllegalMemoryException("Illegal Memory Exception: Address out of bound!");
        }
        for (int i: this.getReservedLocations()) {
            if (address == i) {
                throw new IllegalMemoryException("Illegal Memory Exception: Address is reserved!");
            }
        }
        return this.memory[address];
    }

    public void setMemoryByAddress(int address, Word word) throws IllegalMemoryException {
        if (address < 0 || address >= this.getMemorySize()) {
            throw new IllegalMemoryException("Illegal Memory Exception: Address out of bound!");
        }
        for (int i: this.getReservedLocations()) {
            if (address == i) {
                throw new IllegalMemoryException("Illegal Memory Exception: Address is reserved!");
            }
        }
        this.memory[address] = word;
    }

    public int getBootProgramSize() {
        return bootProgramSize;
    }

    public void setBootProgramSize(int bootProgramSize) {
        this.bootProgramSize = bootProgramSize;
    }
}
