package org.firstinspires.ftc.teamcode.systems;

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
