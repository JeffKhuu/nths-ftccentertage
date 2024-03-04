package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.systems.ActionExecutor;
import org.firstinspires.ftc.teamcode.systems.DriveTrain;
import org.firstinspires.ftc.teamcode.systems.RobotPath;

import java.util.ArrayList;

@Autonomous(name="Auto: Blue Alliance Left", group="Autonomous")
public class BlueAllianceLeft extends LinearOpMode {

    private final ElapsedTime runtime = new ElapsedTime();
    private final ActionExecutor actionExecutor = ActionExecutor.getInstance(this, runtime);
    private final ArrayList<RobotPath> actions = new ArrayList<>();

    @Override
    public void runOpMode() {
        //Drive to the backstage
        actions.add(new RobotPath(DriveTrain.RIGHT, 0.75));
        actions.add(new RobotPath(DriveTrain.FORWARD, 0.5));

        actions.add(new RobotPath(RobotPath.UtilizedHardware.ARM_MOTOR,1.0,5.0));
        actions.add(new RobotPath(0.4,0));

        actions.add(new RobotPath(RobotPath.UtilizedHardware.INTAKE_SERVO, 0.5, 5.0));
        actions.add(new RobotPath(RobotPath.UtilizedHardware.ARM_MOTOR,-1.0,5.0));
        actions.add(new RobotPath(DriveTrain.RIGHT,0.7));
        actions.add(new RobotPath(DriveTrain.FORWARD, 0.3));

        waitForStart(); //Wait for start button to be pressed
        actionExecutor.runPaths(actions);
    }
}
