/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.systems.RobotHardware;
import org.firstinspires.ftc.teamcode.systems.ActionExecutor;
import org.firstinspires.ftc.teamcode.systems.RobotPath;
import org.firstinspires.ftc.teamcode.systems.DriveTrain;
import java.util.ArrayList;

@Autonomous(name="Auto: Blue Alliance Left", group="Autonomous")
public class BlueAllianceLeft extends OpMode {

    private final ElapsedTime runtime = new ElapsedTime();
    private final ActionExecutor actionExecutor = new ActionExecutor(this, runtime);

    private final ArrayList<RobotPath> actions = new ArrayList<>();

    static final double     FORWARD_SPEED = 0.6;
    static final double     TURN_SPEED    = 0.5;

    public void init(){
        actionExecutor.init();


        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
    }
/*
    @Override
    public void start() {
        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way

        actions.add(new RobotPath(RobotPath.UtilizedHardware.INTAKE_SERVO, 1, 5, 5));

        actions.add(new RobotPath(-TURN_SPEED, TURN_SPEED, 0.8));
        actions.add(new RobotPath(FORWARD_SPEED, FORWARD_SPEED, 2));

        telemetry.addData("Status", "Path Initialized");
        telemetry.update();

        actionExecutor.runPaths(actions);

        // Step 1:  Spin left for 0.8 seconds
        // Step 2:  Drive forward for 2 Second
    }


    */
    @Override

    public void start() {

        double intakeServoSpeed = RobotHardware.INTAKE_SERVO_SPEED;

        actions.add(new RobotPath(RobotPath.UtilizedHardware.INTAKE_SERVO, intakeServoSpeed, 5, 5));

        actions.add(new RobotPath(DriveTrain.TURN_SPEED, -DriveTrain.TURN_SPEED, 0.8));
        actions.add(new RobotPath(DriveTrain.FORWARD_SPEED, DriveTrain.FORWARD_SPEED, 2));

        //Run the arm motor continuously for 1.2 seconds and then stop
        double armMotorPower = 1.0;
        double armMotorDuration = 1.2; // in seconds

        RobotHardware.runArmMotorForDuration(armMotorPower, armMotorDuration);

        //Run the wrist servo continuously to its final angle limit and then stop
        double wristServoPower = 1.0;
        double wristServoDuration = 1.2; // in seconds

        RobotHardware.runWristServoForDuration(wristServoPower, wristServoDuration);

        //Continuously set power to the hand servo in the range [0, 3]
        double handServoPower;
        handServoPower = 2.0;

        RobotHardware.setHandPower(handServoPower);

        telemetry.addData("Status", "Path Initialized");
        telemetry.update();

        actionExecutor.runPaths(actions);

        // Step 1: work in progress
        // Step 2: work in progress
        // Step 3: Run the arm motor continuously for 1.2 seconds and then stop
        // Step 4: Run the wrist servo continuously to its final angle limit and then stop
        // Step 5: Continuously set power to the hand servo in the range [0, 3]
    }
    @Override
    public void loop() {

    }
}