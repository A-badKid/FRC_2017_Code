package org.usfirst.frc.team9399.Systems;

import com.ctre.CANTalon;
import org.usfirst.frc.team9399.Systems.Supersystem;

public class Drivetrain {
	
	private CANTalon lefta = null;
	private CANTalon leftb = null;
	private CANTalon righta = null;
	private CANTalon rightb = null;
	
//	Supersystem Bot = Supersystem.getInstance();
	
	public Drivetrain(int la, int lb, int ra, int rb) {
		
		lefta = new CANTalon(la);
		leftb = new CANTalon(lb);
		
		righta = new CANTalon(ra);
		rightb = new CANTalon(rb);
		
		
	}
	
	public void tankDrive(double leftv, double rightv) {
//		if(leftv < -0.3 && rightv < -0.3){
//			lefta.set(0.0);
//			leftb.set(0.0);
//			
//			righta.set(0.0);
//			rightb.set(0.0);
//		}else if(leftv < 0.3 && rightv < 0.3){
//			lefta.set(0.0);
//			leftb.set(0.0);
//			
//			righta.set(0.0);
//			rightb.set(0.0);
//		}
		lefta.set(-leftv);
		leftb.set(-leftv);
		
		righta.set(rightv);
		rightb.set(rightv);
	}
	//Old Method
//	public void distancedForwardStraightDrive(double lpow, double rpow, double distance, double currentAngle, double pulses){
//		double newDistance = distance - 14;
//		double requiredPulses = newDistance * 28.647889757;
//		double leftval = lpow + currentAngle*0.05;
//		double rightval = rpow - currentAngle*0.05;
//		
//		if(pulses < requiredPulses){
//			lefta.set(-leftval);
//			leftb.set(-leftval);
//			righta.set(rightval);
//			rightb.set(rightval);
//		}else if(pulses > requiredPulses){
//			lefta.set(0.0);
//			leftb.set(0.0);
//			righta.set(0.0);
//			rightb.set(0.0);
//		}else{
//			
//		}
//		
//		
//	}
	
	public void distancedBackwardStraightDrive(double lpow, double rpow, double distance, double currentAngle, double pulses,double subtractfromdist, double decayfactor){
		double newDistance = distance - subtractfromdist; //Accounts for bumpers
		double requiredPulses = -newDistance * 39.78873579; // 28.647889757 Pulses to 1 Inch
		double decaynum = Math.abs(pulses) * decayfactor; //The exponent for the motor power for it to exponentially decay. To understand this just search Algebra 2 Exponential Decay
		double leftdecaypow = Math.pow(Math.abs(lpow),Math.abs(decaynum)); // Has to be absolute value because remember negative to the power of a even number equals positive, and negative to the power of an odd number equals negative.
		double rightdecaypow = Math.pow(Math.abs(rpow),Math.abs(decaynum));// And so we take the absolute value to avoid sign changes and we change to negative later.
		double finalleftpow = -leftdecaypow; // Where we actually change the value to a negative
		double finalrightpow = -rightdecaypow; // Where we actually change the value to a negative
		double leftval = finalleftpow + currentAngle*0.09; // Where we implement the exponentially decayed values to the gyro for the left side of drivetrain making it go straight
		double rightval = finalrightpow - currentAngle*0.09; // Where we implement the exponentially decayed values to the gyro for the right side of drivetrain making it go straight
		
				
		
		if(pulses > requiredPulses){
			lefta.set(-leftval);
			leftb.set(-leftval);
			righta.set(rightval);
			rightb.set(rightval);
		}else if(pulses == requiredPulses){
			lefta.set(0.0);
			leftb.set(0.0);
			righta.set(0.0);
			rightb.set(0.0);
		}else{
			
		}
		
		
		
	}
	
	public void easyBackwardStraight(double currentPulses,double angle,double targetDistanceInInches){
		double requiredPulses = targetDistanceInInches * 42.335214866;
		double powpart = (requiredPulses - Math.abs(currentPulses));
		double newpow = powpart/4000;
		double reconAngle = angle*0.09;
		double leftpow = newpow + reconAngle;
		double rightpow = newpow - reconAngle;
		
		lefta.set(newpow);
		leftb.set(newpow);
		righta.set(-newpow);
		rightb.set(-newpow);
		
		
	}
	
	public void easyForwardStraight(double currentPulses,double angle,double targetDistanceInInches){
		double requiredPulses = targetDistanceInInches * 42.335214866;
		double powpart = (requiredPulses - Math.abs(currentPulses));
		double newpow = powpart/4000;
		double reconAngle = angle*0.09;
		double leftpow = newpow + reconAngle;
		double rightpow = newpow - reconAngle;
		
		lefta.set(-newpow);
		leftb.set(-newpow);
		righta.set(newpow);
		rightb.set(newpow);
		
		
	}
	
//	public void distancedForwardStraightDrive(double lpow, double rpow, double distance, double currentAngle, double pulses,double subtractfromdist, double decayfactor){
//		// Same as distancedBackwardStraightDrive method above but for going forward because pulse values are negative going back, and positive going forward.
//		// Try to make into one method possibly?
//		double newDistance = distance - subtractfromdist;
//		double requiredPulses = newDistance * 28.647889757;
//		double decaynum = pulses * decayfactor;
//		double leftdecaypow = Math.pow(Math.abs(lpow),Math.abs(decaynum));
//		double rightdecaypow = Math.pow(Math.abs(rpow),Math.abs(decaynum));
//		double finalleftpow = -leftdecaypow;
//		double finalrightpow = -rightdecaypow;
//		double leftval = finalleftpow + currentAngle*0.05;
//		double rightval = finalrightpow - currentAngle*0.05;
//		
//				
//		
//		if(pulses < requiredPulses){
//			lefta.set(-leftval);
//			leftb.set(-leftval);
//			righta.set(rightval);
//			rightb.set(rightval);
//		}else if(pulses == requiredPulses){
//			lefta.set(0.0);
//			leftb.set(0.0);
//			righta.set(0.0);
//			rightb.set(0.0);
//		}else{
//			
//		}
//		
//		
//	}
	
//	public void turnAngle(double pow, double desiredAngle, double currentAngle, boolean revvalue, double decayFactor){
//		double decayexponent = Math.abs(currentAngle) * decayFactor;
//		double decaypower = Math.pow(pow,decayexponent);
//	
//		if(currentAngle > desiredAngle && desiredAngle < 0.0 && revvalue){
//			lefta.set(-decaypower);
//			leftb.set(-decaypower);
//			righta.set(-decaypower);
//			rightb.set(-decaypower);
//		}else if(currentAngle == desiredAngle && revvalue){
//			lefta.set(0.0);
//			leftb.set(0.0);
//			righta.set(0.0);
//			rightb.set(0.0);
//		}else{
//			
//		}
//			
//		if(currentAngle < desiredAngle && desiredAngle > 0.0){
//			lefta.set(decaypower);
//			leftb.set(decaypower);
//			righta.set(decaypower);
//			rightb.set(decaypower);
//		}else if(currentAngle == desiredAngle){
//			lefta.set(0.0);
//			leftb.set(0.0);
//			righta.set(0.0);
//			rightb.set(0.0);
//		}else{
//			
//		}
//	}
	//0.012 should work for Impulse
	public void updatedNegativeTurnAngle(double currentAngle, double targetAngle){
		//double left = (targetAngle + currentAngle)*0.09;
		double pow = (targetAngle - currentAngle)*0.012;
		lefta.set(pow);
		leftb.set(pow);
		righta.set(pow);
		rightb.set(pow);
		
	}
	
	public void testPID(double output){
		lefta.set(output);
		leftb.set(output);
		righta.set(output);
		rightb.set(output);
	}
	
	public void updatedPositiveTurnAngle(double currentAngle, double targetAngle){
		//double left = (targetAngle + currentAngle)*0.09;
		double pow = (targetAngle - currentAngle)*0.09;
		lefta.set(-pow);
		leftb.set(-pow);
		righta.set(-pow);
		rightb.set(-pow); //figure out signs for output
		
		
		
	}
	
	public void autonBackRightArc(double currentPulses,double targetDistanceInInches,double currentAngle,double targetAngle){
		double requiredPulses = targetDistanceInInches * 42.335214866;
		
		double currentRadians = currentAngle * 0.0174533;
		double requiredRadians = targetAngle * 0.0174533;
//		double pulsetoradian = requiredPulses/requiredRadians;
//		double powcalc = (requiredPulses - Math.abs(currentPulses - pulsetoradian));
		double powcalc = (requiredPulses - Math.abs(currentPulses));
		double radianDifference = (requiredRadians - Math.abs(currentRadians));
		double newpow = powcalc/4000; 
		lefta.set(-newpow + radianDifference);
		leftb.set(-newpow + radianDifference);
		righta.set(newpow);
		rightb.set(newpow);
	}
	
	public void arcForward(double currentPulses, double currentAngle, double targetInInches, double targetAngle){
		double requiredPulses = targetInInches * 42.335214866;
		double requiredRadians = targetAngle * 0.0175;
		double targetAngleInRadians = targetAngle * 0.0175;
		double currentRadians = currentAngle * 0.0175;
		double currentInches = currentPulses/42.335214866;
		
		
	}
	
	public void setZero(boolean zero, double currentAngle){
		if(zero){
			lefta.set(-currentAngle);
			leftb.set(-currentAngle);
			righta.set(currentAngle);
			rightb.set(currentAngle);
		}else{
			lefta.set(0.0);
			leftb.set(0.0);
			righta.set(0.0);
			rightb.set(0.0);
		}
		
	}
	
	public void arcadeDrive(double throttle, double turn){
		double left = throttle + turn;
		double right = throttle - turn;
		lefta.set(-left);
		leftb.set(-left);
		righta.set(right);
		rightb.set(right);
	}
	
	public void limitSwitch(boolean leftfor,boolean leftrev,boolean rightfor,boolean rightrev){
		lefta.enableLimitSwitch(leftfor,leftrev);
		leftb.enableLimitSwitch(leftfor,leftrev);
		
		righta.enableLimitSwitch(rightfor,rightrev);
		rightb.enableLimitSwitch(rightfor,rightrev);
		
	}
	
}
