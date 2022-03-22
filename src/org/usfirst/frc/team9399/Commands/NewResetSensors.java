package org.usfirst.frc.team9399.Commands;

import org.usfirst.frc.team9399.Systems.Supersystem;

import edu.wpi.first.wpilibj.command.Command;

public class NewResetSensors extends Command {
	double timeout;
	
	public NewResetSensors(double time){
		timeout = time;
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		//currently gets stuck on this Command
		Supersystem.getInstance().rightenc.reset();
		System.out.println("Pulses:" + Supersystem.getInstance().rightenc.getDistance() + "Angle:" + Supersystem.getInstance().ahrs.getAngle());
		setTimeout(timeout);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void end() {
		Supersystem.getInstance().drive.tankDrive(0.0,0.0);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		Supersystem.getInstance().drive.tankDrive(0.0,0.0);
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return this.isTimedOut();
	}

}
