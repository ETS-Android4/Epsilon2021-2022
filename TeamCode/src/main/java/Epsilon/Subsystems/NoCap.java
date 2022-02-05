package Epsilon.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import Epsilon.Superclasses.Subsystem;

public class NoCap implements Subsystem {

    public CRServo tapeExtender;
    public CRServo pivot;
    public DcMotor tapeRotator;

    public void initialize(LinearOpMode opMode) {
        tapeExtender = opMode.hardwareMap.crservo.get("tapeExtender");
        pivot = opMode.hardwareMap.crservo.get("pivot");
        tapeRotator = opMode.hardwareMap.dcMotor.get("tapeRotator");

        tapeRotator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

}
