package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class RobotHardware {
    OpMode opMode;
    private static DcMotor armMotor;
    private CRServo leftHand;
    private CRServo rightHand;
    public static Servo wristServo;
    private CRServo intakeServo;
    private static RobotHardware instance;
    public static final double WRIST_SPEED = 0.01;
    public static final double HAND_SPEED = 0.2;  // sets rate to move servo
    public static final double ARM_UP_POWER = 0.75;
    public static final double INTAKE_SERVO_SPEED = 0.5; // Adjust as needed


    public RobotHardware (OpMode opmode) {
        opMode = opmode;
        instance = this;
    }

    public void init(){
        leftHand = opMode.hardwareMap.get(CRServo.class, "leftHand");
        rightHand = opMode.hardwareMap.get(CRServo.class, "rightHand");
        leftHand.setDirection(DcMotorSimple.Direction.FORWARD);
        rightHand.setDirection(CRServo.Direction.REVERSE);

        wristServo = opMode.hardwareMap.get(Servo.class, "wristServo");
        armMotor = opMode.hardwareMap.get(DcMotor.class, "motorArm");
        intakeServo = opMode.hardwareMap.get(CRServo.class, "intakeServo");
        intakeServo.setDirection(CRServo.Direction.FORWARD);
        opMode.telemetry.addData(getClass().getName(), "Hardware Initialized");
    }

    public void updateTelemetry() {
        opMode.telemetry.addLine("Robot Hardware")
                .addData("Hand Power", "Left: %.2f Right: %.2f", leftHand.getPower(), rightHand.getPower())
                .addData("Wrist Power", wristServo.getPosition())
                .addData("Intake Servo Power", intakeServo.getPower());
        opMode.telemetry.addLine();
    }

    public void setArmPower(double power) {
        armMotor.setPower(Range.clip(power, -1, 1));
    }

    public static void runArmMotorForDuration(double power, double duration) {
        armMotor.setPower(Range.clip(power, -1, 1)); // Set power to the arm motor
        sleep((long) (duration * 1000)); // Convert duration from seconds to milliseconds
        armMotor.setPower(0.0); // Stop the arm motor
    }

    /* public void setWristPower(double power){
        wristServo.setPower(Range.clip(power, -1, 1));
    }*/

    public static void runWristServoForDuration(double power, double duration) {
        double initialPosition = wristServo.getPosition();
        wristServo.setPosition(initialPosition + Range.clip(power, -1, 1)); // Set position of the wrist servo
        sleep((long) (duration * 1000)); // Convert duration from seconds to milliseconds
        wristServo.setPosition(initialPosition); // Set the wrist servo back to its initial position
    }

    public static void setHandPower(double power) {
        if (instance != null) {
            instance.leftHand.setPower(Range.clip(power, -1, 1));
            instance.rightHand.setPower(Range.clip(power, -1, 1));
        }
    }

    // Deprecated hand code...

    public void moveWristPosition(int direction) {
        wristServo.setPosition(getWristPosition() + (RobotHardware.WRIST_SPEED * direction));
    }

    public double getWristPosition() {
        return wristServo.getPosition();
    }

    // Existing sleep method...

    private static void sleep(long millis) {
        long endTime = System.currentTimeMillis() + millis;
        while (System.currentTimeMillis() < endTime) {
            // Do nothing and wait
        }
    }
}
