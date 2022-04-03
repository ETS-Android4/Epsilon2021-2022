package Epsilon.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Epsilon.OurRobot;
import Epsilon.Subsystems.Drivetrain;

@Autonomous
public class DriveTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        OurRobot robot = new OurRobot();
        robot.initialize(this);

        waitForStart();
        OurRobot.drivetrain.Move(20, 0, this);
    }
}
