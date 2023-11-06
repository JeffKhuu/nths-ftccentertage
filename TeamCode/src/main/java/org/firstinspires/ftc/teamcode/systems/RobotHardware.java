package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.ServoFlavor;
import com.qualcomm.robotcore.hardware.configuration.annotations.ServoType;
import com.qualcomm.robotcore.util.Range;

public class RobotHardware {
    OpMode opMode = null;
    private DcMotor armMotor = null;
    private Servo leftHand = null;
    private Servo rightHand = null;
    public Servo wristServo = null;

    public static final double WRIST_SPEED     =  0.001 ;
    public static final double HAND_SPEED      =  0.3 ;  // sets rate to move servo
    public static final double ARM_UP_POWER    =  0.75 ;

    public RobotHardware (OpMode opmode) {
        opMode = opmode;
    }

    public void init(){
        leftHand = opMode.hardwareMap.get(Servo.class, "leftHand");

        rightHand = opMode.hardwareMap.get(Servo.class, "rightHand");
        rightHand.setDirection(Servo.Direction.REVERSE);

        wristServo = opMode.hardwareMap.get(Servo.class, "wristServo");

        armMotor = opMode.hardwareMap.get(DcMotor.class, "motorArm");

       leftHand.setPosition(0);
       rightHand.setPosition(0);

       wristServo.setPosition(1);

       opMode.telemetry.addData("RobotHardware", "Hardware Initialized");
    }

    public void setArmPower(double power) {
        armMotor.setPower(power);
    }

    public void setHandPositions(double position) {
        leftHand.setPosition(position);
        rightHand.setPosition(position);
        opMode.telemetry.addData("Left Hand Position", leftHand.getPosition());
        opMode.telemetry.addData("Right Hand Position", rightHand.getPosition());
    }

    public void setWristPosition(int direction) {
        wristServo.setPosition(getWristPosition() + (RobotHardware.WRIST_SPEED * direction));
        opMode.telemetry.addData("Wrist Position", wristServo.getPosition());
    }
    public double getWristPosition() {
        return wristServo.getPosition();
    }
}
