package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class RobotHardware {
    OpMode opMode;
    private DcMotor armMotor;
    private CRServo leftHand;
    private CRServo rightHand;
    private Servo wristServo;

    private Servo droneServo;

    public static final double WRIST_SPEED     =  0.001 ;
    public static final double INTAKE_SPEED      =  0.2 ;  // sets rate to move servo
    public static final double OUTTAKE_SPEED = 1.0;
    public static final double ARM_UP_POWER    =  0.75 ;

    public static final double DRONE_IDLE = 1.0;
    public static final double DRONE_LAUNCHED = 0.5;

    public RobotHardware (OpMode opmode) {
        opMode = opmode;
    }



    public void init(){
        leftHand = opMode.hardwareMap.get(CRServo.class, "leftHand");
        rightHand = opMode.hardwareMap.get(CRServo.class, "rightHand");
        leftHand.setDirection(DcMotorSimple.Direction.FORWARD);
        rightHand.setDirection(CRServo.Direction.REVERSE);

        wristServo = opMode.hardwareMap.get(Servo.class, "wristServo");
        armMotor = opMode.hardwareMap.get(DcMotor.class, "motorArm");

        opMode.telemetry.addData(getClass().getName(), "Hardware Initialized");

   }
    public void updateTelemetry(){
        opMode.telemetry.addLine("Robot Hardware")
                .addData("Hand Power", "Left: %.2f Right: %.2f", leftHand.getPower(), rightHand.getPower())
                .addData("Wrist Power", wristServo.getPosition());
        opMode.telemetry.addLine();
    }

    public void setArmPower(double power) {
        armMotor.setPower(Range.clip(power, -1, 1));
    }

    public void setHandPower(double power) {
        leftHand.setPower(Range.clip(power, -1, 1));
        rightHand.setPower(Range.clip(power, -1, 1));
    }

    public void moveWristPosition(int direction) {
        wristServo.setPosition(getWristPosition() + (RobotHardware.WRIST_SPEED * direction));
    }

    public double getWristPosition() {
        return wristServo.getPosition();
    }

    public void moveDroneServo(double position){
        droneServo.setPosition(position);
    }

    public double getDroneServoPosition(){
        return(droneServo.getPosition());
    }


}
