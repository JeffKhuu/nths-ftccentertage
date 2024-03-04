package org.firstinspires.ftc.teamcode;

public interface AutoOpMode {
    enum Side {
        LEFT,
        CENTER,
        RIGHT,
        NONE
    }

    Side[] spikeMarks = {Side.LEFT, Side.CENTER, Side.RIGHT};

    // !! IMPLEMENT ABSTRACT METHOD TO DRIVE TO BACKSTAGE !! //

}
