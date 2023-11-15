package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RobotHardware {
    OpMode opMode;
    private DcMotor armMotor;
    private CRServo leftHand;
    private CRServo rightHand;
    public CRServo wristServo;

    public CRServo crServoForward;
    public CRServo crServoReverse;

    public static final double WRIST_SPEED     =  0.001 ;
    public static final double HAND_SPEED      =  0.2 ;  // sets rate to move servo
    public static final double ARM_UP_POWER    =  0.75 ;
    public static final double STATIONARY_SERVO = 0.5;

    public RobotHardware (OpMode opmode) {
        opMode = opmode;
        crServoForward = null;
        crServoReverse = null;
    }



    public void init(){
        leftHand = opMode.hardwareMap.get(CRServo.class, "leftHand");
        rightHand = opMode.hardwareMap.get(CRServo.class, "rightHand");
        rightHand.setDirection(CRServo.Direction.REVERSE);

        wristServo = opMode.hardwareMap.get(CRServo.class, "wristServo");
        armMotor = opMode.hardwareMap.get(DcMotor.class, "motorArm");

        opMode.telemetry.addData(getClass().getName(), "Hardware Initialized");


   }
    public void init(HardwareMap hwMap) {
        // Map Servo to hardware
        crServoForward = hwMap.get(CRServo.class, "crservo_forward");
        crServoReverse = hwMap.get(CRServo.class, "crservo_reverse");

        // Set initial power
        crServoForward.setPower(0.5);
        crServoReverse.setPower(0.5);
    }
    public void updateTelemetry(){
        opMode.telemetry.addLine("Robot Hardware")
                .addData("Hand Power", "Left: %.2f Right: %.2f", leftHand.getPower(), rightHand.getPower())
                .addData("Wrist Power", wristServo.getPower());
        opMode.telemetry.addLine();
    }

    public void setArmPower(double power) {
        armMotor.setPower(power);
    }

    public void setWristPower(double power){
        wristServo.setPower(STATIONARY_SERVO + Range.clip(power, -0.5, 0.5) );
    }

    public void setHandPower(double power) {
        leftHand.setPower(power);
        rightHand.setPower(power);
    }


    public void setForward() {
            crServoForward.setPower(1.0);
            crServoReverse.setPower(0.0);
    }

        // for reverse motion
    public void setReverse() {
            crServoForward.setPower(0.0);
            crServoReverse.setPower(1.0);
    }

        //stop  Servos
    public void stopServos() {
            crServoForward.setPower(0.5);
            crServoReverse.setPower(0.5);
    }
//            ! DEPRECATED HAND CODE !

/*    public void setHandPositions(double position) {
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
 */

}
