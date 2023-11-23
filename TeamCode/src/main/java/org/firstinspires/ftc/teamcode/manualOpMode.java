package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.systems.DriveTrain;
import org.firstinspires.ftc.teamcode.systems.RobotHardware;

@TeleOp(name = "TeleOp: Player-Control", group = "Movement")
public class manualOpMode extends OpMode {
    private final ElapsedTime runtime = new ElapsedTime();

    private final DriveTrain driveTrain = new DriveTrain(this);
    private final RobotHardware robotHardware = new RobotHardware(this);

    @Override
    public void init(){
        driveTrain.init();
        robotHardware.init();

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
                  Left Stick: Arm              Right Stick/D-Pad Up & Down: Wrist
        */

        //sets the DC arm motor power, very important for the arm to function
        //  Values: -1 (Pull up) to 1 (Pull down)
        //          Positive Arm Power = Arm Raises
        //          Negative Arm Power = Arm Lowers
        robotHardware.setArmPower(-gamepad2.right_stick_y * RobotHardware.ARM_UP_POWER);

        if(gamepad2.dpad_up || gamepad2.left_stick_y > 0){
            robotHardware.moveWristPosition(1);
        }
        if(gamepad2.dpad_down || gamepad2.left_stick_y < 0){
            robotHardware.moveWristPosition(-1);
        }

        if(gamepad2.right_trigger > 0){
            robotHardware.setHandPower(RobotHardware.HAND_SPEED);
        }else if(gamepad2.left_trigger > 0){
            robotHardware.setHandPower(-RobotHardware.HAND_SPEED);
        }else{
            robotHardware.setHandPower(0);
        }

        /*
                    TELEMETRY
         */
        telemetry.addData("Status", "Run Time: " + runtime);
        driveTrain.updateTelemetry();
        robotHardware.updateTelemetry();

    }
    public void stop(){
        telemetry.addData("Status", "Stopped");
    }
}




