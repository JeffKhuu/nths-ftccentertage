package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.systems.DriveTrain;
import org.firstinspires.ftc.teamcode.systems.RobotHardware;

@TeleOp(name = "TeleOp: Player-Control", group = "Movement")
public class manualOpMode extends OpMode {
    private final ElapsedTime runtime = new ElapsedTime();

    private final DriveTrain driveTrain = new DriveTrain(this);
    private final RobotHardware robotHardware = new RobotHardware(this);

    boolean isHanging = false;

    @Override
    public void init(){
        driveTrain.init();
        robotHardware.init();
        robotHardware.servoTelemetry();
        resetRuntime();
    }

    @Override
    public void loop() {
        /*

                          GAMEPAD ONE CONTROLS
                  X: Switch Speeds   B: Reset Speed to 70%
                  Left Stick: Drive   Right Stick: Turn
        */
        if(gamepad1.x){
            driveTrain.switchSpeed(); //Shift the maximum, minimum speeds when X BUTTON IS PRESSED
        }
        if(gamepad1.b){
            driveTrain.setSpeed(0); //Resets to default speed (70%)
        }

        double drive = -gamepad1.left_stick_y; // Values: -1 (Pull up) to 1 (Pull down)
        double turn  =  gamepad1.right_stick_x;
        driveTrain.move(drive, turn);



        /*

                                  GAMEPAD TWO CONTROLS
                  RT (Hold): Disengages Hand   RT (Release): Engages Hand
                  Left Stick: Arm              Right Stick: Wrist
        */

        //sets the DC arm moter power, very important for the arm to function
        robotHardware.setArmPower(-gamepad2.right_stick_y * RobotHardware.ARM_UP_POWER);

        //Gets the current wrist position and sets the increment that the wrist moves by
        double curWristPosition = robotHardware.getWristPosition();
        double wristIncrement = 0.001; //change this for wrist speed/possible angles

        //if the joystick is up and the wrist is not at 0 then increment its value up
        //if the joystick is down and the wristposition is not 0 then move the wrist down
        if(gamepad2.left_stick_y > 0 && curWristPosition < 1){
            robotHardware.setWristPosition(curWristPosition + wristIncrement);
        }else if(gamepad2.left_stick_y < 0 && curWristPosition > 0) {
            robotHardware.setWristPosition(curWristPosition - wristIncrement);
        }
        //if the joystick is up and the wrist is not at 0 then increment its value up
        //if the joystick is down and the wristposition is not 0 then move the wrist down
        if(gamepad2.dpad_up && curWristPosition < 1){
            robotHardware.setWristPosition(curWristPosition - wristIncrement);
        }else if(gamepad2.dpad_down && curWristPosition > 0){
            robotHardware.setWristPosition(curWristPosition + wristIncrement);
        }


        if(gamepad2.b){
            if(!isHanging){
                robotHardware.setArmPower(RobotHardware.ARM_DOWN_POWER);
                isHanging = true;
            }
            if(isHanging){
                robotHardware.setArmPower(RobotHardware.ARM_DOWN_POWER);
                isHanging = false;
            }
        }

        //if the right trigger is down the fingers open
        //if the joystick is down and the wristposition is not 0 then move the wrist down
        if(gamepad2.right_trigger == 1){
            robotHardware.setHandPositions(0.3);
        }else if(gamepad2.right_trigger == 0){
            robotHardware.setHandPositions(0);
        }


        /*
                    TELEMETRY
         */
        telemetry.addData("Status", "Run Time: " + runtime);
        robotHardware.servoTelemetry();
    }
    public void stop(){
        telemetry.addData("Status", "Stopped");
    }
}




