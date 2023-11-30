package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.Locale;

public class DriveTrain {
    private final OpMode opMode;
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor leftBackMotor;
    private DcMotor rightBackMotor;

    private final double[] speedArr = {0.5, 0.2, 1.0}; // 100% speed, 50% speed, 20% speed
    private int selectedSpeed = 0;
    public boolean isSpeedSwitched = false;

    public static final double[] FORWARD = {1.0, 1.0, 1.0, 1.0};
    public static final double[] BACKWARD = {-1.0, -1.0, -1.0, -1.0};
    public static final double[] LEFT = {-1.0, 1.0, -1.0, 1.0};
    public static final double[] RIGHT = {1.0, -1.0, 1.0, -1.0};

    public static final double[] SPIN_CW = {1.0, -1.0, 1.0, -1.0};
    public static final double[] SPIN_CCW = {-1.0, 1.0, -1.0, 1.0};

    public static final double FORWARD_SPEED = 0.6; // Forward speed constant
    public static final double TURN_SPEED = 0.5;   // Turn speed constant

    public DriveTrain(OpMode opMode) {
        this.opMode = opMode;
    }

    public void init() {
        leftMotor = opMode.hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = opMode.hardwareMap.get(DcMotor.class, "rightMotor");
        leftBackMotor = opMode.hardwareMap.get(DcMotor.class, "leftBackMotor");
        rightBackMotor = opMode.hardwareMap.get(DcMotor.class, "rightBackMotor");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
        rightBackMotor.setDirection(DcMotor.Direction.REVERSE);

        opMode.telemetry.addData(getClass().getName(), "Initialized");
    }

    public void updateTelemetry() {
        opMode.telemetry.addLine("Selected Speed");
        opMode.telemetry.addLine(writeSpeedTelemetry());
        opMode.telemetry.addLine();
    }

    public void move(double drive, double strafe, double turn) {
//        double sin = Math.sin(theta - Math.PI/4);
//        double cos = Math.cos(theta - Math.PI/4);
//        double max = Math.max(Math.abs(sin), Math.abs(cos));
//
//        double leftPower = (drive * cos/max + turn) * speedArr[selectedSpeed];
//        double leftBackPower = (drive * sin/max + turn) * speedArr[selectedSpeed];
//        double rightMotorPower = (drive * sin/max - turn) * speedArr[selectedSpeed];
//        double rightBackPower = (drive * cos/max + turn) * speedArr[selectedSpeed];

        //drive left-stick-y
        //strafe left-stick-x
        //turn right-stick-x

        double leftPower = drive + strafe + turn;
        double rightPower = drive - strafe - turn;
        double leftBackPower = drive - strafe + turn;
        double rightBackPower = drive + strafe - turn;

        setDrivePower(leftPower, rightPower, leftBackPower, rightBackPower);
    }

    public void setDrivePower(double leftPower, double rightPower, double leftBackPower, double rightBackPower){
        leftMotor.setPower(leftPower);
        leftBackMotor.setPower(leftBackPower);
        rightMotor.setPower(rightPower);
        rightBackMotor.setPower(rightBackPower);
    }

    public void setDrivePower(double[] motorPower){
        leftMotor.setPower(motorPower[0]);
        leftBackMotor.setPower(motorPower[1]);
        rightMotor.setPower(motorPower[2]);
        rightBackMotor.setPower(motorPower[3]);
    }

    public void switchSpeed() {
        if(isSpeedSwitched){ return; }

        selectedSpeed++;
        if (selectedSpeed >= speedArr.length - 1) {
            selectedSpeed = 0;
        }
        isSpeedSwitched = true;
    }

    public void setSpeed(int x) {
        selectedSpeed = x;
    }

    public String writeSpeedTelemetry() {
        StringBuilder result = new StringBuilder();

        for (Double speed : speedArr) {
            if (speed == speedArr[selectedSpeed]) {
                result.append(String.format(Locale.ENGLISH, "||||||[ ● %.0f ]", (speed * 100)));
            } else {
                result.append(String.format(Locale.ENGLISH, "||||||[ ◯ %.0f ]", (speed * 100)));
            }
        }
        result.append("|||||||");

        /*
        Expected Result:
            |||||||[ ● 20% ]||||||[ ◯ 50% ]||||||[ ◯ 70% ]|||||||
        */

        return (result.toString());
    }
}
