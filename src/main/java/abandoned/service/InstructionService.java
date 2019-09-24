//package com.app.classproject.service;
//
//import com.app.classproject.common.IllegalMemoryException;
//import com.app.classproject.common.InvalidInstructionException;
//import com.app.classproject.model.Computer;
//import com.app.classproject.model.NormalInstruction;
//import com.app.classproject.model.memory;
////import com.app.classproject.model.Word;
//
//public class InstructionService {
//    public static final int	LDR_opc	= 1;
//    public static final int	STR_opc	= 2;
//    public static final int	LDA_opc	= 3;
//    public static final int	LDX_opc	= 41;
//    public static final int	STX_opc	= 42;
//
//    public Computer LDR(NormalInstruction word, Computer computer) throws InvalidInstructionException, IllegalMemoryException {
//        int EA = getEffectiveAdr(word, computer);
//
//        //Specifies one of four general purpose registers;
//        //may be referred to by R0 – R3
//        memory memorySrc = computer.getMemoryByAddress(EA);
//        switch(word.getGpr()) {
//            case 0:
//                computer.getR0().setValue(memorySrc.value);
//                break;
//            case 1:
//                computer.getR1().setValue(memorySrc.value);
//                break;
//            case 2:
//                computer.getR2().setValue(memorySrc.value);
//                break;
//            case 3:
//                computer.getR3().setValue(memorySrc.value);
//                break;
//            default:
//                throw new InvalidInstructionException("Invalid LDR Instruction: " + word.toString());
//        }
//        return computer;
//    }
//
//    public Computer STR(NormalInstruction word, Computer computer) throws InvalidInstructionException, IllegalMemoryException {
//        int EA = getEffectiveAdr(word, computer);
//        System.out.println(EA);
//
//        memory target = new memory();
//        switch(word.getGpr()) {
//            case 0:
//                target.loadValue(computer.getR0().getValue());
//                computer.setMemoryByAddress(EA, target);
//                break;
//            case 1:
//                target.loadValue(computer.getR1().getValue());
//                computer.setMemoryByAddress(EA, target);
//                break;
//            case 2:
//                target.loadValue(computer.getR2().getValue());
//                computer.setMemoryByAddress(EA, target);
//                break;
//            case 3:
//                target.loadValue(computer.getR3().getValue());
//                computer.setMemoryByAddress(EA, target);
//                break;
//            default:
//                throw new InvalidInstructionException("Invalid STR Instruction: " + word.toString());
//        }
//        return computer;
//    }
//
//    private int getEffectiveAdr(NormalInstruction word, Computer computer) throws InvalidInstructionException, IllegalMemoryException {
//        if (word.getI() == 0) {
//            switch(word.getIdr()) {
//                case 0:
//                    // No indexing
//                    return word.getAddr();
//                case 1:
//                    return computer.getX1().getValue() + word.getAddr();
//                case 2:
//                    return computer.getX2().getValue() + word.getAddr();
//                case 3:
//                    return computer.getX3().getValue() + word.getAddr();
//                default:
//                    throw new InvalidInstructionException("Invalid Instruction: " + word.toString());
//            }
//        } else {
//            switch(word.getIdr()) {
//                case 0:
//                    // No indexing
//                    return computer.getMemoryByAddress(word.getAddr()).value;
//                case 1:
//                    return computer.getMemoryByAddress(computer.getX1().getValue() + word.getAddr()).value;
//                case 2:
//                    return computer.getMemoryByAddress(computer.getX2().getValue() + word.getAddr()).value;
//                case 3:
//                    return computer.getMemoryByAddress(computer.getX3().getValue() + word.getAddr()).value;
//                default:
//                    throw new InvalidInstructionException("Invalid Instruction: " + word.toString());
//            }
//        }
//    }
//}
