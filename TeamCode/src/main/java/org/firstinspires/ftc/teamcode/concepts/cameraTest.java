package org.firstinspires.ftc.teamcode.concepts;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.function.Consumer;
import org.firstinspires.ftc.robotcore.external.function.Continuation;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamSource;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.teamcode.concepts.VisionPortalStreamingOpMode;
import org.firstinspires.ftc.teamcode.systems.AprilTagDetector;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.opencv.core.Mat;

import java.util.List;
import java.util.Locale;


@TeleOp(name = "DetectAprilTag", group = "Vision")
public class cameraTest extends OpMode {
    AprilTagDetector detector = new AprilTagDetector(this);;

    @Override
    public void init() {
        final VisionPortalStreamingOpMode.CameraStreamProcessor processor = new VisionPortalStreamingOpMode.CameraStreamProcessor();
        detector.init();
    }
    @Override
    public void loop() {
        List<AprilTagDetection> detections = detector.getDetections();
        telemetry.addData("# AprilTags Detected", detections.size());

        for(AprilTagDetection detection : detections){
            telemetry.addLine(String.format(Locale.ENGLISH, "\n==== (ID %d) %s", detection.id, detection.metadata.name));
            telemetry.addLine(String.format(Locale.ENGLISH, "PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
            telemetry.addData("X", detection.ftcPose.x);
        }
    }
}


