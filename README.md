# CSCI6461 Project
A simulation of a small classical CISC computer  

All the classes we need to implement are in *ClassProject/src/main/java/com/app/classproject*.

## *Controller: Interact with front-end*
### InterfaceController.java ###
Display the user interface **(DON NOT CAHNGE)**.  

### NewActionController.java ###
Receive requests submitted by the front-end, execute the action and return the result. You can add a data interface here for testing. Data are passed in JSON format: Just create a JSONObject, put something in and return it. Usually put a status in the JSON data, 0 → normal and -1 → abnormal.

## *Model*
### Instructions.java ###
Contains definitions of program instructions. Each instruction takes a 16-bit binary input.  

### Computer.java ###
(Correspond to old **Main.java**). Define the virtual computer, could initialize registers and memory, preload and run program.  

### Memory.java ###
Models a 16-bit memory unit. Includes functions to translate from a base 2 binary array to base 10 integer and parse the components of an instruction.  

### Register.java ###
Models a program register and provides a constructor to create all required types of registers.  
