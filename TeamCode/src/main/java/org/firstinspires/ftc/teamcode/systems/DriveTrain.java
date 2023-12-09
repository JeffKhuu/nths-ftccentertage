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

    private final double[] speedArr = {0.69, 0.25}; // 69% speed, 25% speed
    private int selectedSpeed = 0;
    public boolean isSpeedSwitched = false;

    public static final double FORWARD_SPEED = 0.6; // Forward speed constant
    public static final double TURN_SPEED = 0.5;   // Turn speed constant

    /** The constant variable storing an array with the four motor powers to move the robot forwards. */
    public static final double[] FORWARD = {FORWARD_SPEED, FORWARD_SPEED, FORWARD_SPEED, FORWARD_SPEED};

    /** The constant variable storing an array with the four motor powers to move the robot backwards. */
    public static final double[] BACKWARD = {-FORWARD_SPEED, -FORWARD_SPEED, -FORWARD_SPEED, -FORWARD_SPEED};

    /** The constant variable storing an array with the four motor powers to move the robot left. */
    public static final double[] LEFT = {-FORWARD_SPEED, FORWARD_SPEED, FORWARD_SPEED, -FORWARD_SPEED};

    /** The constant variable storing an array with the four motor powers to move the robot right. */
    public static final double[] RIGHT = {FORWARD_SPEED, -FORWARD_SPEED, -FORWARD_SPEED, FORWARD_SPEED};

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
        /* Handles the drive train's telemetry */
        opMode.telemetry.addLine("Selected Speed");
        opMode.telemetry.addLine(writeSpeedTelemetry());
        opMode.telemetry.addLine();
    }

    /**
     * The method used to move the drive train given three inputs, most likely from a gamepad.
     * @param x A Value to dictate the "X" direction of the drive train
     * @param y A value to dictate the "Y" direction of the drive train
     * @param turn A Value to dictate the rotation of the drive train
     */
    public void move(double x, double y, double turn) {
        //x: left-stick-x
        //y: left-stick-y
        //Turn: right-stick-x



        double leftPower = y + x + turn;
        double leftBackPower = y - x + turn;
        double rightPower = y - x - turn;
        double rightBackPower = y + x - turn;

        double maxPower = Math.max(Math.max(Math.abs(leftPower), Math.abs(leftBackPower)),
                Math.max(Math.abs(rightPower), Math.abs(rightBackPower)));

        if (maxPower > 1.0) {
            leftPower /= maxPower;
            leftBackPower /= maxPower;
            rightPower /= maxPower;
            rightBackPower /= maxPower;
        }
        setDrivePower(leftPower * speedArr[selectedSpeed],
                rightPower * speedArr[selectedSpeed],
                leftBackPower * speedArr[selectedSpeed],
                rightBackPower * speedArr[selectedSpeed]);

    }


    /**
     * The method used to move the drive train given four powers for each of the motors.
     * @param leftPower The power to be given to the front-left motor.
     * @param rightPower The power to be given to the front-right motor.
     * @param leftBackPower The power to be given to the back-left motor.
     * @param rightBackPower The power to be given to the back-right motor.
     * @see <a href="https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html">Mecanum Wheel Guide</a>
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
     * @see <a href="https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html">Mecanum Wheel Guide</a>
     */
    public void setDrivePower(double[] powers){
        leftMotor.setPower(powers[0]);
        rightMotor.setPower(powers[1]);
        leftBackMotor.setPower(powers[2]);
        rightBackMotor.setPower(powers[3]);
    }


    public void mecanumDrive(double drive, double strafe, double turn) {
        double leftPower = drive + strafe + turn;
        double leftBackPower = drive - strafe + turn;
        double frontRightPower = drive - strafe - turn;
        double rearRightPower = drive + strafe - turn;

        double maxPower = Math.max(Math.max(Math.abs(leftPower), Math.abs(leftBackPower)),
                Math.max(Math.abs(frontRightPower), Math.abs(rearRightPower)));

        if (maxPower > 1.0) {
            leftPower /= maxPower;
            leftBackPower /= maxPower;
            frontRightPower /= maxPower;
            rearRightPower /= maxPower;
        }
        leftMotor.setPower(leftPower * speedArr[selectedSpeed]);
        leftBackMotor.setPower(leftBackPower * speedArr[selectedSpeed]);
        rightMotor.setPower(frontRightPower * speedArr[selectedSpeed]);
        rightBackMotor.setPower(rearRightPower * speedArr[selectedSpeed]);
    }

    public void switchSpeed() {
        if(isSpeedSwitched){ return; }

        if (selectedSpeed >= speedArr.length - 1) {
            selectedSpeed = 0;
        }else{
            selectedSpeed++;
        }

        isSpeedSwitched = true;
    }

    public void setSpeed(int x) {
        selectedSpeed = x;
    }

    /**
     * A method using StringBuilder to format a string containing the selected max speed of the robot.
     * See "Returns:" below for an example of the formatted string.
     * @return |||||||[ ● 20% ]||||||[ ◯ 50% ]||||||[ ◯ 70% ]|||||||
     */
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

    private boolean areMotorsValid(){
        return leftMotor == null || rightMotor == null || leftBackMotor == null || rightBackMotor == null;
    }
}
