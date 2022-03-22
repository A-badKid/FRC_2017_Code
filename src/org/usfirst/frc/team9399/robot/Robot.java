package org.usfirst.frc.team9399.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;


import jaci.pathfinder.modifiers.TankModifier;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team9399.Config.Ports;
import org.usfirst.frc.team9399.Systems.Supersystem;
import org.usfirst.frc.team9399.Systems.GearPickup;
import org.usfirst.frc.team9399.Auton.DriveStraight2;
import org.usfirst.frc.team9399.Auton.DriveStraight3;
import org.usfirst.frc.team9399.Auton.Test;
import org.usfirst.frc.team9399.Auton.TestTurn;
import org.usfirst.frc.team9399.Auton.DoNothing;
import org.usfirst.frc.team9399.Auton.DriveStraight1;
import org.usfirst.frc.team9399.Commands.PegGear;
import org.usfirst.frc.team9399.Commands.TurntoAngle;



public class Robot extends IterativeRobot implements PIDOutput {
	SendableChooser autonchooser = new SendableChooser();
	Command autonomousCommand;
	
	Supersystem Bot = Supersystem.getInstance();
	Joystick Driverleft = new Joystick(Ports.Controls.Driver_Joystick_1);
	Joystick Driverright = new Joystick(Ports.Controls.Driver_Joystick_2);
	Joystick OperatorGamepad = new Joystick(Ports.Controls.Driver_Joystick_OperatorGamepad);
	PIDController turnController;
	double kP = 0.0;
	double kI = 0.0;
	double kD = 0.0;
	double kF = 0.0;
	double kToleranceDegrees = 2.0;
	@Override
	public void robotInit() {
		autonchooser.addDefault("Do Nothing", new DoNothing());
		autonchooser.addObject("Gear Pos.1 or Left Face", new DriveStraight1());
		autonchooser.addObject("Gear Pos.2 or Middle Face", new DriveStraight2());
		autonchooser.addObject("Gear Pos.3 or Right Face", new DriveStraight3());
		autonchooser.addObject("Test Turn", new TestTurn());
		SmartDashboard.putData("Auto choices", autonchooser);
		
		
		turnController = new PIDController(kP, kI, kD, kF, Bot.ahrs, this);
		turnController.setInputRange(-180.0, 180.0);
		turnController.setOutputRange(-1.0, 1.0);
		turnController.setAbsoluteTolerance(kToleranceDegrees);
	    turnController.setContinuous(true);
		LiveWindow.addActuator("Drivesysttem", "RotateController", turnController);
		
//		CameraServer.getInstance().startAutomaticCapture();
		
	}

	@Override
	public void autonomousInit() {
		
		autonomousCommand = (Command) autonchooser.getSelected();
		if(autonomousCommand != null){
			autonomousCommand.start();
			System.out.println("Chosen auton:" + autonomousCommand);
		}
		
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopPeriodic() {

		Bot.drive.tankDrive(-Driverleft.getRawAxis(1), -Driverright.getRawAxis(1));
//		Bot.innouttake.runInNOuttake(OperatorGamepad.getPOV() == 180 ? 1 : OperatorGamepad.getPOV() == 0 ? -1 : 0);
//		Bot.climber.runClimb(OperatorGamepad.getRawButton(2) ? 1 : 0);
//		Bot.gm.runGearManipulator(OperatorGamepad.getRawButton(5),OperatorGamepad.getRawButton(6),Bot.gmbut1.get(),Bot.gmbut2.get());

		Bot.gp.runGearPickup(OperatorGamepad.getRawButton(5), OperatorGamepad.getRawButton(6), Bot.gmbut2.get(),OperatorGamepad.getRawButton(7),OperatorGamepad.getRawButton(8), Bot.rightenc.getDistance());
		System.out.println(Supersystem.getInstance().rightenc.getDistance());
		
//	if(OperatorGamepad.getRawButton(4)){
//		Bot.ahrs.reset();
//	}else{
//		
//	}
//	
//	if(OperatorGamepad.getRawButton(2)){
//		turnController.setSetpoint(90);
//		Bot.drive.testPID(turnController.get());
//	}else{
//		Bot.drive.tankDrive(0.0, 0.0);
//	}
//		
	

	}

	@Override
	public void testPeriodic() {
		if(Driverright.getRawButton(5)){
			Bot.drive.tankDrive(-Driverleft.getRawAxis(1), -Driverright.getRawAxis(1));
		}else{
		
		if(OperatorGamepad.getRawButton(4)){
			Bot.ahrs.reset();
		}else{
			
		}
		
		if(OperatorGamepad.getRawButton(2)){
			turnController.setSetpoint(60);
			Bot.drive.testPID(turnController.get());
		}else{
			Bot.drive.tankDrive(0.0, 0.0);
		}
			
		System.out.println(Supersystem.getInstance().ahrs.getAngle());
		
		}
	}

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		
	}
}

