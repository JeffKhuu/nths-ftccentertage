package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.systems.ActionExecutor;
import org.firstinspires.ftc.teamcode.systems.AutoOpMode;
import org.firstinspires.ftc.teamcode.systems.DriveTrain;
import org.firstinspires.ftc.teamcode.systems.RobotPath;
import org.firstinspires.ftc.teamcode.systems.TensorflowDetector;

import java.util.ArrayList;
import java.util.List;

@Autonomous(name="Auto: Blue Alliance Right", group="Autonomous")
public class BlueAllianceRight extends LinearOpMode implements AutoOpMode {

    private final ElapsedTime runtime = new ElapsedTime();
    private final ActionExecutor actionExecutor = new ActionExecutor(this, runtime);
    private final TensorflowDetector tfDetector = new TensorflowDetector(this);
    private final ArrayList<RobotPath> actions = new ArrayList<>();

    private Side randomizationSide = Side.NONE;
    @Override
    public void runOpMode(){
        actionExecutor.init();
        tfDetector.init();

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart(); //Wait for start button to be pressed


        for(Side side : spikeMarks){ //For each spike mark indicator:
            List<Recognition> currentRecognitions = tfDetector.getRecognitions();

            //Drive forward allowing the camera to see the pixel
            actionExecutor.runPath(new RobotPath(DriveTrain.FORWARD, 0.5, 1.0));

            //Move and check the right side for the pixel
            if(side == Side.RIGHT){
                actionExecutor.runPath(new RobotPath(DriveTrain.RIGHT, 0.5, 2.0));
                currentRecognitions = tfDetector.getRecognitions();
            }

            //Move and check the center for the pixel
            else if(side == Side.CENTER){
                actionExecutor.runPath(new RobotPath(DriveTrain.LEFT, 0.5, 2.0));
                currentRecognitions = tfDetector.getRecognitions();
            }

            //Move and check the left side for the pixel
            else if(side == Side.LEFT){
                actionExecutor.runPath(new RobotPath(DriveTrain.LEFT, 0.5, 2.0));
                currentRecognitions = tfDetector.getRecognitions();
            }

            //If the pixel was found, break out the for loop
            if(currentRecognitions.size() != 0){
                randomizationSide = side;
                break;
            }
        }

        if(randomizationSide != Side.NONE){
            telemetry.addData("Randomization", "PIXEL FOUND");
        }else{
            telemetry.addData("Randomization", "The pixel was not found.");
            telemetryTfod();
        }
        telemetry.update();




        //Drive to the backstage
        //actionExecutor.runPaths(actions);

    }

    private boolean isInRange(double value, int min, int max){
        return(min < value && value < max);
    }

    private void telemetryTfod() {

        List<Recognition> currentRecognitions = tfDetector.getRecognitions();
        telemetry.addData("# Objects Detected", currentRecognitions.size());

        // Step through the list of recognitions and display info for each one.
        for (Recognition recognition : currentRecognitions) {
            double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
            double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;

            telemetry.addData(""," ");
            telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
            telemetry.addData("- Position", "%.0f / %.0f", x, y);
            telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());
        }   // end for() loop
        telemetry.update();

    }
}
