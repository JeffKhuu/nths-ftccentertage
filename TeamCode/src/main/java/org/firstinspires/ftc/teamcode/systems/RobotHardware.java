package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class RobotHardware {
    OpMode opMode;
    private Servo armServo;

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
        armServo = opMode.hardwareMap.get(Servo.class, "armServo");
        opMode.telemetry.addData(getClass().getName(), "Hardware Initialized");
    }

    public void updateTelemetry() {
        opMode.telemetry.addLine("Robot Hardware");
        opMode.telemetry.addLine();
    }

    public void setArmPosition(int direction){
        armServo.setPosition(armServo.getPosition() + (RobotHardware.ARM_UP_POWER * direction));
    }
}
