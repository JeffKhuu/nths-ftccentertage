package org.firstinspires.ftc.teamcode.systems;

public class Input {
    private static volatile Input instance;

    //Initialize a singleton instance to prevent multiple coexisting
    public static Input getInstance() {
        Input result = instance; //Create local variable to prevent accessing a volatile variable more than necessary
        if(result == null){
            synchronized (Input.class){ //Use double-check locking to ensure another thread does not create another instance
                result = instance;
                if(result == null){
                    instance = result = new Input(); //Set both the local variable and global Singleton to new instance of DriveTrain
                }
            }
        }
        return result;
    }
    private Input(){ }



}
