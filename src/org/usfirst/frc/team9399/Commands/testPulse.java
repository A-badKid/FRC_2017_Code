package org.usfirst.frc.team9399.Commands;

import org.usfirst.frc.team9399.Systems.Supersystem;

import edu.wpi.first.wpilibj.command.Command;

public class testPulse extends Command {
	private double Pulses;
	private double Angle;
	private double Distance;
	private double Timeout;
	public testPulse(double pulse, double time){
		Pulses = pulse;
		Timeout = time;
		
	}
	
	@Override
	protected void initialize() {
		
		System.out.println(Pulses);
		setTimeout(Timeout);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return this.isTimedOut();
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

}
