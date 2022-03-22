package org.usfirst.frc.team9399.Commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team9399.Systems.Supersystem;
import org.usfirst.frc.team9399.Systems.Drivetrain;



public class ForwardDistancedStraightDrive extends Command {
	private double leftpower;
	private double rightpower;
	private double targetDistance;
	private double currentAngle;
	private double pulse;
	private double subtract;
	private double decaynum;
	private double timeout;
	
	public ForwardDistancedStraightDrive(double lpow, double rpow, double distance, double currentangle, double pulses,double subtract,double decayNum, double timeout){
		this.leftpower = lpow;
		this.rightpower = rpow;
		this.targetDistance = distance;
		this.currentAngle = currentangle;
		this.decaynum = decayNum;
		this.pulse = pulses;
		this.subtract = subtract;
		this.timeout = timeout;
		
	}
	
	@Override
	protected void initialize() {
		Supersystem.getInstance().drive.distancedBackwardStraightDrive(leftpower, rightpower, targetDistance, currentAngle, pulse, subtract, decaynum);
		setTimeout(timeout);
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
