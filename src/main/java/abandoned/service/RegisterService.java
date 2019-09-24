//package com.app.classproject.service;
//
//import com.app.classproject.common.InvalidInputException;
//import com.app.classproject.model.Computer;
//import com.app.classproject.model.Register;
//
//public class RegisterService {
//    public Computer alterRegister(Computer computer, String registerStr, String valueStr) throws InvalidInputException {
//        // check if register is null
//        if (registerStr == null || registerStr == "") {
//            throw new InvalidInputException("You need to select a register!");
//        }
//
//        // validate register
//        Register target;
//        switch (registerStr) {
//            case "R0":
//                target = computer.getR0();
//                break;
//            case "R1":
//                target = computer.getR1();
//                break;
//            case "R2":
//                target = computer.getR2();
//                break;
//            case "R3":
//                target = computer.getR3();
//                break;
//            case "X1":
//                target = computer.getX1();
//                break;
//            case "X2":
//                target = computer.getX2();
//                break;
//            case "X3":
//                target = computer.getX3();
//                break;
//            case "PC":
//                target = computer.getPc();
//                break;
//            case "CC":
//                target = computer.getCc();
//                break;
//            case "IR":
//                target = computer.getIr();
//                break;
//            case "MAR":
//                target = computer.getMar();
//                break;
//            case "MBR":
//                target = computer.getMbr();
//                break;
//            case "MFR":
//                target = computer.getMfr();
//                break;
//            default:
//                throw new InvalidInputException("Invalid register!");
//        }
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
//            if (value < 0 || value >= (int)Math.pow(2, target.getSize())) {
//                throw new InvalidInputException("Value out of bound!");
//            }
//        }
//
//        // change value
//        target.setValue(value);
//        return computer;
//    }
//}
