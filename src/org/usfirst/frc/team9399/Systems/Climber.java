package org.usfirst.frc.team9399.Systems;

import com.ctre.CANTalon;

public class Climber {
	CANTalon climber1;
	CANTalon climber2;
	
	public Climber(int climb1, int climb2){
		climber1 = new CANTalon(climb1);
		climber2 = new CANTalon(climb2);
	}
	
	public void runClimb(double value){
		if(value == 1){
			climber1.set(value);
			climber2.set(-value);
		}else if(value == -1){
			climber1.set(-value);
			climber2.set(value);
		}else{
			climber1.set(0.0);
			climber2.set(0.0);
		}
	}

}
