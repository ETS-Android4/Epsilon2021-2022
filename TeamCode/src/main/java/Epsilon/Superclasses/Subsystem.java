package Epsilon.Superclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public interface Subsystem {
    public abstract void initialize(LinearOpMode opMode);
}

/*
 * This interface provides the initialize method for all other subsystems
 * This includes drivetrain, imu, etc.
 * */