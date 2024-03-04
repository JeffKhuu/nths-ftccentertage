package org.firstinspires.ftc.teamcode.opmodes.autonomous.concepts;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.systems.ActionExecutor;
import org.firstinspires.ftc.teamcode.systems.DriveTrain;
import org.firstinspires.ftc.teamcode.systems.RobotHardware;
import org.firstinspires.ftc.teamcode.systems.RobotPath;

import java.util.ArrayList;

/*
    STARTING POSITION: Blue Alliance - Backstage (LEFT)
    SCENARIO:
        Drive to the backboard
        Deposit two pixels
        Drive to the left-most pixel stack
        Drive back to the backboard
        Deposit two more pixels
        Park backstage
 */

public class BlueAlliancePlace extends LinearOpMode {
    private final ElapsedTime runtime = new ElapsedTime();
    private final ActionExecutor actionExecutor = ActionExecutor.getInstance(this, runtime);

    //Constants for pathing
    private final ArrayList<RobotPath> DRIVE_TO_BACKSTAGE = new ArrayList<>();
    private final ArrayList<RobotPath> DEPOSIT_PIXELS = new ArrayList<>();
    @Override
    public void runOpMode() throws InterruptedException {
        //Propagate the actions to drive to the backstage.
        DRIVE_TO_BACKSTAGE.add(new RobotPath(DriveTrain.SPIN_CCW, 0.3, 1.0));
        DRIVE_TO_BACKSTAGE.add(new RobotPath(DriveTrain.FORWARD, 1, 1.0));
        DRIVE_TO_BACKSTAGE.add(new RobotPath(DriveTrain.RIGHT, 2, 1.0));

        //Propagate the actions to deposit pixels on the backboard.
        DEPOSIT_PIXELS.add(new RobotPath(RobotPath.UtilizedHardware.ARM_MOTOR, 1.0, 5));
        DEPOSIT_PIXELS.add(new RobotPath(RobotPath.UtilizedHardware.INTAKE_SERVO, RobotHardware.OUTTAKE_SPEED, 5));
        waitForStart(); //  ^ Everything above this line is run BEFORE start is hit ^

        actionExecutor.runPaths(DRIVE_TO_BACKSTAGE);
        actionExecutor.runPaths(DEPOSIT_PIXELS);

    }
}
