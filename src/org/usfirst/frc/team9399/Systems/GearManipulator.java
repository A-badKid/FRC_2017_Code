package org.usfirst.frc.team9399.Systems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.Counter;

public class GearManipulator {
	CANTalon gm1;
	CANTalon gm2;

	public GearManipulator(int gm1,int gm2){
		this.gm1 = new CANTalon(gm1);
		this.gm2 = new CANTalon(gm2);

		
	}
	
	public void runGearManipulator(boolean run,boolean zero,boolean buttoncontact1,boolean buttoncontact2){
		if(run == false){
			gm1.set(0.1);
			gm2.set(-0.1);
		}else if(run){
			gm1.set(-0.4);
			gm2.set(0.4);
		}else if(buttoncontact1 == false && buttoncontact2 == false){
			gm1.set(0.5);
			gm2.set(-0.5);
		}else{
			gm1.set(0.0);
			gm2.set(0.0);
		}
		
	}
	
	public void autonRunGearManipulator(boolean btn1,boolean btn2){
		if(btn1 && btn2){
			gm1.set(-0.3);
			gm2.set(0.3);
		}else if(btn1 == false && btn2 == false) {
			gm1.set(0.0);
			gm2.set(0.0);
		}else{
			gm1.set(0.1);
			gm2.set(-0.1);
		}
		
	}
	
	

}
