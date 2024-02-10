package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.systems.ActionExecutor;
import org.firstinspires.ftc.teamcode.systems.DriveTrain;
import org.firstinspires.ftc.teamcode.systems.RobotPath;
import org.firstinspires.ftc.teamcode.systems.TensorflowDetector;

import java.util.ArrayList;

@Autonomous(name="Auto: Red Alliance Left Ram Board", group="Autonomous")
public class RedAllianceLeft extends LinearOpMode {

    private final ElapsedTime runtime = new ElapsedTime();
    private final ActionExecutor actionExecutor = new ActionExecutor(this, runtime);
    private final TensorflowDetector tfDetector = new TensorflowDetector(this);
    private final ArrayList<RobotPath> actions = new ArrayList<>();

    @Override
    public void runOpMode(){
        actionExecutor.init();

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();


        //Drive to the backstage
        actions.add(new RobotPath(DriveTrain.LEFT, 1.65));
        actions.add(new RobotPath(DriveTrain.FORWARD, 2.2));
        actions.add(new RobotPath(DriveTrain.RIGHT, 0.6));

        actions.add(new RobotPath(RobotPath.UtilizedHardware.ARM_MOTOR,1.0,5.0));
        actions.add(new RobotPath(0.6,0));

        actions.add(new RobotPath(RobotPath.UtilizedHardware.INTAKE_SERVO, 0.5, 5.0));
        actions.add(new RobotPath(RobotPath.UtilizedHardware.ARM_MOTOR,-1.0,5.0));
        actions.add(new RobotPath(DriveTrain.LEFT,0.7));
        actions.add(new RobotPath(DriveTrain.FORWARD, 0.3));


        waitForStart(); //Wait for start button to be pressed
        actionExecutor.runPaths(actions);

    }

    private boolean isInRange(double value, int min, int max){
        return(min < value && value < max);
    }
}

