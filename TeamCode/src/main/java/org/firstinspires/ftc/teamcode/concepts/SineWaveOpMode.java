package org.firstinspires.ftc.teamcode.concepts;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/*
 * Sine wave sample to demonstrate telemetry and config variables in action. Adjust the amplitude,
 * phase, and frequency of the oscillation and watch the changes propagate immediately to the graph.
 */


@Autonomous
@Disabled
public class SineWaveOpMode extends LinearOpMode {
    public static double AMPLITUDE = 10;
    public static double PHASE = 90;
    public static double FREQUENCY = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {
    }

}
