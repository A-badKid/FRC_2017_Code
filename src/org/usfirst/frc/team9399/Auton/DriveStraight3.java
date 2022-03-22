package org.usfirst.frc.team9399.Auton;

import org.usfirst.frc.team9399.Commands.BackwardDistancedStraightDrive;
import org.usfirst.frc.team9399.Commands.ForwardDistancedStraightDrive;
import org.usfirst.frc.team9399.Commands.NewResetSensors;
import org.usfirst.frc.team9399.Commands.PegGear;
import org.usfirst.frc.team9399.Commands.TurntoAngle;
import org.usfirst.frc.team9399.Commands.ResetSensors;
import org.usfirst.frc.team9399.Systems.Supersystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveStraight3 extends CommandGroup {
//	private double angle = Supersystem.getInstance().ahrs.getAngle(); 
//	private double pulse = Supersystem.getInstance().rightenc.getDistance();
	
	public DriveStraight3(){
//		this.addSequential(new NewResetSensors(1.0));
//		this.addSequential(new BackwardDistancedStraightDrive(80,3.0));
//		this.addSequential(new TurntoAngle(-60,2.0));
//		this.addSequential(new NewResetSensors(1.0));
//		this.addSequential(new BackwardDistancedStraightDrive(26.50,3.0)); // was 5 seconds for testing
//		this.addSequential(new PegGear(1.0));
	}

}
