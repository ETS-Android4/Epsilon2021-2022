package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import Epsilon.OurRobot;
import Epsilon.Subsystems.Outtake;
import Epsilon.Superclasses.EpsilonRobot;

@TeleOp
public class TestFinalTeleOp extends LinearOpMode {
    public enum OuttakeState{
        OUTTAKE_INIT,
        VERTICAL_EXTEND,
        HORIZONTAL_EXTEND,
        VERTICAL_RETRACT,
        HORIZONTAL_RETRACT
    }
    @Override
    public void runOpMode() throws InterruptedException{

        //OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
        OurRobot.initialize(this);

        double verticalPosition = OurRobot.outtake.upMotor.getCurrentPosition();

        waitForStart();

        double speed = 0.7;
        ElapsedTime time = new ElapsedTime();
        boolean verticalActive = false;
        boolean horizontalActive = false;

        boolean doorToggle = true;
        boolean lastBumperState = false;

        double outtakeInitTime = time.milliseconds();
        double wheelSpeed = 0.9;

        OuttakeState outtakeState = OuttakeState.OUTTAKE_INIT;

        int level = OurRobot.outtake.FLOOR;

        while (opModeIsActive()){

            /*****************
             * Drive Formula *
             ****************/
            double y = gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double r = gamepad1.right_stick_x;

            OurRobot.drivetrain.frontLeft.setPower(speed*(y-r-x));
            OurRobot.drivetrain.frontRight.setPower(speed*(y+r+x));
            OurRobot.drivetrain.backLeft.setPower(speed*(y-r+x));
            OurRobot.drivetrain.backRight.setPower(speed*(y+r-x));

            if (gamepad1.right_trigger>0)           //slowmode
                speed = 0.3;
            else if (gamepad1.left_trigger>0)       //fastmode
                speed = 1.0;
            else
                speed = 0.7;

            /**********
             * Intake *
             **********/
            if(gamepad1.right_bumper)
                wheelSpeed = 0.75;

            if(gamepad1.left_bumper)
                OurRobot.intake.wheel.setPower(-wheelSpeed);
            else if (gamepad1.right_bumper)
                OurRobot.intake.wheel.setPower(wheelSpeed);
            else
                OurRobot.intake.wheel.setPower(0.0);

            /***********
             * Outtake *
             **********/
            //dpad control of the linear slides
            if(gamepad2.dpad_up) {
                OurRobot.outtake.upMotor.setPower(0.85);
                verticalPosition = OurRobot.outtake.upMotor.getCurrentPosition();
            } else if(gamepad2.dpad_down) {
                OurRobot.outtake.upMotor.setPower(-0.3);
                verticalPosition = OurRobot.outtake.upMotor.getCurrentPosition();
            } else
                OurRobot.outtake.upMotor.setPower(OurRobot.outtake.PID(verticalPosition));

            if (gamepad2.dpad_left && OurRobot.outtake.upMotor.getCurrentPosition() > 300) {
                OurRobot.outtake.arm.setPosition(0.6);
            } else if (gamepad2.dpad_right) {
                OurRobot.outtake.arm.setPosition(0);
            }

            /************************
             * Finite State Machine *
             ***********************/
            switch (outtakeState){
                case OUTTAKE_INIT:
                        if (gamepad2.x) {
                            level = OurRobot.outtake.ASH_TOP;
                            outtakeState = OuttakeState.VERTICAL_EXTEND;
                        } else if (gamepad2.a) {
                            level = OurRobot.outtake.ASH_MID;
                            outtakeState = OuttakeState.VERTICAL_EXTEND;
                        } else if (gamepad2.y) {
                            level = OurRobot.outtake.ASH_BOTTOM;
                            outtakeState = OuttakeState.VERTICAL_EXTEND;
                        }
                    break;
                case VERTICAL_EXTEND:
                    outtakeInitTime = time.milliseconds();
                    //OurRobot.outtake.setVertical(0.6,level);
                    OurRobot.outtake.upMotor.setPower(0.6);
                    OurRobot.outtake.upMotor.setTargetPosition(level);
                    outtakeState = OuttakeState.HORIZONTAL_EXTEND;
                    break;
                case HORIZONTAL_EXTEND:
                    if(time.milliseconds() > outtakeInitTime + 2000) {
                        OurRobot.outtake.setHorizontal(OurRobot.outtake.ARM_EXTEND);
                        outtakeState = OuttakeState.HORIZONTAL_RETRACT;
                    }
                    break;
                case HORIZONTAL_RETRACT:
                    if(time.milliseconds() > outtakeInitTime + 4000) {
                        OurRobot.outtake.setHorizontal(OurRobot.outtake.ARM_RETRACT);
                        outtakeState = OuttakeState.VERTICAL_RETRACT;
                    }
                    break;
                case VERTICAL_RETRACT:
                    if(time.milliseconds() > outtakeInitTime + 6000) {
                        OurRobot.outtake.setVertical(0.6, OurRobot.outtake.FLOOR);
                        outtakeState = OuttakeState.OUTTAKE_INIT;
                    }
                    break;
                default:
                    outtakeState = OuttakeState.OUTTAKE_INIT;
            }
            telemetry.addData("level",level);
            telemetry.addData("outtake fsm",outtakeState);

            /*******************
             * Door and Toggle *
             ******************/
            /*
            boolean currentRightBumper = gamepad2.right_bumper;
            //If statement to confirm that the bumper really changed values between while loops
            if (currentRightBumper && !lastBumperState){
                doorToggle = !doorToggle;
            }
            lastBumperState = currentRightBumper;

            if (doorToggle) {
                OurRobot.outtake.closeDoor();
            } else if(!doorToggle) {
                OurRobot.outtake.openDoor();
            }
            */
            if (gamepad2.right_bumper)
                OurRobot.outtake.openDoor();
            else
                OurRobot.outtake.closeDoor();

            /************
             * Carousel *
             ***********/
            if(gamepad1.x)
                OurRobot.carousel.duckMotor.setPower(0.5);
            else if(gamepad1.y)
                OurRobot.carousel.duckMotor.setPower(-0.5);
            else
                OurRobot.carousel.duckMotor.setPower(0.0);

            telemetry.addData("Door Toggle", doorToggle);
            telemetry.addData("Horizontal",OurRobot.outtake.arm.getPosition());
            telemetry.addData("Vertical",OurRobot.outtake.upMotor.getCurrentPosition());
            telemetry.update();


          /*  OurRobot.Odometry.update();
            telemetry.addData("XPosition", OurRobot.Odometry.xPos);
            telemetry.addData("YPosition", OurRobot.Odometry.yPos);
            telemetry.addData("Angle", Math.toDegrees(OurRobot.Odometry.heading));
            telemetry.update(); */
        }
    }


}
