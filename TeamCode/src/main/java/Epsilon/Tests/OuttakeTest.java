
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
        double servoPosition = 0.2;
        double verticalPosition = OurRobot.outtake.top.getCurrentPosition();
        waitForStart();

        OurRobot.outtake.bottom.setPosition(0.2);

        while (opModeIsActive()){

            if(gamepad1.dpad_up) {
                OurRobot.outtake.top.setPower(power);
                verticalPosition = OurRobot.outtake.top.getCurrentPosition();
            } else if(gamepad1.dpad_down) {
                OurRobot.outtake.top.setPower(-power/2);
                verticalPosition = OurRobot.outtake.top.getCurrentPosition();
            } else
                OurRobot.outtake.top.setPower(OurRobot.outtake.PID(verticalPosition));

                if (gamepad1.dpad_left && OurRobot.outtake.top.getCurrentPosition() > 300) {
                    servoPosition = 0.6;
                    OurRobot.outtake.bottom.setPosition(servoPosition);//PLACEHOLDER VALUE
                } else if (gamepad1.dpad_right) {
                    servoPosition = 0;
                    OurRobot.outtake.bottom.setPosition(servoPosition);//PLACEHOLDER VALUE
                }
            //else
            //    OurRobot.outtake.top.setPower(0.0);

            telemetry.addData("Horizontal Position", OurRobot.outtake.bottom.getPosition());
            telemetry.addData("Vertical Position", OurRobot.outtake.top.getCurrentPosition());
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
