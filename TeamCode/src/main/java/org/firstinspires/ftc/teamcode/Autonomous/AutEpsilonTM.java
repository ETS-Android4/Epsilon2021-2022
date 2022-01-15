package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import Epsilon.OurRobot;
import Epsilon.Subsystems.Drivetrain;
import Epsilon.Subsystems.OpenCV;
import Epsilon.Subsystems.Outtake;

@Autonomous
public class AutEpsilonTM extends LinearOpMode {
    OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
    @Override
    public void runOpMode() throws InterruptedException {
        robot.initialize(this);
        waitForStart();

        //robot.drivetrain.Move(1, 5, Drivetrain.MoveType.DRIVE);
        //robot.imu.gyroTurn(0.25, 90);
       //robot.drivetrain.Move(1, -5. Drivetrain.MoveType.DRIVE);
        robot.drivetrain.Move(0.5,-15, Drivetrain.MoveType.DRIVE);
        robot.drivetrain.Move(0.5, 34, Drivetrain.MoveType.STRAFE);
        robot.drivetrain.Move(0.5, 13, Drivetrain.MoveType.DRIVE);
        robot.wait(2000);
        robot.carousel.duckMotor.setPower(0.5);
        robot.wait(5000);
        robot.carousel.duckMotor.setPower(0.0);
        robot.drivetrain.Move(0.5, -23, Drivetrain.MoveType.DRIVE);

/*
        //ASSUMING Y = front -> back, X = left -> righ

        //Scan Duck or Cool Team Game Element
        robot.OpenCV.initialize(this);
        Outtake.PosASH3a scorePos = OpenCV.Pipeline.getAnalysis();

        //Carousel
        for(int i = 0; i < 1; i++) {
            robot.drivetrain.Move(-18, 0);
            robot.carousel.spin(1, 360);
        }

        robot.drivetrain.Move(36, 0);
        robot.drivetrain.Move(0, 9);

        //ASH (Alliance Shipping Hub) Scoring
        robot.outtake.scoreASH(scorePos);

        //ASH Cycling
        robot.drivetrain.Move(0,-2);
        robot.imu.gyroTurn(0.25,90);
        robot.drivetrain.Move(0,-7);
        robot.drivetrain.Move(0,21);
        for (int i = 0; i < 3; i++) {
            robot.intake.eat(1, 360);
            robot.drivetrain.Move(0,-21);
            robot.drivetrain.Move(7,0);
            robot.imu.gyroTurn(-1, -90);
            robot.drivetrain.Move(0,2);
            robot.outtake.scoreASH(Outtake.PosASH.TOP);
            robot.drivetrain.Move(0,-2);
            robot.imu.gyroTurn(0.25,90);
            robot.drivetrain.Move(0,-7);
            robot.drivetrain.Move(0,21);
        }
 */
    }
}
