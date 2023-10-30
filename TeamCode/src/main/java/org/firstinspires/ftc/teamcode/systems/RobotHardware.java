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
    private Servo   rightHand = null;

    public static final double MID_SERVO       =  0.5 ;
    public static final double HAND_SPEED      =  0.02 ;  // sets rate to move servo
    public static final double ARM_UP_POWER    =  0.45 ;
    public static final double ARM_DOWN_POWER  = -0.45 ;

    public RobotHardware (OpMode opmode) {
        opMode = opmode;
    }

    public void init(){
        leftHand = opMode.hardwareMap.get(Servo.class, "leftHand");
        rightHand = opMode.hardwareMap.get(Servo.class, "rightHand");

        armMotor = opMode.hardwareMap.get(DcMotor.class, "motorArm");

        leftHand.setPosition(MID_SERVO);
        rightHand.setPosition(MID_SERVO);

        opMode.telemetry.addData("RobotHardware", "Hardware Initialized");
    }

    public void setArmPower(double power) {
        armMotor.setPower(power);
    }

    public void setHandPositions(double offset) {
        offset = Range.clip(offset, -0.5, 0.5);
        leftHand.setPosition(MID_SERVO + offset);
        rightHand.setPosition(MID_SERVO - offset);
    }
}
