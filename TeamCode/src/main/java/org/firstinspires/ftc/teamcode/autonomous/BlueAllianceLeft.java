package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.systems.ActionExecutor;
import org.firstinspires.ftc.teamcode.systems.DriveTrain;
import org.firstinspires.ftc.teamcode.systems.RobotHardware;
import org.firstinspires.ftc.teamcode.systems.RobotPath;

import java.util.ArrayList;

@Autonomous(name="Auto: Blue Alliance Left", group="Autonomous")
public class BlueAllianceLeft extends LinearOpMode {

    private final ElapsedTime runtime = new ElapsedTime();
    private final ActionExecutor actionExecutor = new ActionExecutor(this, runtime);
    private final RobotHardware robotHardware = new RobotHardware(this);
    private final ArrayList<RobotPath> actions = new ArrayList<>();

    @Override
    public void runOpMode() {
        actionExecutor.init();
        robotHardware.init();

        waitForStart();

        actions.add(new RobotPath(DriveTrain.FORWARD, 1.5));
        actionExecutor.runPaths(actions);

        if (robotHardware.isTouchSensorTouched()) {
            actions.add(new RobotPath(DriveTrain.BACKWARD, 1.0));
            actions.add(new RobotPath(DriveTrain.RIGHT, 0.6));
            actions.add(new RobotPath(DriveTrain.SPIN_CCW, 1.3));
            actions.add(new RobotPath(DriveTrain.FORWARD, 1.4));
            actions.add(new RobotPath(RobotPath.UtilizedHardware.WRIST_SERVO, 1.0, 1.0, 0));
            actions.add(new RobotPath(RobotPath.UtilizedHardware.INTAKE_SERVO, 1.0, 1.0, 0));

            actions.add(new RobotPath(DriveTrain.BACKWARD, 2.0));
            actions.add(new RobotPath(DriveTrain.LEFT, 3.2));
            actions.add(new RobotPath(DriveTrain.SPIN_CCW,1.0));

            actionExecutor.runPaths(actions);

        } else {
            actions.add(new RobotPath(DriveTrain.SPIN_CCW, 0.3));
            actions.add(new RobotPath(DriveTrain.LEFT, 0.5));
            actions.add(new RobotPath(DriveTrain.FORWARD, 1.0));

            if (robotHardware.isTouchSensorTouched()) {
                actions.add(new RobotPath(DriveTrain.BACKWARD, 1.0));
                actions.add(new RobotPath(DriveTrain.RIGHT, 0.6));
                actions.add(new RobotPath(DriveTrain.SPIN_CW, 1.9));
                actions.add(new RobotPath(DriveTrain.FORWARD, 0.4));

                actions.add(new RobotPath(DriveTrain.BACKWARD, 3.0));
                actions.add(new RobotPath(DriveTrain.RIGHT, 2.0));
                actions.add(new RobotPath(DriveTrain.FORWARD, 3.2));
                actions.add(new RobotPath(DriveTrain.LEFT,2.0));

                actionExecutor.runPaths(actions);

            } else {
                actions.add(new RobotPath(DriveTrain.SPIN_CCW, 0.9));
                actions.add(new RobotPath(DriveTrain.FORWARD, 2.0));

                if (robotHardware.isTouchSensorTouched()) {
                    actions.add(new RobotPath(DriveTrain.BACKWARD, 1.0));
                    actions.add(new RobotPath(DriveTrain.RIGHT, 0.6));
                    actions.add(new RobotPath(DriveTrain.FORWARD, 1.0));
                    actions.add(new RobotPath(DriveTrain.BACKWARD, 3.0));
                    actions.add(new RobotPath(DriveTrain.RIGHT, 2.0));
                    actions.add(new RobotPath(DriveTrain.FORWARD, 3.2));
                    actionExecutor.runPaths(actions);
                }
            }
        }
    }
}
