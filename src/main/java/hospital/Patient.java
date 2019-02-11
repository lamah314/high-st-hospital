package hospital;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Patient {
	private static final int BLOOD_LEVEL = 20; //default. Max of 20
	private static final int HEALTH_LEVEL = 10; //default. Max of 20 
	
	private String name;
	private int bloodLevel;
	private int healthLevel;
	private String specialtyNeed;
	private String specialtyNeedDisplay;
	private boolean deathFlag;
	private boolean leaveFlag;

	public static Object getDefaultBloodLevel() {
		return BLOOD_LEVEL;
	}
	
	public static Object getDefaultHealthLevel() {
		return HEALTH_LEVEL;
	}
	
	public String getName() {
		return name;
	}
	
	public int getBloodLevel() {
		return bloodLevel;
	}

	public int getHealthLevel() {
		return healthLevel;
	}
	
	public String getSpecialtyNeed() {
		return specialtyNeed;
	}
	
	public String getSpecialtyNeedDisplay() {
		return specialtyNeedDisplay;
	}
	
	public boolean getDeathFlag() {
		return deathFlag;
	}
	
	public boolean getLeaveFlag() {
		return leaveFlag;
	}
	
	public Patient(String name) {
		this.name = name;
		bloodLevel = BLOOD_LEVEL;
		healthLevel = HEALTH_LEVEL;
		specialtyNeedDisplay = "unknown";
		specialtyNeed = randomlyAssignSpecialty();
		enforceMaxBloodValue();
		enforceMaxHealthValue();
	}

	public void displayStats() {
		System.out.println("Patient " + name +"\t" + bloodLevel +"\t\t" + healthLevel +"\t\t" + specialtyNeedDisplay);
	}
	
	@Override
	public String toString() {
		return ("Patient " + name + " has a bloodLevel of " + bloodLevel + " and a healthLevel of " + healthLevel);
	}

	public void tick() {
		raiseHealthLevel(Math.min(0, Math.round((bloodLevel-5)/2))); //penalize for low blood level (<5)
		raiseBloodLevel(Math.max(0, Math.round((healthLevel-10)/2))); //raise blood if good health (>10)	
	}
	
	
	public void lowerBloodLevel(int amount) {
		bloodLevel -= amount;
	}
	
	public void lowerHealthLevel(int amount) {
		healthLevel -= amount;
	}

	public void raiseBloodLevel(int amount) {
		bloodLevel += amount;
		enforceMaxBloodValue();
	}
	
	public void raiseHealthLevel(int amount) {
		healthLevel += amount;
		enforceMaxHealthValue();
	}

	public void healSpecialty() {
		specialtyNeed = "";
	}

	public void deathCheck() {
		if (healthLevel <= 0 || bloodLevel <= 0) {
			deathFlag = true;
		}
	}
	
	public void deathWarning() {
		if (healthLevel <= 5 || bloodLevel <= 5) {
			System.out.println("Patient " + getName() + " needs to be tended to.");
		}
	}
	
	public void discoverSpecialty() {
		specialtyNeedDisplay = specialtyNeed;
	}
	
	public void healthCheck() {
		if (healthLevel >= 20 || bloodLevel >= 20) {
			leaveFlag = true;
		}
	}
	
	
	
	
	
	//special methods
	public String randomlyAssignSpecialty() {
		List<String> givenList = Arrays.asList("", "", "", "Heart", "Brain", "Foot"); //half of patients will need a specialist Doctor to help them 
		Random rand = new Random();
	    String randomSpecialty = givenList.get(rand.nextInt(givenList.size()));
	    return randomSpecialty;
	}
	
	public void enforceMaxBloodValue() {
		if(specialtyNeed.equals("Heart")) { //can't have much blood if your heart is in bad condition
			if (bloodLevel > 10) {
				bloodLevel = 10;
			}
		} else if (specialtyNeed.equals("Brain")) {
			if (bloodLevel > 15) {
				bloodLevel = 15;
			}
		} else if (specialtyNeed.equals("Foot")) { //slight incentive to treat foot
			if (bloodLevel > 18) {
				bloodLevel = 18;
			}
		} else {
			if (bloodLevel > 20) {
				bloodLevel = 20;
			}
		}
	}
	
	public void enforceMaxHealthValue() {
		if(specialtyNeed.equals("Heart")) {
			if (healthLevel > 10) { 
				healthLevel = 10;
			}
		} else if (specialtyNeed.equals("Brain")) { 
			if (healthLevel > 10) {
				healthLevel = 10;
			}
		} else if (specialtyNeed.equals("Foot")) { //slight incentive to treat foot
			if (healthLevel > 18) {
				healthLevel = 18;
			}
		}  else {
			if (healthLevel > 20) {
				healthLevel = 20;
			}
		}
	}
	
}
