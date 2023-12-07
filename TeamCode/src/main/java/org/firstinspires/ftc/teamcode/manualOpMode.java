package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.systems.DriveTrain;
import org.firstinspires.ftc.teamcode.systems.RobotHardware;

@TeleOp(name = "TeleOp: Player-Control", group = "Movement")
public class manualOpMode extends OpMode {
    private final ElapsedTime runtime = new ElapsedTime();

    private final DriveTrain driveTrain = new DriveTrain(this);
    private final RobotHardware robotHardware = new RobotHardware(this);
    private final BetterGamepad driverGamepad = new BetterGamepad(gamepad1);



    @Override
    public void init(){
        driveTrain.init();
        robotHardware.init();

        resetRuntime();
    }

    @Override
    public void loop() {
        //Handles the actions of gamepad1
        handleGamepad1();

        //Handles the actions of gamepad2
        handleGamepad2();

        /*
                    TELEMETRY
         */
        telemetry.addData("Status", "Run Time: " + runtime);
        driveTrain.updateTelemetry(); //Handles DriveTrain telemetry
        //robotHardware.updateTelemetry(); //Handles RobotHardware telemetry

    }
    public void stop(){
        telemetry.addData("Status", "Stopped");
    }


    private void handleGamepad1(){
        /*

                          GAMEPAD ONE CONTROLS
                  X: Switch Speeds   B: Reset Speed to 70%
                  Left Stick: Drive   Right Stick: Turn
        */

        if(gamepad1.x){
            driveTrain.switchSpeed();
        }

        if(!gamepad1.x && driveTrain.isSpeedSwitched){
            driveTrain.isSpeedSwitched = false;
        }

        if(gamepad1.b){
            driveTrain.setSpeed(0); //Resets to default speed (70%)
        }

        if(gamepad1.dpad_up){
            driveTrain.setDrivePower(DriveTrain.FORWARD);
        }
        else if(gamepad1.dpad_left){
            driveTrain.setDrivePower(DriveTrain.LEFT);
        }
        else if(gamepad1.dpad_right){
            driveTrain.setDrivePower(DriveTrain.RIGHT);
        }
        else if(gamepad1.dpad_down){
            driveTrain.setDrivePower(DriveTrain.BACKWARD);
        }

        if(gamepad1.left_bumper){
            driveTrain.setDrivePower(DriveTrain.SPIN_CCW);
        }
        else if(gamepad1.right_bumper){
            driveTrain.setDrivePower(DriveTrain.SPIN_CW);
        }

        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y; // Values: -1 (Pull up) to 1 (Pull down)
        double turn  =  gamepad1.right_stick_x;
        driveTrain.move(x, y, turn);
    }

    private void handleGamepad2(){
        /*

                                  GAMEPAD TWO CONTROLS
                  RT (Hold): Disengages Hand   RT (Release): Engages Hand
                  Left Stick: Arm              Right Stick/D-Pad Up & Down: Wrist
        */

        //sets the DC arm motor power, very important for the arm to function
        //  Values: -1 (Pull up) to 1 (Pull down)
        //          Positive Arm Power = Arm Raises
        //          Negative Arm Power = Arm Lowers
        if(-gamepad2.right_stick_y == 1 || gamepad2.dpad_up){
            robotHardware.moveArm(1); //Move arm up
            telemetry.addData("Arm", "Up");
        }
        else if(-gamepad2.right_stick_y == -1 || gamepad2.dpad_down){
            robotHardware.moveArm(-1); //Move arm down
            telemetry.addData("Arm", "Down");
        }

        if(-gamepad2.left_stick_y == 1){
            robotHardware.moveWrist(1);
        }else if(-gamepad2.left_stick_y == -1){
            robotHardware.moveWrist(-1);
        }

        if(gamepad2.right_trigger == 1){
            robotHardware.setRollerServo(1);
        }else{
            robotHardware.setRollerServo(-1);
        }

    }
}




