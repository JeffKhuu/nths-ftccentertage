package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.HashMap;


@TeleOp(name = "manualDrive", group = "manual")
public class manualDrive extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    DcMotor leftMotor;
    DcMotor rightMotor;

    HashMap<Integer, Double>speedMap = new HashMap<Integer, Double>();
    int i = 0;


    @Override
    public void init(){
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");

        speedMap.put(0, 0.5);
        speedMap.put(1, 0.2);
        speedMap.put(2, 1.0);


        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialization is a success");
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running");

        // Power sent to wheels, affected by joysticks
        double leftPower;
        double rightPower;

        double min = speedMap.get(i); //maximum/minimum power sent to wheels
        double max = speedMap.get(i);

        if(gamepad1.left_stick_button){
            i = switchSpeed(i);
        }

        // POV Mode uses left stick to go forward, and right stick to turn.
        // - This uses basic math to combine motions and is easier to drive straight.
        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
        leftPower    = Range.clip(drive + turn, min, max) ;
        rightPower   = Range.clip(drive - turn, min, max) ;

        // Tank Mode uses one stick to control each wheel.
        // - This requires no math, but it is hard to drive forward slowly and keep straight.
//        leftPower  = -gamepad1.left_stick_y ;
//        rightPower = -gamepad1.right_stick_y ;

        // Send calculated power to wheels
        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Gamepad1", "RT Value: " + gamepad1.right_trigger);

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }

    public static int switchSpeed(int i){
        if(i < 2){
            return i++;
        }else{
            return i = 0;
        }
    }

}




