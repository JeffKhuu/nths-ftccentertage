package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;

public class ActionExecutor {
    LinearOpMode autoMode;
    private final ElapsedTime runtime;

    DriveTrain driveTrain;
    RobotHardware robotHardware;


    public ActionExecutor(LinearOpMode opMode, ElapsedTime runtime){
        this.autoMode = opMode;
        this.runtime = runtime;
    }

    public void init(){
        driveTrain = new DriveTrain(autoMode);
        robotHardware = new RobotHardware(autoMode);

        driveTrain.init();
        robotHardware.init();
    }

    // RUNS A SINGLE RobotPath
    /*
        In a RobotPath, you're given:
            action.type (MOVEMENT / OPERATION)
            action.hardware (ARM_MOTOR / WRIST_SERVO / INTAKE_SERVO)
            action.power (A value applied to the utilized hardware)

            action.duration (Time, in seconds, to run the robot path)
            action.delay (Time, in seconds, to delay until the next robot path)
     */
    public void runPath(RobotPath action){

        //Handles movement-based actions (uses DriveTrain)
        if(action.type == RobotPath.Type.MOVEMENT){
            driveTrain.setDrivePower(action.leftPower, action.leftBackPower, action.rightPower,  action.rightBackPower);
        }

        //Handles operation-based actions (uses RobotHardware)
        if(action.type == RobotPath.Type.OPERATION){
                if(action.hardware == RobotPath.UtilizedHardware.ARM_MOTOR){
                    robotHardware.setArmPower(action.power);
                }
                else if(action.hardware == RobotPath.UtilizedHardware.WRIST_SERVO){
                    if(action.position != 0){
                        robotHardware.setWrist(action.position);
                    }else{
                        robotHardware.moveWrist((int) Math.round(action.power));
                    }

                }
                else if(action.hardware == RobotPath.UtilizedHardware.INTAKE_SERVO){
                    robotHardware.setRollerServo(action.power);
                }
        }

        //Reset runtime and run code for for the specified duration
        runtime.reset();
        while (((runtime.seconds() < action.duration)) && autoMode.opModeIsActive() ) {
            autoMode.telemetry.addData("Path", "%4.1f S Elapsed", runtime.seconds());
            autoMode.telemetry.update();
        }

        stopAllProcesses(); //Stop all running motors/servos

        //If specified, add a delay until the next action.
        if(action.delay > 0){
            runtime.reset();
            while ((runtime.seconds() < action.delay) && autoMode.opModeIsActive()) {
                autoMode.telemetry.addData("Path", "Delay");
                autoMode.telemetry.update();
            }
        }
    }



    // RUNS A LIST OF RobotPaths
    /*
        In a single RobotPath, you're given:
            action.type (MOVEMENT / OPERATION)
            action.hardware (ARM_MOTOR / WRIST_SERVO / INTAKE_SERVO)
            action.power (A value applied to the utilized hardware)

            action.duration (Time, in seconds, to run the robot path)
            action.delay (Time, in seconds, to delay until the next robot path)
     */
    public void runPaths(ArrayList<RobotPath> actions) {
        for(RobotPath action : actions){
            if(!autoMode.opModeIsActive()){
                break;
            }

            if(action.type == RobotPath.Type.MOVEMENT){
                driveTrain.setDrivePower(action.leftPower, action.leftBackPower, action.rightPower,  action.rightBackPower);
            }

            if(action.type == RobotPath.Type.OPERATION){
                if(action.hardware == RobotPath.UtilizedHardware.ARM_MOTOR){
                    robotHardware.setArmPower(action.power);
                }
                else if(action.hardware == RobotPath.UtilizedHardware.WRIST_SERVO){
                    robotHardware.moveWrist((int) Math.round(action.power));
                }
                else if(action.hardware == RobotPath.UtilizedHardware.INTAKE_SERVO){
                    robotHardware.setRollerServo(action.power);
                }
            }

            runtime.reset();
            while ((runtime.seconds() < action.duration) && autoMode.opModeIsActive()) {
                autoMode.telemetry.addData("Path", "Leg %d: %4.1f S Elapsed",
                        actions.indexOf(action), runtime.seconds());
                autoMode.telemetry.update();
            }
            stopAllProcesses();

            if(action.delay > 0){
                runtime.reset();
                while ((runtime.seconds() < action.delay) && autoMode.opModeIsActive()) {
                    autoMode.telemetry.addData("Path", "Leg %d: Delay",
                            actions.indexOf(action));
                    autoMode.telemetry.update();
                }
            }
        }

        autoMode.telemetry.addData("Path", "Complete");
        autoMode.telemetry.update();
    }

    private void stopAllProcesses(){
        driveTrain.setDrivePower(0,0, 0, 0);
        robotHardware.setArmPower(0);
        robotHardware.setRollerServo(0);
    }

}
