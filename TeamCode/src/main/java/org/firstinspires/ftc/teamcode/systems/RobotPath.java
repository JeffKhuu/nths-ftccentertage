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
    double rightPower;

    double power;
    UtilizedHardware hardware;

    double duration;
    Type type;
    double delay = 0;

    public RobotPath(double leftPower, double rightPower, double duration){
        this.leftPower = leftPower;
        this.rightPower = rightPower;

        this.duration = duration;
        this.type = Type.MOVEMENT;
    }

    public RobotPath(UtilizedHardware hardware, double power, double duration){
        this.hardware = hardware;
        this.power = power;

        this.duration = duration;
        this.type = Type.OPERATION;
    }

    public RobotPath(double leftPower, double rightPower, double duration, double delay){
        this.leftPower = leftPower;
        this.rightPower = rightPower;

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
