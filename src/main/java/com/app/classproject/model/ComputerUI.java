package com.app.classproject.model;

public class ComputerUI {
    public Register[] gpr = new Register[4];
    public Register[] idx = new Register[3]; // X0-X2
    public Register pc, cc, ir, mar, mbr, mfr;

    public int status;

    public ComputerUI(Computer computer) {
        for(int i = 0; i < gpr.length; i++) {
            gpr[i] = computer.gpr[i];
        }
        for(int i = 0; i < idx.length; i++) {
            idx[i] = computer.idx[i];
        }
        pc = computer.pc;
        cc = computer.cc;
        ir = computer.ir;
        mar = computer.mar;
        mbr = computer.mbr;
        mfr = computer.mfr;

        status = computer.status;
    }
}
