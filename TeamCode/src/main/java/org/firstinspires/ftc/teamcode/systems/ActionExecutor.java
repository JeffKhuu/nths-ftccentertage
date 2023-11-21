package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;

public class ActionExecutor {
    OpMode autoMode;
    private final ElapsedTime runtime;

    DriveTrain driveTrain;
    RobotHardware robotHardware;


    public ActionExecutor(OpMode opMode, ElapsedTime runtime){
        this.autoMode = opMode;
        this.runtime = runtime;
    }

    public void init(){
        driveTrain = new DriveTrain(autoMode);
        robotHardware = new RobotHardware(autoMode);

        driveTrain.init();
        robotHardware.init();
    }

    public void runPaths(ArrayList<RobotPath> actions){
        for(RobotPath action : actions){
            if(action.type == RobotPath.Type.MOVEMENT){
                driveTrain.setDrivePower(action.leftPower, action.rightPower);
                runtime.reset();
                while ((runtime.seconds() < action.duration)) {
                    autoMode.telemetry.addData("Path", "Leg %d: %4.1f S Elapsed",
                            actions.indexOf(action), runtime.seconds());
                    autoMode.telemetry.update();
                }
                driveTrain.setDrivePower(0,0);
            }

            if(action.type == RobotPath.Type.OPERATION){
                if(action.hardware == RobotPath.UtilizedHardware.ARM_MOTOR){
                    robotHardware.setArmPower(action.power);
                }
                else if(action.hardware == RobotPath.UtilizedHardware.WRIST_SERVO){
                    robotHardware.moveWristPosition((int) Math.round(action.power));
                }
                else if(action.hardware == RobotPath.UtilizedHardware.INTAKE_SERVO){
                    robotHardware.setHandPower(action.power);
                }

                runtime.reset();
                while ((runtime.seconds() < action.duration)) {
                    autoMode.telemetry.addData("Path", "Leg %d: %4.1f S Elapsed",
                            actions.indexOf(action), runtime.seconds());
                    autoMode.telemetry.update();
                }
                robotHardware.setArmPower(0);
                robotHardware.setHandPower(0);
            }

            if(action.delay != 0){
                runtime.reset();
                while ((runtime.seconds() < action.delay)) {
                    autoMode.telemetry.addData("Path", "Leg %d: Delay",
                            actions.indexOf(action));
                    autoMode.telemetry.update();
                }
            }
        }

        autoMode.telemetry.addData("Path", "Complete");
        autoMode.telemetry.update();
    }

}
