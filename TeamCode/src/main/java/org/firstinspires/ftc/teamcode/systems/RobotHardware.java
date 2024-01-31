package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotHardware {
    private final OpMode opMode;
    private DcMotor armMotor;

    private Servo wristServo;
    private CRServo rollerServo;


    /** Constant for the speed of the wrist. */
    public static final double WRIST_SPEED     =  0.001 ;
    /** Constant for the speed of the intake. */
    public static final double INTAKE_SPEED      =  0.2 ;  // sets rate to move servo
    /** Constant for the speed of the intake in reverse. */
    public static final double OUTTAKE_SPEED = 1.0;
    /** Constant for the speed of the arm. */
    public static final double ARM_POWER    =  0.5 ;

    /** Constant for the position of the servo in idle driving the Drone. */
    public static final double DRONE_IDLE = 1.0;
    /** Constant for the position of the servo driving the Drone (after launch). */
    public static final double DRONE_LAUNCHED = 0.5;

    public RobotHardware (OpMode opmode) {
        opMode = opmode;
    }

    public void init(){
        armMotor = opMode.hardwareMap.get(DcMotor.class, "armMotor");
        wristServo = opMode.hardwareMap.get(Servo.class, "wristServo");
        rollerServo = opMode.hardwareMap.get(CRServo.class, "rollerServo");

        opMode.telemetry.addData(getClass().getName(), "Hardware Initialized");
    }

    public void updateTelemetry() {
        opMode.telemetry.addLine("Robot Hardware");
        opMode.telemetry.addLine();
    }

    /**
     * The method used to move the arm on the robot.
     * Positive power = lift upwards,
     * Negative power = move downwards
     * @param Power Power given to the arm.
     */
    public void moveArm(double Power){
        armMotor.setPower(Power);
    }

    /**
     * The method used to move the wrist on the end of the robot.
     * @param direction Direction for the servo to rotate in.
     */
    public void moveWrist(int direction){
        double wristPos = wristServo.getPosition();
        wristServo.setPosition(wristPos + (RobotHardware.WRIST_SPEED * direction));
    }

    /**
     * The method used to move the roller on the end of the wrist.
     * @param power Power given to the roller.
     */
    public void setRollerServo(double power){
        rollerServo.setPower(power);
    }
}
