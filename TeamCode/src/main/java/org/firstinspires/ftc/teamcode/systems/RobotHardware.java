package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class RobotHardware {
    OpMode opMode = null;
    private DcMotor armMotor = null;
    private Servo leftHand = null;
    private Servo rightHand = null;
    public Servo wristServo = null;

    public static final double MID_SERVO       =  0.5 ;
    public static final double HAND_SPEED      =  0.02 ;  // sets rate to move servo
    public static final double ARM_UP_POWER    =  0.75 ;
    public static final double ARM_DOWN_POWER  = -0.75 ;

    public RobotHardware (OpMode opmode) {
        opMode = opmode;
    }

    public void init(){
        leftHand = opMode.hardwareMap.get(Servo.class, "leftHand");
        leftHand.setDirection(Servo.Direction.REVERSE);

        rightHand = opMode.hardwareMap.get(Servo.class, "rightHand");
        wristServo = opMode.hardwareMap.get(Servo.class, "wristServo");

        armMotor = opMode.hardwareMap.get(DcMotor.class, "motorArm");

       leftHand.setPosition(0);
       rightHand.setPosition(0);

       wristServo.setPosition(0);

       opMode.telemetry.addData("RobotHardware", "Hardware Initialized");
    }

    public void setArmPower(double power) {
        armMotor.setPower(power);
    }

    public void setHandPositions(double position) {
        leftHand.setPosition(position);
        rightHand.setPosition(position);
    }





    public void setWristPosition(double position){
        wristServo.setPosition(wristServo.getPosition() + position);
    }

    public void servoTelemetry(){
        opMode.telemetry.addData("LEFT HAND POSITION", leftHand.getPosition());
        opMode.telemetry.addData("RIGHT HAND POSITION", rightHand.getPosition());
    }
}
