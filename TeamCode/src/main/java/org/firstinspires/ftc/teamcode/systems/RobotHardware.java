package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.Range;

public class RobotHardware {
    OpMode opMode;
    private Servo armServoLeft;
    private Servo armServoRight;
    private Servo wristServo;
    private CRServo rollerServo;

    public static final double WRIST_SPEED     =  0.001 ;
    public static final double INTAKE_SPEED      =  0.2 ;  // sets rate to move servo
    public static final double OUTTAKE_SPEED = 1.0;
    public static final double ARM_UP_POWER    =  0.001 ;
    public static final double INTAKE_SERVO_SPEED = 0.5; // Adjust as needed

    public static final double DRONE_IDLE = 1.0;
    public static final double DRONE_LAUNCHED = 0.5;

    public RobotHardware (OpMode opmode) {
        opMode = opmode;
    }

    public void init(){
        armServoLeft = opMode.hardwareMap.get(Servo.class, "armServoLeft");
        armServoRight = opMode.hardwareMap.get(Servo.class, "armServoRight");
        wristServo = opMode.hardwareMap.get(Servo.class, "wristServo");
        rollerServo = opMode.hardwareMap.get(CRServo.class, "rollerServo");

        armServoLeft.setDirection(Servo.Direction.FORWARD);
        armServoRight.setDirection(Servo.Direction.FORWARD);

        opMode.telemetry.addData(getClass().getName(), "Hardware Initialized");
    }

    public void updateTelemetry() {
        opMode.telemetry.addLine("Robot Hardware");
        opMode.telemetry.addLine();
    }

    public void moveArm(int direction){
        double armLeftPos = armServoLeft.getPosition();
        double armRightPos = armServoRight.getPosition();

        armServoLeft.setPosition(armLeftPos + (RobotHardware.ARM_UP_POWER * direction));
        armServoRight.setPosition(armRightPos + (RobotHardware.ARM_UP_POWER) * -direction);

    }

    public void moveWrist(int direction){
        double wristPos = wristServo.getPosition();
        wristServo.setPosition(wristPos + (RobotHardware.WRIST_SPEED * direction));
    }

    public void setRollerServo(int power){
        rollerServo.setPower(power);
    }
}
