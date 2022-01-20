package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import Epsilon.OurRobot;
import Epsilon.Subsystems.Outtake;
import Epsilon.Superclasses.EpsilonRobot;

@TeleOp
public class FinalTeleOp extends LinearOpMode{

    double drivetrainSpeed = -0.7;
    double intakeSpeed = 0.0;
    double carouselSpeed = 0.0;

    double outtakeX = 0.0;
    double outtakeY = 0.0;

    double lastVerticalPosition = OurRobot.outtake.upMotor.getCurrentPosition();



    @Override
    public void runOpMode() throws InterruptedException{

        //OurRobot robot = new OurRobot();    //creates instance of "OurRobot," giving it access to hardware/methods
        OurRobot.initialize(this);

        waitForStart();
        while(opModeIsActive()){
            /*************
             * GAMEPAD 1 *
             ************/
            //Drivetrain, Intake, Carousel

                /**************
                 * Drivetrain *
                 **************/
                //Run the drivetrain with three speed settings: slow, mid, and fast

                    if (gamepad1.right_trigger>0)
                        setDrivetrainSpeed(0.3);
                    else if (gamepad1.left_trigger>0)
                        setDrivetrainSpeed(1.0);
                    else
                        setDrivetrainSpeed(0.7);

                    runDrivetrain();

                /**********
                 * Intake *
                 **********/
                //Intake or eject game elements

                    if(gamepad1.left_bumper)
                        setIntakeSpeed(-0.75);
                    else if(gamepad1.right_bumper)
                        setIntakeSpeed(0.75);
                    else
                        setIntakeSpeed(0.0);

                    runIntake();

                /************
                 * Carousel *
                 ************/
                //Run the carousel forwards or backwards

                    if(gamepad1.x)
                        setCarouselSpeed(0.5);
                    else if(gamepad1.y)
                        setCarouselSpeed(-0.5);
                    else
                        setCarouselSpeed(0.0);

                    runCarousel();

            /*************
             * GAMEPAD 2 *
             *************/
            //Door, Outtake Slides, Outtake Finite State Machine

                /********
                 * Door *
                 ********/
                //Open or close the outtake door

                    boolean open;

                    if(gamepad2.right_bumper)
                        open = true;
                    else
                        open = false;

                    setDoor(open);

                /******************
                 * Manual Outtake *
                 ******************/
                //Set the outtake slides to a specific coordinate using controllers

                    runOuttakeVertical();
                    runOuttakeHorizontal();

                /***************
                 * Outtake FSM *
                 ***************/
                //Set the outtake slides to a specific coordinate autonomously


        }
    }

    public void runDrivetrain(){
        double x = -gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double r = -gamepad1.right_stick_x;

        OurRobot.drivetrain.frontLeft.setPower(drivetrainSpeed*(y-r-x));
        OurRobot.drivetrain.frontRight.setPower(drivetrainSpeed*(y+r+x));
        OurRobot.drivetrain.backLeft.setPower(drivetrainSpeed*(y-r+x));
        OurRobot.drivetrain.backRight.setPower(drivetrainSpeed*(y+r-x));
    }

    public void setDrivetrainSpeed(double speed){
        drivetrainSpeed = speed;
    }

    public void runIntake(){
        OurRobot.intake.wheel.setPower(intakeSpeed);
    }

    public void setCarouselSpeed(double speed){
        carouselSpeed = speed;
    }

    public void setIntakeSpeed(double speed){
        intakeSpeed = speed;
    }

    public void runCarousel(){
        OurRobot.carousel.duckMotor.setPower(carouselSpeed);
    }

    public void setDoor(boolean open){
        if(open)
            OurRobot.outtake.openDoor();
        else
            OurRobot.outtake.closeDoor();
    }

    public void runOuttakeVertical() {
        if (gamepad2.dpad_up) {
            OurRobot.outtake.upMotor.setPower(0.85);
            lastVerticalPosition = OurRobot.outtake.upMotor.getCurrentPosition();
        } else if (gamepad2.dpad_down) {
            OurRobot.outtake.upMotor.setPower(-0.3);
            lastVerticalPosition = OurRobot.outtake.upMotor.getCurrentPosition();
        } else
            OurRobot.outtake.upMotor.setPower(OurRobot.outtake.PID(lastVerticalPosition));
    }

    public void runOuttakeHorizontal(){
        if (gamepad2.dpad_left) {
            OurRobot.outtake.arm.setPosition(0.6);
        } else if (gamepad2.dpad_right) {
            OurRobot.outtake.arm.setPosition(0);
        }
    }
}
