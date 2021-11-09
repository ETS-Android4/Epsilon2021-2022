package Epsilon;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import Epsilon.Subsystems.Drivetrain;
import Epsilon.Superclasses.EpsilonRobot;
import Epsilon.Superclasses.Subsystem;

//"OurRobot" class creates instances of all the subsystem
//This class also contains most of the methods we'll use in auto

public class OurRobot implements EpsilonRobot {

    public Drivetrain drivetrain = new Drivetrain();

    private final Subsystem[] Subsystems = {drivetrain};    //Array for all the subsystems

    @Override
    // "initialize" method runs the "initialize" method in all the subsystems
    // Essentially declares/initializes all the motors and stuff
    public void initialize(LinearOpMode opMode) {
        for (Subsystem x : Subsystems){
            x.initialize(opMode);
        }
    }


}