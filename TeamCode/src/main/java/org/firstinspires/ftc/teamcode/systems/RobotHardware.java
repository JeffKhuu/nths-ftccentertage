package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class RobotHardware {
    private static volatile RobotHardware instance;
    private final OpMode opMode;

    private DcMotor armMotor;
    private Servo wristServo;
    private CRServo rollerServo;


    /** Constant for the speed of the wrist. */
    public static final double WRIST_SPEED     =  0.001 ;
    /** Constant for the speed of the intake. */
    public static final double INTAKE_SPEED      =  0.2 ;  // sets rate to move servo
    public static final double OUTTAKE_SPEED = 1.0;

    /** Constant for the speed of the intake in reverse. */
    private TouchSensor touchSensor;

    /** Constant for the speed of the arm. */
    public static final double ARM_POWER    =  1.0 ;

    /** Constant for the position of the servo in idle driving the Drone. */
    public static final double DRONE_IDLE = 1.0;
    /** Constant for the position of the servo driving the Drone (after launch). */
    public static final double DRONE_LAUNCHED = 0.5;

    /** Constant for the state of the touch sensor. */
    private boolean isTouchSensorTouched;

    /**Variable representing the current power constantly applied to the hang power. */
    public double currentHangPower = 0;

    /** Constant power applied to a motor in order for it to hang. */
    public double HANG_POWER = -0.2;

    //Initialize a singleton instance to prevent multiple coexisting
    public static RobotHardware getInstance(OpMode opMode) {
        RobotHardware result = instance; //Create local variable to prevent accessing a volatile variable more than necessary
        if(result == null){
            synchronized (RobotHardware.class){ //Use double-check locking to ensure another thread does not create another instance
                result = instance;
                if(result == null){
                    instance = result = new RobotHardware(opMode); //Set both the local variable and global Singleton to new instance of DriveTrain
                }
            }
        }
        return result;
    }
    private RobotHardware(OpMode opmode) {
        opMode = opmode;
        init();
    }

    private void init() {

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
        return touchSensor.isPressed();
    }

    /**
     * The method used to move the arm on the robot.
     * Positive power = lift upwards,
     * Negative power = move downwards
     * @param Power Power given to the arm.
     */
    public void setArmPower(double Power){
        armMotor.setPower(-Power);
    } // !! Set to negative power, allegedly gear is on backwards !!

    /**
     * The method used to move the wrist on the end of the robot.
     * @param direction Direction for the servo to rotate in.
     */
    public void moveWrist(int direction){
        double wristPos = wristServo.getPosition();
        wristServo.setPosition(wristPos + (RobotHardware.WRIST_SPEED * direction));
    }

    /**
     * The method used to set the position of the wrist servo.
     * @param position End position for the servo (-1.0 - 1.0)
     */
    public void setWrist(double position){
        wristServo.setPosition(position);
    }

    /**
     * The method used to return the current position of the wrist servo.
     * @return Returns a double containing the position of the servo (-1.0 - 1.0)
     */
    public double getWristPosition(){
        return wristServo.getPosition();
    }

    /**
     * The method used to move the roller on the end of the wrist.
     * @param power Power given to the roller.
     */
    public void setRollerServo(double power){
        rollerServo.setPower(power);
    }
}