package org.usfirst.frc.team9399.Systems;

import org.usfirst.frc.team9399.Config.Ports;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.PIDSubsystem;


public class GearPickup {
	//kP magic number = 0.005
	final double kP = 0.011;
	final double kI = 0.0;
	final double kD = 0.000001;
	
	
	
	double p;
	double d; 
	
	double i =0;
	double lastError = 0;
	double value;
	double setPoint;
	double output;
	CANTalon pivot;
	CANTalon wheel;

	double encoderPulses;

	
	public GearPickup(int turn, int roller){
		pivot = new CANTalon(turn);
		wheel = new CANTalon(roller);

	}

	
	public void runGearPickup(boolean intake, boolean score,boolean bump,boolean suck,boolean zero, double currentPulses){
		if(bump){
			
		}else if(bump == false){
			Supersystem.getInstance().rightenc.reset();
		}else{
			
		}
		
		
		if(intake){
//			pidcontroller.setSetpoint(); is down
			setPoint = 187;
			p = setPoint - currentPulses;
			i += p;
			d = p - lastError;
			lastError = p;
			output = (kP * p + kI * i + kD * d);
			pivot.set(-output);
			wheel.set(1.0);
		}else if(score){
			setPoint = 187;
			p = setPoint - currentPulses;
			i += p;
			d = p - lastError;
			lastError = p;
			output = (kP * p + kI * i + kD * d);
			pivot.set(-output);
			if(currentPulses == 89){
				wheel.set(-0.5);
			}else{
				wheel.set(0.3);
			}
			
		}else if(zero){
			pivot.set(0.5);
		}else{
			setPoint = 55;
			p = setPoint - currentPulses;
			i += p;
			d = p - lastError;
			lastError = p;
			output = (kP * p + kI * i + kD * d);
			pivot.set(-output);
			
			if(suck){
				wheel.set(0.8);
			}else{
				wheel.set(0.0);
			}
			
		}
		
		
	}
	
	
	
//	public double encodervalue(){
//		return Supersystem.getInstance().rightenc.getDistance();
//	
//	}
	
	public void run(boolean run, boolean run2){
		if(run){
			pivot.set(0.2);
		}else if(run2){
			pivot.set(-0.2);
		}else{
			pivot.set(0.0);
		}
		
	}


}
