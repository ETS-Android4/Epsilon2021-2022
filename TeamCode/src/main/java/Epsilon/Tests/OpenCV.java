package Epsilon.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Epsilon.OurRobot;
import Epsilon.Subsystems.Outtake;

@TeleOp
public class OpenCV extends LinearOpMode {
    OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods

    @Override
    public void runOpMode() throws InterruptedException {
        robot.initialize(this);
        waitForStart();
        while (opModeIsActive()) {
            Outtake.PosASH scorePos = Epsilon.Subsystems.OpenCV.Pipeline.getAnalysis();
            telemetry.addData("OpenCV Pos: ", scorePos);
            telemetry.update();
        }
    }
}
