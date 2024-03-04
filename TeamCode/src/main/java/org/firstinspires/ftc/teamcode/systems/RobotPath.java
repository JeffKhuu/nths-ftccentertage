package org.firstinspires.ftc.teamcode.systems;
public class RobotPath {
    public enum Type {
        MOVEMENT,
        OPERATION
    }
    public enum UtilizedHardware {
        ARM_MOTOR,
        WRIST_SERVO,
        INTAKE_SERVO
    }

    double leftPower;
    double leftBackPower;
    double rightPower;
    double rightBackPower;

    double power;
    double position;
    UtilizedHardware hardware;

    double duration;
    Type type;
    double delay = 0;

    public RobotPath(double[] power, double duration){
        this.leftPower = power[0];
        this.rightPower = power[1];
        this.leftBackPower = power[2];
        this.rightBackPower = power[3];

        this.duration = duration;
        this.type = Type.MOVEMENT;
    }

    public RobotPath(UtilizedHardware hardware, double power, double duration){
        this.hardware = hardware;
        this.power = power;

        this.duration = duration;
        this.type = Type.OPERATION;
    }

    public RobotPath(double position, double delay){
        this.hardware = UtilizedHardware.WRIST_SERVO;
        this.position = position;
        this.duration = 0;
        this.delay = delay;

        this.type = Type.OPERATION;
    }

    public RobotPath(double[] power, double duration, double delay){
        this.leftPower = power[0];
        this.rightPower = power[1];
        this.leftBackPower = power[2];
        this.rightBackPower = power[3];

        this.duration = duration;
        this.type = Type.MOVEMENT;
        this.delay = delay;
    }

    public RobotPath(UtilizedHardware hardware, double power, double duration, double delay){
        this.hardware = hardware;
        this.power = power;

        this.duration = duration;
        this.type = Type.OPERATION;
        this.delay = delay;
    }
}
