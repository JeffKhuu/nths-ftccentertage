package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

import org.firstinspires.ftc.robotcore.external.Telemetry;


@TeleOp(name = "test")
public class test extends OpMode {

    @Override
    public void init(){
        telemetry.addData("Status", "Initialization is a success");
        telemetry.update();
    }

    @Override
    public void loop() {

    }

}


