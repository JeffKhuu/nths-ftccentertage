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
                }
                driveTrain.setDrivePower(0,0);
            }

            if(action.type == RobotPath.Type.OPERATION){
                if(action.hardware == RobotPath.UtilizedHardware.ARM_MOTOR){
                    robotHardware.setArmPower(action.power);
                }
                else if(action.hardware == RobotPath.UtilizedHardware.WRIST_SERVO){
                    robotHardware.setWristPower(action.power);
                }
                else if(action.hardware == RobotPath.UtilizedHardware.INTAKE_SERVO){
                    robotHardware.setHandPower(action.power);
                }

                runtime.reset();
                while ((runtime.seconds() < action.duration)) {
                    autoMode.telemetry.addData("Path", "Leg %d: %4.1f S Elapsed",
                            actions.indexOf(action), runtime.seconds());
                }
                robotHardware.setArmPower(0);
                robotHardware.setWristPower(0);
                robotHardware.setHandPower(0);
            }
        }


        autoMode.telemetry.addData("Path", "Complete");
    }

}
