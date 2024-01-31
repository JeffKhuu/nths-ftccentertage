package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.systems.ActionExecutor;
import org.firstinspires.ftc.teamcode.systems.AutoOpMode;
import org.firstinspires.ftc.teamcode.systems.DriveTrain;
import org.firstinspires.ftc.teamcode.systems.RobotPath;
import org.firstinspires.ftc.teamcode.systems.TensorflowDetector;

import java.util.ArrayList;

@Autonomous(name="Auto: Blue Alliance Left", group="Autonomous")
public class BlueAllianceLeft extends LinearOpMode implements AutoOpMode  {

    private final ElapsedTime runtime = new ElapsedTime();
    private final ActionExecutor actionExecutor = new ActionExecutor(this, runtime);
    private final TensorflowDetector tfDetector = new TensorflowDetector(this);
    private final ArrayList<RobotPath> actions = new ArrayList<>();

    private final double TIME_TO_CHECK = 2.0;

    Side randomizationSide = Side.NONE;

    @Override
    public void runOpMode(){
        actionExecutor.init();
        TouchSensor touchSensor = hardwareMap.get(TouchSensor.class, "sensorTouch");

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");
        telemetry.update();


        waitForStart(); //Wait for start button to be pressed
        actionExecutor.runPath(new RobotPath(DriveTrain.FORWARD, 0.1, 1.0));

        pixelLoop:
        for(Side side : spikeMarks){
            runtime.reset();
            if(side == Side.LEFT){
                actionExecutor.runPath(new RobotPath(DriveTrain.SPIN_CCW, 0.5, 1.0));
                actionExecutor.runPath(new RobotPath(DriveTrain.FORWARD, 0.1, 0));
                while(runtime.seconds() < TIME_TO_CHECK && opModeIsActive()){
                    if(touchSensor.isPressed()){
                        randomizationSide = Side.LEFT;
                        break pixelLoop;
                    }else{
                        actionExecutor.runPath(new RobotPath(DriveTrain.BACKWARD, 0.1, 2));
                        actionExecutor.runPath(new RobotPath(DriveTrain.SPIN_CW, 0.5, 1.0));
                    }
                }
            }

            if(side == Side.CENTER){
                actionExecutor.runPath(new RobotPath(DriveTrain.FORWARD, 0.1, 0));
                while(runtime.seconds() < TIME_TO_CHECK && opModeIsActive()){
                    if(touchSensor.isPressed()){
                        randomizationSide = Side.LEFT;
                        break pixelLoop;
                    }else{
                        actionExecutor.runPath(new RobotPath(DriveTrain.BACKWARD, 0.1, 2));
                    }
                }
            }

            if(side == Side.RIGHT){
                actionExecutor.runPath(new RobotPath(DriveTrain.SPIN_CW, 0.5, 1.0));
                actionExecutor.runPath(new RobotPath(DriveTrain.FORWARD, 0.1, 0));
                while(runtime.seconds() < TIME_TO_CHECK && opModeIsActive()){
                    if(touchSensor.isPressed()){
                        randomizationSide = Side.LEFT;
                        break pixelLoop;
                    }else{
                        actionExecutor.runPath(new RobotPath(DriveTrain.BACKWARD, 0.1, 2));
                        actionExecutor.runPath(new RobotPath(DriveTrain.SPIN_CCW, 0.5, 1.0));
                    }
                }
            }
            
            if(randomizationSide != Side.NONE){
                telemetry.addData("Auto", "The pixel is " + randomizationSide.toString());
            }

        }






        actionExecutor.runPaths(actions);

    }

    private boolean isInRange(double value, int min, int max){
        return(min < value && value < max);
    }
}
