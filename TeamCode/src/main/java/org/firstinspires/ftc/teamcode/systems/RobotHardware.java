package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class RobotHardware {
    OpMode opMode;
    private DcMotor armMotor;
    private Servo wristServo;
    private CRServo rollerServo;
    private TouchSensor touchSensor;
    public static final double WRIST_SPEED = 0.001;
    public static final double INTAKE_SPEED = 0.2;
    public static final double OUTTAKE_SPEED = 1.0;
    public static final double ARM_POWER = 0.5;
    public static final double INTAKE_SERVO_SPEED = 0.5;
    private boolean isTouchSensorTouched;
    public static final double DRONE_IDLE = 1.0;
    public static final double DRONE_LAUNCHED = 0.5;

    public RobotHardware(OpMode opmode) {
        opMode = opmode;

    }

    public void init() {

        armMotor = opMode.hardwareMap.get(DcMotor.class, "armMotor");
        wristServo = opMode.hardwareMap.get(Servo.class, "wristServo");
        rollerServo = opMode.hardwareMap.get(CRServo.class, "rollerServo");
        touchSensor = opMode.hardwareMap.get(TouchSensor.class, "Touch");

        opMode.telemetry.addData(getClass().getName(), "Hardware Initialized");
        opMode.telemetry.update();
    }

    public void updateTelemetry() {
        opMode.telemetry.addLine("Robot Hardware");
        opMode.telemetry.addLine("Touch Sensor Touched: " + touchSensor.isPressed());
        opMode.telemetry.addLine();
        opMode.telemetry.update();
    }

    public boolean isTouchSensorTouched() {
        isTouchSensorTouched = touchSensor.isPressed();
        return isTouchSensorTouched;
    }

    public void moveArm(double power) {
        armMotor.setPower(power);
    }

    public void moveWrist(int direction) {
        double wristPos = wristServo.getPosition();
        wristServo.setPosition(wristPos + (RobotHardware.WRIST_SPEED * direction));
    }

    public void setRollerServo(double power) {
        rollerServo.setPower(power);
    }
}