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


//package org.firstinspires.ftc.teamcode.autonomous;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
//import org.firstinspires.ftc.teamcode.systems.ActionExecutor;
//import org.firstinspires.ftc.teamcode.systems.RobotPath;
//import org.firstinspires.ftc.teamcode.systems.TensorflowDetector;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Disabled
//@Autonomous(name="Auto: Blue Alliance Right", group="Autonomous")
//public class BlueAllianceRight extends OpMode {
//
//    private final ElapsedTime runtime = new ElapsedTime();
//    //private final ActionExecutor actionExecutor = new ActionExecutor(this, runtime);
//    private final TensorflowDetector tfDetector = new TensorflowDetector(this);
//    private final ArrayList<RobotPath> actions = new ArrayList<>();
//
//    static final double     FORWARD_SPEED = 0.6;
//    static final double     TURN_SPEED    = 0.5;

//    public void init(){
//        actionExecutor.init();
//
//        // Send telemetry message to signify robot waiting;
//        telemetry.addData("Status", "Ready to run");
//        telemetry.update();
//    }
//
//    @Override
//    public void start() {
//        List<Recognition> recognitions = tfDetector.getRecognitions();
//        Recognition recognition = recognitions.get(0);
//
//        double pixelPosition = (recognition.getLeft() + recognition.getRight()) / 2 ;
//
////        if(isInRange(pixelPosition, -40, -20)){ //Pixel is to the left
////            actions.add(new RobotPath(FORWARD_SPEED, FORWARD_SPEED, 0.2));
////            actions.add(new RobotPath(-TURN_SPEED, TURN_SPEED, 0.8, 5));
////        }
////
////        else if(isInRange(pixelPosition, -1, 1)){ //Pixel is centered
////            actions.add(new RobotPath(FORWARD_SPEED, FORWARD_SPEED, 1, 5));
////
////        } else if(isInRange(pixelPosition, 20, 40)){ //Pixel is to the right
////            actions.add(new RobotPath(FORWARD_SPEED, FORWARD_SPEED, 0.2));
////            actions.add(new RobotPath(TURN_SPEED, -TURN_SPEED, 0.8, 5));
////        }
//
//        actions.add(new RobotPath(FORWARD_SPEED, FORWARD_SPEED, 2));
//        actions.add(new RobotPath(-1, 0, 1));
//        actions.add(new RobotPath(FORWARD_SPEED, FORWARD_SPEED, 4));
//        actions.add(new RobotPath(RobotPath.UtilizedHardware.ARM_MOTOR, 0.5, 1.0)); //Moves arm up for 1 second
//
//        actionExecutor.runPaths(actions);
//
//
//        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way
//
//        // Step 1:  Drive forward for 1.5 seconds
//        // Step 2:  Spin left for 0.8 seconds
//        // Step 2:  Drive forward for 4 Second
//        // Step 4:  Stop
//
//    }
//
//    @Override
//    public void loop() {
//    }
//
//    private boolean isInRange(double value, int min, int max){
//        return(min < value && value < max);
//    }
//}
package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.systems.DriveTrain;

@Autonomous(name="Auto: Blue Alliance Right", group="Autonomous")
public class BlueAllianceRight extends OpMode {

    private final ElapsedTime runtime = new ElapsedTime();
    private final DriveTrain driveTrain = new DriveTrain(this);

    static final double     FORWARD_SPEED = 0.6;
    static final double     TURN_SPEED    = 0.5;

    public void init(){
        driveTrain.init();

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();
    }

    @Override
    public void start() {
        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way

//        // Step 1:  Drive forward for 1 seconds
//        driveTrain.setDrivePower(FORWARD_SPEED, FORWARD_SPEED);
//        runtime.reset();
//        while ((runtime.seconds() < 0.9)) {
//            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
//            telemetry.update();
//        }
//
//        // Step 1:  Spin right for 0.8 seconds
//        driveTrain.setDrivePower(-TURN_SPEED, TURN_SPEED);
//        runtime.reset();
//        while ((runtime.seconds() < 1.3)) {
//            telemetry.addData("Path", "Leg 2: %4.1f S Elapsed", runtime.seconds());
//            telemetry.update();
//        }
//
//        // Step 2:  Drive forward for 4 Second
//        driveTrain.setDrivePower(FORWARD_SPEED, FORWARD_SPEED);
//        runtime.reset();
//        while ((runtime.seconds() < 4.0)) {
//            telemetry.addData("Path", "Leg 2: %4.1f S Elapsed", runtime.seconds());
//            telemetry.update();
//        }
//
//        // Step 4:  Stop
//        driveTrain.setDrivePower(0, 0);

        telemetry.addData("Path", "Complete");
    }

    @Override
    public void loop() {

    }
}
