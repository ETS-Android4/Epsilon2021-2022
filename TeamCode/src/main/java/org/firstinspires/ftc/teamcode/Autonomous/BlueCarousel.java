package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Epsilon.OurRobot;
import Epsilon.Subsystems.Drivetrain;
import Epsilon.Subsystems.OpenCV;
import Epsilon.Subsystems.Outtake;

@Autonomous
public class BlueCarousel extends LinearOpMode {

    public void runOpMode() throws InterruptedException{
        OurRobot.initialize(this);

        Outtake.PosASH scorePos = OpenCV.Pipeline.getAnalysis();

        //Strafes left to shipping hub, turns 180, and pre loads
        OurRobot.drivetrain.Move(1, -10, Drivetrain.MoveType.STRAFE);
        OurRobot.imu.gyroTurn(0.5, 180);
        OurRobot.outtake.scoreASH(scorePos);

        //Drives back, strafes to carousel, turns it, parks backwards
        OurRobot.drivetrain.Move(1, 8, Drivetrain.MoveType.DRIVE);
        OurRobot.drivetrain.Move(1, -20, Drivetrain.MoveType.STRAFE);
        OurRobot.carousel.spin(1, 3);
        OurRobot.drivetrain.Move(1, -5, Drivetrain.MoveType.DRIVE);
    }
}
