package com.app.classproject.model;

import java.util.Arrays;

public class Instructions {
    public static final int[] HLT_opc = {0, 0, 0, 0, 0, 0};
    public static final int[] LDR_opc = {0, 0, 0, 0, 0, 1};
    public static final int[] STR_opc = {0, 0, 0, 0, 1, 0};
    public static final int[] LDA_opc = {0, 0, 0, 0, 1, 1};
    public static final int[] LDX_opc = {1, 0, 1, 0, 0, 1};
    public static final int[] STX_opc = {1, 0, 1, 0, 1, 0};

    public Computer computer;
    public memory instruction = new memory();

    public Instructions(int[] instruction, Computer computer) {
        this.computer = computer;
        this.instruction.MEM = instruction;
        this.instruction.setup();
    }

    /**
     * Execute instruction
     */
    public int execute() {
        System.out.println(Arrays.toString(instruction.MEM));
        switch(instruction.opc) {
            case 0:
                return Computer.HLT_RET_CODE;
            case 1:
                return LDR();
            case 2:
                return STR();
            case 3:
                return LDA();
            case 41:
                return LDX();
            case 42:
                return STX();
            default:
                return Computer.ERROR_RET_CODE;
        }
    }

    /**
     * Calculate effective address
     */
    private int getEffectiveAdr() {
        if(instruction.iad == 0) {
            // No indirect addressing
            switch(instruction.idr) {
                case 0:
                    // No indexing
                    return instruction.address;
                case 1:
                    return computer.idx[0].getBase10Value() + instruction.address;
                case 2:
                    return computer.idx[1].getBase10Value() + instruction.address;
                case 3:
                    return computer.idx[2].getBase10Value() + instruction.address;
                default:
                    return -1;
            }
        } else {
            // Indirect addressing
            switch(instruction.idr) {
                case 0:
                    // No indexing
                    return computer.RAM[instruction.address].mem;
                case 1:
                    return computer.RAM[instruction.address + computer.idx[0].getBase10Value()].mem;
                case 2:
                    return computer.RAM[instruction.address + computer.idx[1].getBase10Value()].mem;
                case 3:
                    return computer.RAM[instruction.address + computer.idx[2].getBase10Value()].mem;
                default:
                    return -1;
            }
        }
    }

    /**
     * Load register from memory
     */
    public int LDR() {
        int EA = getEffectiveAdr();
        computer.mar.setValue(EA);
        computer.mbr.setValue(computer.RAM[EA].MEM);
        computer.pc.setValue(computer.pc.getBase10Value() + 1);
        computer.ir.setValue(computer.RAM[computer.pc.getBase10Value()].MEM);

        System.out.println("\nRAM[" + EA + "] = " + computer.RAM[EA].mem + ", gpr[" + instruction.gpr + "] = " + computer.gpr[instruction.gpr].getBase10Value());
        System.out.println("Loading RAM[" + EA + "] into gpr[" + instruction.gpr + "]");

        switch(instruction.gpr) {
            case 0:
                computer.gpr[0].setValue(computer.RAM[EA].MEM);
                break;
            case 1:
                computer.gpr[1].setValue(computer.RAM[EA].MEM);
                break;
            case 2:
                computer.gpr[2].setValue(computer.RAM[EA].MEM);
                break;
            case 3:
                computer.gpr[3].setValue(computer.RAM[EA].MEM);
                break;
            default:
                return Computer.ERROR_RET_CODE;
        }

        System.out.println("RAM[" + EA + "] = " + computer.RAM[EA].mem + ", gpr[" + instruction.gpr + "] = " + computer.gpr[instruction.gpr].getBase10Value() + "\n");
        return Computer.SUCCESS_RET_CODE;
    }

    /**
     * Store register to memory
     */
    public int STR() {
        int EA = getEffectiveAdr();
        computer.pc.setValue(computer.pc.getBase10Value() + 1);
        computer.ir.setValue(computer.RAM[computer.pc.getBase10Value()].MEM);

        System.out.println("\nRAM[" + EA + "] = " + computer.RAM[EA].mem + ", gpr[" + instruction.gpr + "] = " + computer.gpr[instruction.gpr].getBase10Value());
        System.out.println("Storing gpr[" + instruction.gpr + "] into RAM[" + EA + "]");

        switch(instruction.gpr) {
            case 0:
                computer.mbr.setValue(computer.gpr[0].getValue());
                computer.RAM[EA].MEM = computer.gpr[0].getValue();
                computer.RAM[EA].setup();
                break;
            case 1:
                computer.mbr.setValue(computer.gpr[1].getValue());
                computer.RAM[EA].MEM = computer.gpr[1].getValue();
                computer.RAM[EA].setup();
                break;
            case 2:
                computer.mbr.setValue(computer.gpr[2].getValue());
                computer.RAM[EA].MEM = computer.gpr[2].getValue();
                computer.RAM[EA].setup();
                break;
            case 3:
                computer.mbr.setValue(computer.gpr[3].getValue());
                computer.RAM[EA].MEM = computer.gpr[3].getValue();
                computer.RAM[EA].setup();
                break;
            default:
                System.out.println("Error");
                return Computer.ERROR_RET_CODE;
        }

        System.out.println("RAM[" + EA + "] = " + computer.RAM[EA].mem + ", gpr[" + instruction.gpr + "] = " + computer.gpr[instruction.gpr].getBase10Value() + "\n");
        return Computer.SUCCESS_RET_CODE;

    }

    /**
     *  Load Register with Address
     */
    public int LDA() {
        int EA = getEffectiveAdr();
        computer.mbr.setValue(EA);
        computer.pc.setValue(computer.pc.getBase10Value() + 1);
        computer.ir.setValue(computer.RAM[computer.pc.getBase10Value()].MEM);

        switch (instruction.gpr) {
            case 0:
                computer.gpr[0].setValue(EA);
                return Computer.SUCCESS_RET_CODE;
            case 1:
                computer.gpr[1].setValue(EA);
                return Computer.SUCCESS_RET_CODE;
            case 2:
                computer.gpr[2].setValue(EA);
                return Computer.SUCCESS_RET_CODE;
            case 3:
                computer.gpr[3].setValue(EA);
                return Computer.SUCCESS_RET_CODE;
            default:
                System.out.println("Error");
                return Computer.ERROR_RET_CODE;
        }
    }

    /**
     *   Load Index Register from Memory
     */
    public int LDX() {
        int EA = getEffectiveAdr();
        computer.mar.setValue(EA);
        computer.mbr.setValue(computer.RAM[EA].MEM);
        computer.pc.setValue(computer.pc.getBase10Value() + 1);
        computer.ir.setValue(computer.RAM[computer.pc.getBase10Value()].MEM);

        switch(instruction.idr) {
            case 1:
                computer.idx[0].setValue(computer.RAM[EA].MEM);
                return Computer.SUCCESS_RET_CODE;
            case 2:
                computer.idx[1].setValue(computer.RAM[EA].MEM);
                return Computer.SUCCESS_RET_CODE;
            case 3:
                computer.idx[2].setValue(computer.RAM[EA].MEM);
                return Computer.SUCCESS_RET_CODE;
            default:
                System.out.println("Error");
                return Computer.ERROR_RET_CODE;
        }
    }

    /**
     *  Store Index Register to Memory
     */
    public int STX() {
        int EA = getEffectiveAdr();
        computer.pc.setValue(computer.pc.getBase10Value() + 1);
        computer.ir.setValue(computer.RAM[computer.pc.getBase10Value()].MEM);

        switch (instruction.idr) {
            case 1:
                computer.mbr.setValue(computer.idx[0].getValue());
                computer.RAM[EA].MEM = computer.idx[0].getValue();
                computer.RAM[EA].setup();
                return Computer.SUCCESS_RET_CODE;
            case 2:
                computer.mbr.setValue(computer.idx[1].getValue());
                computer.RAM[EA].MEM = computer.idx[1].getValue();
                computer.RAM[EA].setup();
                return Computer.SUCCESS_RET_CODE;
            case 3:
                computer.mbr.setValue(computer.idx[2].getValue());
                computer.RAM[EA].MEM = computer.idx[2].getValue();
                computer.RAM[EA].setup();
                return Computer.SUCCESS_RET_CODE;
            default:
                System.out.println("Error");
                return Computer.ERROR_RET_CODE;
        }
    }

    public void printInfo() {

    }
}