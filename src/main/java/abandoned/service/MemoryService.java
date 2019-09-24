//package com.app.classproject.service;
//
//import com.app.classproject.common.IllegalMemoryException;
//import com.app.classproject.common.InvalidInputException;
//import com.app.classproject.model.Computer;
////import com.app.classproject.model.Memory;
//import com.app.classproject.model.Word;
//import com.app.classproject.model.memory;
//
//public class MemoryService {
//    public Computer alterMemory(Computer computer, String addressStr, String valueStr) throws InvalidInputException, IllegalMemoryException {
//        // check if memory address is null
//        if (addressStr == null || addressStr == "") {
//            throw new InvalidInputException("You need to input an address!");
//        }
//
//        // validate address
//        int address;
//        try {
//            address = Integer.parseInt(addressStr);
//        } catch (NumberFormatException e) {
//            throw new InvalidInputException("Address should be a non-negative integer!");
//        }
//        if (address < 0 || address >= computer.getMemorySize()) {
//            throw new InvalidInputException("Address out of bound!");
//        }
//        for (int i: computer.getReservedLocations()) {
//            if (address == i) {
//                throw new InvalidInputException("Address is reserved!");
//            }
//        }
//        Word target = new Word();
//
//        // validate value
//        int value;
//        if (valueStr == null || valueStr == "") {
//            value = 0;
//        } else {
//            try {
//                value = Integer.parseInt(valueStr);
//            } catch (NumberFormatException e) {
//                throw new InvalidInputException("Value should be a non-negative integer or empty!");
//            }
//            if (value < 0 || value >= (int)Math.pow(2, target.getMem().length)) {
//                throw new InvalidInputException("Value out of bound!");
//            }
//        }
//
//        // change value
//        target.loadValue(value);
//        computer.setMemoryByAddress(address, target);
//        return computer;
//    }
//
//    public void loadInstructions(Computer computer) {
//        int instructions[][] = new int[3][5];
//        instructions[0] = new int[] {1, 2, 3, 4, 5};
//        instructions[1] = new int[] {1, 2, 3, 4, 5};
//        instructions[2] = new int[] {2, 2, 3, 4, 5};
//
//        int startAddress = 8;
//        for (int i = 0; i < instructions.length; i++) {
//            memory temp = new memory();
//            temp.opc = instructions[i][0];
//            temp.gpr = instructions[i][1];
//            temp.idr = instructions[i][2];
//            temp.iad = instructions[i][3];
//            temp.address = instructions[i][4];
//            temp.load();
//            computer.getRAM()[i + startAddress] = temp;
//        }
//
//        computer.setBootProgramSize(instructions.length);
//        computer.getPc().setValue(startAddress);
//    }
//}
