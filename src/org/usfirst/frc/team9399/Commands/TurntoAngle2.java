package org.usfirst.frc.team9399.Commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team9399.Systems.Supersystem;
import org.usfirst.frc.team9399.Systems.Drivetrain;

public class TurntoAngle2 extends Command {
	private double targetAngle;

	private double timeout;
	public TurntoAngle2(double targetAngle, double timeout){
		this.targetAngle = targetAngle;

		this.timeout = timeout;
	}
	
	@Override
	protected void initialize() {
//		Supersystem.getInstance().reset(true);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		double angle = Supersystem.getInstance().ahrs.getAngle();
		Supersystem.getInstance().drive.updatedPositiveTurnAngle(angle, targetAngle);
		Supersystem.getInstance().gm.runGearManipulator(false, false, true, true);
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
