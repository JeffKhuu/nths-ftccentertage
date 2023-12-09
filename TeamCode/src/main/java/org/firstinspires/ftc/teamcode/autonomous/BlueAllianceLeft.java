package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.systems.ActionExecutor;
import org.firstinspires.ftc.teamcode.systems.DriveTrain;
import org.firstinspires.ftc.teamcode.systems.RobotPath;
import org.firstinspires.ftc.teamcode.systems.TensorflowDetector;

import java.util.ArrayList;
import java.util.List;

@Autonomous(name="Auto: Blue Alliance Left", group="Autonomous")
public class BlueAllianceLeft extends LinearOpMode{

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

        // Step 1:  Spin left for 0.8 seconds
        // Step 2:  Drive forward for 2 Second
        // Step 3:  Stop

        waitForStart(); //Wait for start button to be pressed
        actionExecutor.runPaths(actions);

    }

    private boolean isInRange(double value, int min, int max){
        return(min < value && value < max);
    }
}
