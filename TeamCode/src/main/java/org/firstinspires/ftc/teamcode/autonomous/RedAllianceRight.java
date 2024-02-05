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

@Autonomous(name="Auto: Red Alliance Right", group="Autonomous")
public class RedAllianceRight extends LinearOpMode {

    private final ElapsedTime runtime = new ElapsedTime();
    private final ActionExecutor actionExecutor = new ActionExecutor(this, runtime);
    private final TensorflowDetector tfDetector = new TensorflowDetector(this);
    private final ArrayList<RobotPath> actions = new ArrayList<>();

    @Override
    public void runOpMode(){
        actionExecutor.init();

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        //Drive to the backstage
        actions.add(new RobotPath(DriveTrain.FORWARD,  2));


        waitForStart(); //Wait for start button to be pressed
        actionExecutor.runPaths(actions);

    }
}
