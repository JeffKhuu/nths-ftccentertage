package org.firstinspires.ftc.teamcode.systems;



import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Map;

public class DriveTrain {
    private OpMode opMode = null;
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private final double[] speedArr = {0.7, 0.5, 0.2}; //70% speed, 50% speed, 20% speed
    private int selectedSpeed = 0;

    public DriveTrain(OpMode opMode){
        this.opMode = opMode;
    }
    public void init() {
        leftMotor = opMode.hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = opMode.hardwareMap.get(DcMotor.class, "rightMotor");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);

        opMode.telemetry.addData(getClass().getName(), "Initialized");
    }

    public void move(double Drive, double Turn) {
        // Combine drive and turn for blended motion.
        double left  = Drive + Turn;
        double right = Drive - Turn;

        // Scale the values so neither exceed +/- 1.0
        double max = Math.max(Math.abs(left), Math.abs(right));
        if (max > 1.0)
        {
            left /= max;
            right /= max;
        }

        // Use existing function to drive both wheels.
        setDrivePower(left * speedArr[selectedSpeed], right * speedArr[selectedSpeed]);
    }

    public void setDrivePower(double leftWheel, double rightWheel) {
        // Output the values to the motor drives.
        leftMotor.setPower(leftWheel); //Multiplies power of the left wheel by an amt. (Default: 70%)
        rightMotor.setPower(rightWheel);
        opMode.telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftWheel, rightWheel);
    }

    public void switchSpeed(){
        if(selectedSpeed < speedArr.length-1){
            selectedSpeed++;
        }else{
            selectedSpeed = 0;//Loop back to the original speed
        }
    }

    public void setSpeed(int x){
        selectedSpeed = x;
    }

    public void updateTelemetry(){
        opMode.telemetry.addData("", "");
    }

}
