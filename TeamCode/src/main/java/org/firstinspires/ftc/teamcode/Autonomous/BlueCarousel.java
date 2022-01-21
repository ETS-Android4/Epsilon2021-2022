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

        //scan
        Outtake.PosASH scorePos = OpenCV.Pipeline.getAnalysis();
        telemetry.addData("OpenCV Pos: ", scorePos);
        telemetry.update();

        waitForStart();

        //drive to ASH
        OurRobot.drivetrain.Move(0.5, -29, Drivetrain.MoveType.STRAFE, this);
        OurRobot.drivetrain.Move(0.5, -18, Drivetrain.MoveType.DRIVE, this);

        //score
        OurRobot.outtake.scoreASH(scorePos);

        //OurRobot.CycleFreight(this);

        //drive back, go to carousel
        OurRobot.wait(500, this);
        OurRobot.drivetrain.Move(0.5, 17, Drivetrain.MoveType.DRIVE, this);
        OurRobot.drivetrain.Move(0.2, -5, Drivetrain.MoveType.DRIVE, this);
        //OurRobot.drivetrain.Move(0.5,-15, Drivetrain.MoveType.DRIVE);
        OurRobot.drivetrain.Move(0.5, 58, Drivetrain.MoveType.STRAFE, this);

        OurRobot.carousel.duckMotor.setPower(0.5);
        OurRobot.wait(5000, this);
        OurRobot.carousel.duckMotor.setPower(0.0);

        OurRobot.drivetrain.Move(0.5, -20, Drivetrain.MoveType.DRIVE, this);
        /*
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
        */
    }
}
