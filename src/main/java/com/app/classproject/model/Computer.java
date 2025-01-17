package com.app.classproject.model;

public class Computer {
    public static int ERROR_RET_CODE = -1;
    public static int HLT_RET_CODE = 0;
    public static int SUCCESS_RET_CODE = 1;

    public Register[] gpr = new Register[4];
    public Register[] idx = new Register[3]; // X0-X2
    public Register pc, cc, ir, mar, mbr, mfr;

    public memory[] RAM = new memory[2048];

    public int status; // 1: working, 0: halt, -1: error

    public Computer() {
        // Initialize registers
        for(int i = 0; i < gpr.length; i++) {
            gpr[i] = new Register(Register.Type.GPR);
        }
        for(int i = 0; i < idx.length; i++) {
            idx[i] = new Register(Register.Type.IDX);
        }
        pc = new Register(Register.Type.PC);
        cc = new Register(Register.Type.CC);
        ir = new Register(Register.Type.IR);
        mar = new Register(Register.Type.MAR);
        mbr = new Register(Register.Type.MBR);
        mfr = new Register(Register.Type.MFR);

        // Initialize memory
        for(int i = 0; i < RAM.length; i++) {
            RAM[i] = new memory();
            RAM[i].ini();
        }

        status = 1;
    }

    /**
     * Build 16-bit instruction array based on given input
     */
    public int[] buildInstruction(int[] opcode, int[] IX, int[] R, int I, int[] address) {
        int[] instruction = new int[16];

        // Set opcode
        for(int i = 0; i < 6; i++) {
            instruction[i] = opcode[i];
        }

        // Set IX
        instruction[6] = IX[0];
        instruction[7] = IX[1];

        // Set R
        instruction[8] = R[0];
        instruction[9] = R[1];

        // Set I
        instruction[10] = I;

        // Set Address
        for(int i = 11; i < 16; i++) {
            instruction[i] = address[i - 11];
        }

        return instruction;
    }

    /**
     * Specify instructions and load into memory
     */
    public void loadProgram() {
        // Program starts at RAM[6]
        pc.setValue(6);

        // Set register values
        idx[0].setValue(1);
        idx[1].setValue(2);
        idx[2].setValue(3);

        // Set memory values
        RAM[25].MEM = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0}; // 26
        RAM[25].setup();
        RAM[26].MEM = new int[] {0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0}; // 26794
        RAM[26].setup();
        RAM[28].MEM = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1}; // 29
        RAM[28].setup();
        RAM[29].MEM = new int[] {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1}; // 351
        RAM[29].setup();
        RAM[30].MEM = new int[] {0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0}; // 18650
        RAM[30].setup();
        RAM[31].MEM = new int[] {0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1}; // 7083
        RAM[31].setup();

        // Load register 0 from RAM[31]. LDR, no indexing, no indirect
        RAM[6].MEM = buildInstruction(Instructions.LDR_opc, new int[] {0, 0}, new int[] {0, 0}, 0, new int[] {1, 1, 1, 1, 1});
        RAM[6].setup();

        // Load register 2 from RAM[30]. LDR, indexing, no indirect
        RAM[7].MEM = buildInstruction(Instructions.LDR_opc, new int[] {0, 1}, new int[] {1, 0}, 0, new int[] {1, 1, 1, 0, 1});
        RAM[7].setup();

        // Load register 1 from RAM[29]. LDR, no indexing, indirect
        RAM[8].MEM = buildInstruction(Instructions.LDR_opc, new int[] {0, 0}, new int[] {0, 1}, 1, new int[] {1, 1, 1, 0, 0});
        RAM[8].setup();

        // Load register 3 from RAM[29]. LDR, indexing, indirect
        RAM[9].MEM = buildInstruction(Instructions.LDR_opc, new int[] {1, 0}, new int[] {1, 1}, 1, new int[] {1, 1, 0, 1, 0});
        RAM[9].setup();

        // Store register 0 to RAM[28]. STR, no indexing, no indirect
        RAM[10].MEM = buildInstruction(Instructions.STR_opc, new int[] {0, 0}, new int[] {0, 0}, 0, new int[] {1, 1, 1, 0, 0});
        RAM[10].setup();

        // Store register 1 to RAM[27]. STR, indexing, no indirect
        RAM[11].MEM = buildInstruction(Instructions.STR_opc, new int[] {1, 1}, new int[] {0, 1}, 0, new int[] {1, 1, 0, 0, 0});
        RAM[11].setup();

        // Store register 2 to RAM[26]. STR, no indexing, indirect
        RAM[12].MEM = buildInstruction(Instructions.STR_opc, new int[] {0, 0}, new int[] {1, 0}, 1, new int[] {1, 1, 0, 0, 1});
        RAM[12].setup();

        // Store register 3 to RAM[26]. STR, indexing, indirect
        RAM[13].MEM = buildInstruction(Instructions.STR_opc, new int[] {0, 1}, new int[] {1, 1}, 1, new int[] {1, 1, 0, 0, 0});
        RAM[13].setup();


        // Load register 3 with effective address. LDA, indexing, direct
        memory test4 = new memory();
        test4.opc = 3;
        test4.gpr = 3;
        test4.idr = 2;
        test4.iad = 0;
        test4.address = 31;
        test4.load();
        RAM[14] = test4;

        // Load register 3 with effective address. LDA, indexing, indirect
        test4 = new memory();
        test4.opc = 3;
        test4.gpr = 3;
        test4.idr = 2;
        test4.iad = 1;
        test4.address = 31;
        test4.load();
        RAM[15] = test4;

        // Load Index register 2 from Memory. LDX, indexing, direct
        test4 = new memory();
        test4.opc = 41;
        test4.gpr = 3;
        test4.idr = 2;
        test4.iad = 0;
        test4.address = 31;
        test4.load();
        RAM[16] = test4;

        // Load Index register 2 from Memory. LDX, indexing, indirect
        test4 = new memory();
        test4.opc = 41;
        test4.gpr = 3;
        test4.idr = 2;
        test4.iad = 1;
        test4.address = 26;
        test4.load();
        RAM[17] = test4;

        // Store Index Register 2 to Memory. STX, indexing, direct
        test4 = new memory();
        test4.opc = 42;
        test4.gpr = 3;
        test4.idr = 2;
        test4.iad = 0;
        test4.address = 31;
        test4.load();
        RAM[18] = test4;

        // Store Index Register 2 to Memory. STX, indexing, indirect
        test4 = new memory();
        test4.opc = 42;
        test4.gpr = 3;
        test4.idr = 2;
        test4.iad = 1;
        test4.address = 29;
        test4.load();
        RAM[19] = test4;

        ir.setValue(RAM[6].MEM);
    }

    /**
     * Run the program and print register/memory information after each instruction
     */
    public void runProgram() {
        Instructions curInstruction = new Instructions(RAM[pc.getBase10Value()].MEM, this);
        int executionResult = curInstruction.execute();
        if (executionResult == SUCCESS_RET_CODE) {
//            pc.setValue(pc.getBase10Value() + 1);
            this.status = 1;
            runProgram();
        } else if (executionResult == ERROR_RET_CODE) {
            this.status = -1;
        } else if (executionResult == HLT_RET_CODE) {
            this.status = 0;
        }
    }

    public void singleStep() {
        Instructions curInstruction = new Instructions(RAM[pc.getBase10Value()].MEM, this);
        int executionResult = curInstruction.execute();
        if (executionResult == SUCCESS_RET_CODE) {
//            pc.setValue(pc.getBase10Value() + 1);
            this.status = 1;
        } else if (executionResult == ERROR_RET_CODE) {
            this.status = -1;
        } else if (executionResult == HLT_RET_CODE) {
            this.status = 0;
        }
    }
}
