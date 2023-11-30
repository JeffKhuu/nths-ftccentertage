package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.Locale;

public class DriveTrain {
    private final OpMode opMode;
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor leftBackMotor;
    private DcMotor rightBackMotor;

    private final double[] speedArr = {0.5, 0.2, 1.0}; // 50% speed, 20% speed, 100% speed
    private int selectedSpeed = 0;
    public boolean isSpeedSwitched = false;

    public static final double FORWARD_SPEED = 0.6; // Forward speed constant
    public static final double TURN_SPEED = 0.5;   // Turn speed constant

    /** The constant variable storing an array with the four motor powers to move the robot forwards. */
    public static final double[] FORWARD = {FORWARD_SPEED, FORWARD_SPEED, FORWARD_SPEED, FORWARD_SPEED};

    /** The constant variable storing an array with the four motor powers to move the robot backwards. */
    public static final double[] BACKWARD = {-FORWARD_SPEED, -FORWARD_SPEED, -FORWARD_SPEED, -FORWARD_SPEED};

    /** The constant variable storing an array with the four motor powers to move the robot left. */
    public static final double[] LEFT = {-FORWARD_SPEED, FORWARD_SPEED, -FORWARD_SPEED, FORWARD_SPEED};

    /** The constant variable storing an array with the four motor powers to move the robot right. */
    public static final double[] RIGHT = {FORWARD_SPEED, -FORWARD_SPEED, FORWARD_SPEED, -FORWARD_SPEED};

    /** The constant variable storing an array with the four motor powers to spin the robot clockwise. */
    public static final double[] SPIN_CW = {TURN_SPEED, -TURN_SPEED, TURN_SPEED, -TURN_SPEED};

    /** The constant variable storing an array with the four motor powers to spin the robot counter-clockwise. */
    public static final double[] SPIN_CCW = {-TURN_SPEED, TURN_SPEED, -TURN_SPEED, TURN_SPEED};

    public DriveTrain(OpMode opMode) {
        this.opMode = opMode;
    }

    public void init() {
        /* Get each drive train motor from the hardware map */
        leftMotor = opMode.hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = opMode.hardwareMap.get(DcMotor.class, "rightMotor");
        leftBackMotor = opMode.hardwareMap.get(DcMotor.class, "leftBackMotor");
        rightBackMotor = opMode.hardwareMap.get(DcMotor.class, "rightBackMotor");

        /* Set the directions of each motor so they spin in a consistent manner */
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
        rightBackMotor.setDirection(DcMotor.Direction.REVERSE);

        //Tell the user we are ready
        opMode.telemetry.addData(getClass().getName(), "Initialized");
    }

    public void updateTelemetry() {
        /* Handles the drive trains telemetry */
        opMode.telemetry.addLine("Selected Speed");
        opMode.telemetry.addLine(writeSpeedTelemetry());
        opMode.telemetry.addLine();
    }

    /**
     * The method used to move the drive train given three inputs, most likely from a gamepad.
     * * @param x A Value to dictate the "X" direction of the drive train
     * @param y A value to dictate the "Y" direction of the drive train
     * @param turn A Value to dictate the rotation of the drive train
     */
    public void move(double x, double y, double turn) {
        //x: left-stick-x
        //y: left-stick-y
        //Turn: right-stick-x
        double theta = Math.atan2(y, x);
        double power = Math.hypot(x, y);

        double sin = Math.sin(theta - Math.PI/4);
        double cos = Math.cos(theta - Math.PI/4);
        double max = Math.max(Math.abs(sin), Math.abs(cos));

        double leftPower = power * cos/max + turn;
        double rightPower = power * sin/max - turn;
        double leftBackPower = power * cos/max + turn;
        double rightBackPower = power * sin/max - turn;

        if((power + Math.abs(turn)) > 1){
            leftPower /= power + Math.abs(turn);
            rightPower /= power + Math.abs(turn);
            leftBackPower /= power + Math.abs(turn);
            rightBackPower /= power + Math.abs(turn);
        }

        setDrivePower(leftPower, rightPower, leftBackPower, rightBackPower);
    }

    /**
     * The method used to move the drive train given four powers for each of the motors.
     * @param leftPower The power to be given to the front-left motor.
     * @param rightPower The power to be given to the front-right motor.
     * @param leftBackPower The power to be given to the back-left motor.
     * @param rightBackPower The power to be given to the back-right motor.
     * @see <a href="https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html">Mecanum Guide</a>
     */
    public void setDrivePower(double leftPower, double rightPower, double leftBackPower, double rightBackPower){
        leftMotor.setPower(leftPower);
        leftBackMotor.setPower(leftBackPower);
        rightMotor.setPower(rightPower);
        rightBackMotor.setPower(rightBackPower);
    }

    /**
     * The method used to move the drive train given an array containing four powers for each of the motors.
     * @param powers An array of doubles containing four powers to set each motor.
     *               The order in which power is given to the motors is as follows:
     *               Front Left, Front Right, Back Left, Back Right
     * @see <a href="https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html">Mecanum Guide</a>
     */
    public void setDrivePower(double[] powers){
        leftMotor.setPower(powers[0]);
        rightMotor.setPower(powers[1]);
        leftBackMotor.setPower(powers[2]);
        rightBackMotor.setPower(powers[3]);
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
