package Epsilon.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

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

    }
}
