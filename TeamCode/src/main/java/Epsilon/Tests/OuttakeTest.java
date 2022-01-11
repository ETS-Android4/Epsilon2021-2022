
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
        double position = OurRobot.outtake.top.getCurrentPosition();
        while (opModeIsActive()) {
            double currentjoystick = gamepad1.right_stick_y;
            if (currentjoystick == 0) {
                OurRobot.outtake.top.setPower(OurRobot.outtake.PID(position));
            } else {
                OurRobot.outtake.top.setPower(gamepad1.right_stick_y);
                position = OurRobot.outtake.top.getCurrentPosition();
            }
            telemetry.addData("encoder counts", OurRobot.outtake.top.getCurrentPosition());
            telemetry.addData("Joystick value", gamepad1.right_stick_y);
            telemetry.addData("position", position);

            if(gamepad1.x)
                OurRobot.outtake.bottom.setPosition(0);//PLACEHOLDER VALUE
            else if(gamepad1.b)
                OurRobot.outtake.bottom.setPosition(1);//PLACEHOLDER VALUE

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
