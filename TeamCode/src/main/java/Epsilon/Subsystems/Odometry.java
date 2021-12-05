package Epsilon.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import Epsilon.Superclasses.Subsystem;

public class Odometry implements Subsystem {
    //public Dc<p>
    public DcMotor encoderX;

    public void initialize (LinearOpMode opMode) {
        encoderX = opMode.hardwareMap.dcMotor.get("encoderX");
    }
}
