package Epsilon.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import Epsilon.Superclasses.Subsystem;

//Initializes all the motors/hardware for the drivetrain
public class Intake implements Subsystem {

    public DcMotor wheel;

    public void initialize(LinearOpMode opMode) {

        wheel = opMode.hardwareMap.dcMotor.get("intake");

        wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
