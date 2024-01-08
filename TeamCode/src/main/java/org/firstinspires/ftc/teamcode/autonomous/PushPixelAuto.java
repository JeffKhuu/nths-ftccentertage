package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

@Autonomous(name = "Auto: Push Pixel TF", group = "Autonomous")
public class PushPixelAuto extends LinearOpMode {
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor leftBackMotor;
    private DcMotor rightBackMotor;
    private static final boolean USE_WEBCAM = true;

    private static final String TFOD_MODEL_ASSET = "CenterStage.tflite";
    private static final String[] LABELS = {"Pixel"};

    private TfodProcessor tfod;
    private VisionPortal visionPortal;

    private static final double BASE_SPEED = 0.5;
    private static final double CONFIDENCE_THRESHOLD = 0.6;
    private double PixelConfid = 0;

    @Override
    public void runOpMode() {
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        leftBackMotor = hardwareMap.get(DcMotor.class, "leftBackMotor");
        rightBackMotor = hardwareMap.get(DcMotor.class, "rightBackMotor");

        initTfod();

        telemetry.addData(">", "Press Play to start OpMode");
        telemetry.update();
        waitForStart();

        //Moves to the left spikemark to detect
        leftBackMotor.setPower(BASE_SPEED);
        rightBackMotor.setPower(-BASE_SPEED);
        leftMotor.setPower(-BASE_SPEED);
        rightMotor.setPower(BASE_SPEED);
        sleep(500);

        //Stops it infront of left spike
        leftBackMotor.setPower(0);
        rightBackMotor.setPower(0);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        sleep(10000);
        //detects
        telemetryTfod();
        detectPixel();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                telemetryTfod();
                detectPixel();

                // ONLY WORKS IF PIKEMARK TO THE LEFT IS CLEAR WITH NO BRAKCET.
                if (PixelConfid > CONFIDENCE_THRESHOLD) {
                    // Move forward
                    leftBackMotor.setPower(BASE_SPEED);
                    rightBackMotor.setPower(BASE_SPEED);
                    sleep(400);

                    // Stop the robot
                    leftBackMotor.setPower(0);
                    rightBackMotor.setPower(0);

                    // Perform additional actions based on the detected pixel, if needed
                    // Example: Rotate right for 1 second

                    /* leftBackMotor.setPower(BASE_SPEED);
                    rightBackMotor.setPower(-BASE_SPEED);
                    sleep(1000);

                    // Stop the robot
                    leftBackMotor.setPower(0);
                    rightBackMotor.setPower(0);
                    */

                    // Add any other necessary actions based on the pixel detection

                    // Reset PixelConfid to avoid repeated actions until the pixel is detected again
                    PixelConfid = 0;

                } else {

                    // move to the center between spikemarks/middle
                    leftBackMotor.setPower(-BASE_SPEED);
                    rightBackMotor.setPower(BASE_SPEED);
                    leftMotor.setPower(BASE_SPEED);
                    rightMotor.setPower(-BASE_SPEED);
                    sleep(500);
                    leftMotor.setPower(0);
                    rightMotor.setPower(0);
                    leftBackMotor.setPower(0);
                    rightBackMotor.setPower(0);
                    sleep(500);
                    //moves forward
                    leftBackMotor.setPower(BASE_SPEED);
                    rightBackMotor.setPower(BASE_SPEED);
                    sleep(800);
                    leftBackMotor.setPower(0);
                    rightBackMotor.setPower(0);
                    telemetryTfod();
                    detectPixel();
                    // Add any other necessary actions when no pixel is detected
                }
                // Other code...

                telemetry.update();
            }
        }

        visionPortal.close();
    }

    private void detectPixel() {
        for (Recognition recognition : tfod.getRecognitions()) {
            if (recognition.getLabel().equals("Pixel") &&
                    recognition.getConfidence() > CONFIDENCE_THRESHOLD &&
                    isOnRedOrBlueTape(recognition)) {

                // Update PixelConfid with the confidence of the detected pixel
                PixelConfid = recognition.getConfidence();
            }
        }
    }

    private void initTfod() {
        // Create the TensorFlow processor by using a builder.
        tfod = new TfodProcessor.Builder()
                // With the following lines commented out, the default TfodProcessor Builder
                // will load the default model for the season. To define a custom model to load,
                // choose one of the following:
                //   Use setModelAssetName() if the custom TF Model is built in as an asset (AS only).
                //   Use setModelFileName() if you have downloaded a custom team model to the Robot Controller.
                //.setModelAssetName(TFOD_MODEL_ASSET)
                //.setModelFileName(TFOD_MODEL_FILE)




                // The following default settings are available to un-comment and edit as needed to
                // set parameters for custom models.
                .setModelLabels(LABELS)
                .setIsModelTensorFlow2(true)
                .setIsModelQuantized(true)
                .setModelInputSize(300)
                .setModelAspectRatio(16.0 / 9.0)
                .build();
        // Create the vision portal by using a builder.
        VisionPortal.Builder builder = new VisionPortal.Builder();
        // Set the camera (webcam vs. built-in RC phone camera).
        if (USE_WEBCAM) {
            builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
        } else {
            builder.setCamera(BuiltinCameraDirection.BACK);
        }
        // Choose a camera resolution. Not all cameras support all resolutions.
        //builder.setCameraResolution(new Size(640, 480));

        // Enable the RC preview (LiveView).  Set "false" to omit camera monitoring.
        builder.enableLiveView(true);

        // Set the stream format; MJPEG uses less bandwidth than default YUY2.
        //builder.setStreamFormat(VisionPortal.StreamFormat.YUY2);

        // Choose whether or not LiveView stops if no processors are enabled.
        // If set "true", monitor shows solid orange screen if no processors enabled.
        // If set "false", monitor shows camera view without annotations.
        builder.setAutoStopLiveView(true);

        // Set and enable the processor.
        builder.addProcessor(tfod);
        // Build the Vision Portal, using the above settings.
        visionPortal = builder.build();
        // Set confidence threshold for TFOD recognitions, at any time.
        tfod.setMinResultConfidence(0.50f);

        // Disable or re-enable the TFOD processor at any time.
        visionPortal.setProcessorEnabled(tfod, true);
    }

    private void telemetryTfod() {
        List<Recognition> currentRecognitions = tfod.getRecognitions();
        telemetry.addData("# Objects Detected", currentRecognitions.size());

        for (Recognition recognition : currentRecognitions) {
            double x = (recognition.getLeft() + recognition.getRight()) / 2;
            double y = (recognition.getTop() + recognition.getBottom()) / 2;

            telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
            telemetry.addData("- Position", "%.0f / %.0f", x, y);
            telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());
        }
    }

    private boolean isOnRedOrBlueTape(Recognition recognition) {
        // Implement logic to determine if the pixel is on red or blue tape
        // Modify this method based on your specific scenario
        // Return true if on red or blue tape, false otherwise
        return true; // Placeholder, modify accordingly
    }
}

