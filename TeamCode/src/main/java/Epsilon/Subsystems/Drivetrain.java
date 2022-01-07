package Epsilon.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Base64;

import Epsilon.OurRobot;
import Epsilon.Superclasses.Subsystem;

//Initializes all the motors/hardware for the drivetrain
public class Drivetrain implements Subsystem {

    Odometry odo = OurRobot.Odometry;

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    //PID constants - will be tuned to different values
    private double kP = 0;
    private double kI = 0;
    private double kD = 0;

    public void initialize(LinearOpMode opMode) {

        frontLeft = opMode.hardwareMap.dcMotor.get("frontLeft");
        frontRight = opMode.hardwareMap.dcMotor.get("frontRight");
        backLeft = opMode.hardwareMap.dcMotor.get("backLeft");
        backRight = opMode.hardwareMap.dcMotor.get("backRight");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        //Run without encoders because we'll probably be using Odo encoders
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /*********************
     * Basic Move Methods
     ********************/

    public enum MoveType {
        DRIVE,
        STRAFE,
        TURN
    }
    public void Power(double power, MoveType Type) {
        switch (Type) {
            case DRIVE:
                frontLeft.setPower(power);
                frontRight.setPower(power);
                backLeft.setPower(power);
                backRight.setPower(power);
                break;
            case STRAFE:
                frontLeft.setPower(-power);
                frontRight.setPower(power);
                backLeft.setPower(power);
                backRight.setPower(-power);
                break;
            case TURN:
                frontLeft.setPower(-power);
                frontRight.setPower(power);
                backLeft.setPower(-power);
                backRight.setPower(power);
                break;
        }
    }

    public double INtoEC(double inches) {       //Hey Jacob I changed from int to double
        //Inches to Encoder Counts Stuff
        double EncoderCounts = 537.6*(inches/12.3622);
        return EncoderCounts;
    }

    public void Move(double power, int inches, MoveType Type) {
        double EncoderCounts = INtoEC(inches);
        frontLeft.setTargetPosition((int) EncoderCounts);
        frontRight.setTargetPosition((int) EncoderCounts);
        backLeft.setTargetPosition((int) EncoderCounts);
        backRight.setTargetPosition((int) EncoderCounts);
        Power(power, Type);
    }

    /*******************
     * PID Stuff Woohoo
     ******************/

    public void resetEncoderPos(){
        //odo.encoderX.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //odo.encoderX.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //odo.encoderY.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //odo.encoderY.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //might not even be necessary
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    //Basic PID method for linear/lateral movement
    public void Move(double inchesX, double inchesY, MoveType Type){

        double targetX = INtoEC(inchesX);
        double targetY = INtoEC(inchesY);
        double currentPosX = odo.encoderX.getCurrentPosition();
        double currentPosY = odo.encoderY.getCurrentPosition();
        double lastErrorX = 0;
        double lastErrorY = 0;
        double integralSumX = 0;
        double integralSumY = 0;

        ElapsedTime timer = new ElapsedTime();
        while (targetX - currentPosX > 0 || targetY - currentPosY > 0){

            odo.update();

            currentPosX = odo.xPos;
            currentPosY = odo.yPos;

            //calculate the error
            double errorX = targetX - currentPosX;
            double errorY = targetY - currentPosY;

            //ROC of the error
            double derivativeX = (errorX - lastErrorX) / timer.seconds();
            double derivativeY = (errorY - lastErrorY) / timer.seconds();

            //sum of all error over time
            integralSumX = integralSumX + (errorX*timer.seconds());
            integralSumY = integralSumY + (errorY*timer.seconds());

            double powerX = (kP*errorX) + (kI*integralSumX) + (kD*derivativeX);
            double powerY = (kP*errorY) + (kI*integralSumY) + (kD*derivativeY);

            //Power(power, Type);

            frontLeft.setPower(powerY+powerX);
            backLeft.setPower(powerY-powerX);
            frontRight.setPower(powerY-powerX);
            backRight.setPower(powerY+powerX);

            lastErrorX = errorX;
            lastErrorY = errorY;
            timer.reset();
        }
    }

}
