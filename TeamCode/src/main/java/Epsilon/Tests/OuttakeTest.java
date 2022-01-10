
package Epsilon.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Epsilon.OurRobot;
import Epsilon.Superclasses.EpsilonRobot;

@TeleOp
public class OuttakeTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException{


        //OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
        OurRobot.initialize(this);
        double power = 0.6;
        waitForStart();

        while (opModeIsActive()){
            if(gamepad1.y && gamepad1.right_bumper)
                OurRobot.outtake.top.setPower(-0.6);
            if(gamepad1.y)
                OurRobot.outtake.top.setPower(0.6);
            if(gamepad1.x)
                OurRobot.outtake.bottom.setPosition(0);//PLACEHOLDER VALUE
            if(gamepad1.b)
                OurRobot.outtake.bottom.setPosition(1);//PLACEHOLDER VALUE
            else
                OurRobot.outtake.top.setPower(0.0);

            telemetry.addData("servo", OurRobot.outtake.bottom.getPosition());
            telemetry.addData("b", gamepad1.b);
            telemetry.addData("x", gamepad1.x);
            telemetry.update();
            /*
            double speed = 0.5;
            double horizontal = 0.0;
            double vertical = 0.0;

            robot.outtake.bottom.setPower(gamepad1.left_stick_y);
            robot.outtake.top.setPower(gamepad1.left_stick_x);
            */
            //robot.outtake.extendo(horizontal, vertical, speed);


        }
    }
}
