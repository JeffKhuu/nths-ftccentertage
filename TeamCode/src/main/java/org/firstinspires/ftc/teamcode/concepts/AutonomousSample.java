package org.firstinspires.ftc.teamcode.concepts;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.systems.ActionExecutor;
import org.firstinspires.ftc.teamcode.systems.DriveTrain;
import org.firstinspires.ftc.teamcode.systems.RobotPath;
import org.firstinspires.ftc.teamcode.systems.TensorflowDetector;

import java.util.ArrayList;

@Disabled
@Autonomous(name="Auto: AUTONOMOUS SAMPLE OPMODE", group="Autonomous")
public class AutonomousSample extends LinearOpMode {

    private final ElapsedTime runtime = new ElapsedTime();
    private final ActionExecutor actionExecutor = ActionExecutor.getInstance(this, runtime);
    private final TensorflowDetector tfDetector = new TensorflowDetector(this);
    private final ArrayList<RobotPath> actions = new ArrayList<>();

    @Override
    public void runOpMode(){
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        //Moves the wrist for 1.0 second, ends with 1.0 second delay
        actions.add(new RobotPath(RobotPath.UtilizedHardware.WRIST_SERVO, 1.0, 1.0, 1.0));

        //Turns the robot left for 0.8 seconds
        actions.add(new RobotPath(DriveTrain.SPIN_CW, 0.8));

        //Drives the robot forward for 2.0 seconds
        actions.add(new RobotPath(DriveTrain.FORWARD,2));

        waitForStart(); //Wait for start button to be pressed
        actionExecutor.runPaths(actions);

    }
}
