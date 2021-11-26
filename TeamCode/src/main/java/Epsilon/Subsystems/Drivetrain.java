package Epsilon.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.Base64;

import Epsilon.Superclasses.Subsystem;

//Initializes all the motors/hardware for the drivetrain
public class Drivetrain implements Subsystem {

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public void initialize(LinearOpMode opMode) {

        frontLeft = opMode.hardwareMap.dcMotor.get("frontLeft");
        frontRight = opMode.hardwareMap.dcMotor.get("frontRight");
        backLeft = opMode.hardwareMap.dcMotor.get("backLeft");
        backRight = opMode.hardwareMap.dcMotor.get("backRight");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
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
                frontLeft.setPower(-1 * power);
                frontRight.setPower(power);
                backLeft.setPower(power);
                backRight.setPower(-1 * power);
                break;
            case TURN:
                frontLeft.setPower(-1 * power);
                frontRight.setPower(power);
                backLeft.setPower(-1 * power);
                backRight.setPower(power);
                break;
        }
    }

    public int INtoEC(int inches) {
        //Inches to Encoder Counts Stuff
        int EncoderCounts = inches;
        return EncoderCounts;
    }

    public void Move(double power, int inches, MoveType Type) {
        int EncoderCounts = INtoEC(inches);
        //Filler for setting Encoder Counts (this is for default motor encoders, not odo)
        frontLeft.setTargetPosition(EncoderCounts);
        frontRight.setTargetPosition(EncoderCounts);
        backLeft.setTargetPosition(EncoderCounts);
        backRight.setTargetPosition(EncoderCounts);
        //POWAAAAA
        Power(power, Type);
    }
}
