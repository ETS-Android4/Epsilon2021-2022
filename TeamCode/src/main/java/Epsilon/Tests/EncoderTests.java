package Epsilon.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Epsilon.OurRobot;
//import Epsilon.Subsystems.Odometry;

@Autonomous
public class EncoderTests extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException{

        OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
        robot.initialize(this);

        waitForStart();
        //while (opModeIsActive()){
            robot.drivetrain.Move(10,0);
            telemetry.addData("Encoder Position", robot.Odometry.encoderToInch(OurRobot.drivetrain.backLeft.getCurrentPosition()));
            telemetry.addData("EncoderX", OurRobot.Odometry.XChange);
            telemetry.addData("EncoderY", OurRobot.Odometry.YChange);
            robot.Odometry.update();
            telemetry.update();


        //}
    }
}
