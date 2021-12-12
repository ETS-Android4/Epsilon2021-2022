package Epsilon.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Epsilon.OurRobot;
//import Epsilon.Subsystems.Odometry;

@TeleOp
public class EncoderTests extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException{

        OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
        robot.initialize(this);

        waitForStart();
        while (opModeIsActive()){
            telemetry.addData("Encoder Position", robot.Odometry.encoderX.getCurrentPosition());
            telemetry.update();
        }
    }
}
