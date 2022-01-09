package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import Epsilon.OurRobot;
import Epsilon.Subsystems.Drivetrain;
import Epsilon.Subsystems.OpenCV;
import Epsilon.Subsystems.Outtake;

@Autonomous
public class AutEpsilonTM extends LinearOpMode {
    OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
    @Override
    public void runOpMode() throws InterruptedException {

        //ASSUMING Y = front -> back, X = left -> right

        //Scan Duck or Cool Team Game Element
        robot.OpenCV.initialize(this);
        Outtake.posASH scorePos = OpenCV.Pipeline.getAnalysis();

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
            robot.outtake.scoreASH(Outtake.posASH.TOP);
            robot.drivetrain.Move(0,-2);
            robot.imu.gyroTurn(0.25,90);
            robot.drivetrain.Move(0,-7);
            robot.drivetrain.Move(0,21);
        }
    }
}
