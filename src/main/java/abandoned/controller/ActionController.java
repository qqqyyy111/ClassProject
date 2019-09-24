//package com.app.classproject.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.app.classproject.common.IllegalMemoryException;
//import com.app.classproject.common.InvalidInputException;
//import com.app.classproject.common.InvalidInstructionException;
//import com.app.classproject.model.*;
//import com.app.classproject.service.InstructionService;
//import com.app.classproject.service.MemoryService;
//import com.app.classproject.service.RegisterService;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Arrays;
//
//
///**
// * Control actions such as altering register or run the computer
// */
//@RestController
//public class ActionController {
//    Computer computer = new Computer();
//    RegisterService registerService = new RegisterService();
//    MemoryService memoryService = new MemoryService();
//    InstructionService instructionService = new InstructionService();
//
//    @RequestMapping(value = "/action/initialize")
//    public String initialize(Model model) {
//        JSONObject result = new JSONObject();
//        ComputerUI computerUI = new ComputerUI(computer);
//        result.put("status", 0);
//        result.put("computer", computerUI);
//        return result.toString();
//    }
//
//    @RequestMapping(value = "/action/submitRegister")
//    public String alterRegister(Model model, String register, String value) {
//        JSONObject result = new JSONObject();
//        try {
//            computer = registerService.alterRegister(computer, register, value);
//            ComputerUI computerUI = new ComputerUI(computer);
//            result.put("status", 0);
//            result.put("computer", computerUI);
//        } catch (InvalidInputException e) {
//            result.put("status", -1);
//            result.put("errorMessage", e.getMessage());
//        }
//        return result.toString();
//    }
//
//    @RequestMapping(value = "/action/submitMemory")
//    public String alterInstruction(Model model, String address, String value) {
//        JSONObject result = new JSONObject();
//        try {
//            computer = memoryService.alterMemory(computer, address, value);
//            ComputerUI computerUI = new ComputerUI(computer);
//            result.put("status", 0);
//        } catch (InvalidInputException | IllegalMemoryException e) {
//            result.put("status", -1);
//            result.put("errorMessage", e.getMessage());
//        }
//        return result.toString();
//    }
//
//    @RequestMapping(value = "/action/submitInstruction")
//    public String loadInstruction(Model model, String instruction) {
//        return "Instruction: " + instruction;
//    }
//
//    @RequestMapping(value = "/action/IPL")
//    public String IPL(Model model) {
//        return "IPL";
//    }
//
//    @RequestMapping(value = "/action/run")
//    public String run(Model model) {
//        return "Run";
//    }
//
//    @RequestMapping(value = "/action/halt")
//    public String halt(Model model) {
//        return "Halt";
//    }
//
//    @RequestMapping(value = "/action/singleStep")
//    public String singleStep(Model model) {
//        return "Single step";
//    }
//
//
//    // Test cases
//    @RequestMapping(value = "/test/t0")
//    public String testSingleInstruction(Model model) {
//        JSONObject result = new JSONObject();
//
//        Word a = new Word();
//        a.setMem(new int[]{0, 0, 0, 0, 1, 0,   1, 1,   0, 1,   1,   1, 1, 1, 1, 1});
//        Instruction b = new Instruction(a);
//        NormalInstruction c = new NormalInstruction(b);
//        try {
//            computer = instructionService.STR(c, computer);
//            ComputerUI computerUI = new ComputerUI(computer);
//            result.put("status", 0);
//            result.put("computer", computerUI);
//        } catch (InvalidInstructionException | IllegalMemoryException e) {
//            result.put("status", -1);
//            result.put("errorMessage", e.getMessage());
//        }
//
//        return result.toString();
//    }
//
//    @RequestMapping(value = "/test/t1")
//    public int testGetMemoryByAddress(Model model, String address) throws IllegalMemoryException {
//        int addressInt = Integer.parseInt(address);
//        memory target = computer.getMemoryByAddress(addressInt);
//        return target.value;
//    }
//}
