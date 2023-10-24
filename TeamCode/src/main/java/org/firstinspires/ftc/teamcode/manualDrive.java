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
    private DriveTrain driveTrain = new DriveTrain();
    HashMap<Integer, Double>speedMap = new HashMap<Integer, Double>();
    int i = 0;

    @Override
    public void init(){
        speedMap.put(0, 0.5);
        speedMap.put(1, 0.2);
        speedMap.put(2, 0.7);

        driveTrain.init(hardwareMap);

        telemetry.addData("Status", "Initialization is a success");
    }

    @Override
    public void loop() {
        // Power sent to wheels, affected by joysticks
        double leftPower;
        double rightPower;

        double min = speedMap.get(i); //maximum/minimum power sent to wheels
        double max = speedMap.get(i);

        if(gamepad1.left_stick_button){ //Shift the maximum, minimum speeds
            i = switchSpeed(i);
        }

        // POV Mode uses left stick to go forward, and right stick to turn.
        // - This uses basic math to combine motions and is easier to drive straight.
        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
        leftPower    = Range.clip(drive + turn, min, max) ;
        rightPower   = Range.clip(drive - turn, min, max) ;

        // Send calculated power to drive train
        driveTrain.move(leftPower, rightPower);

        // Show the elapsed game time and wheel power.
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




