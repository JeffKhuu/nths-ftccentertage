package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.systems.DriveTrain;
import org.firstinspires.ftc.teamcode.systems.RobotHardware;

@TeleOp(name = "TeleOp: Player-Control", group = "Movement")
public class manualOpMode extends OpMode {
    private final ElapsedTime runtime = new ElapsedTime();

    private final DriveTrain driveTrain = new DriveTrain(this);
    private final RobotHardware robotHardware = new RobotHardware(this);

    private final Gamepad driverGamepad = gamepad1; //Handles movement. The 1st controller
    private final Gamepad opGamepad = gamepad2; //Handles intake, arm maneuvers. The 2nd controller

    @Override
    public void init(){
        driveTrain.init();
        robotHardware.init();
        resetRuntime();
    }

    @Override
    public void loop() {
        if(driverGamepad.x){ driveTrain.switchSpeed(); } //Shift the maximum, minimum speeds when X BUTTON IS PRESSED
        if(driverGamepad.b){ driveTrain.setSpeed(0); } //Resets to default speed (70%)

        double drive = -driverGamepad.left_stick_y; // Values: -1 (Pull up) to 1 (Pull down)
        double turn  =  driverGamepad.right_stick_x;
        driveTrain.move(drive, turn); //Move robot based on inputs

        robotHardware.setArmPower(opGamepad.left_stick_y * RobotHardware.ARM_UP_POWER);



        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
    }

    public void stop(){
        telemetry.addData("Status", "Stopped");
    }
}




