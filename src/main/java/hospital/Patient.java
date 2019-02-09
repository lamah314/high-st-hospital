package hospital;

public class Patient {
	private static final int BLOOD_LEVEL = 20; //default
	private static final int HEALTH_LEVEL = 10; //default
	
	private String name;
	private int bloodLevel;
	private int healthLevel;

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
	
	public Patient(String name) {
		this.name = name;
		bloodLevel = BLOOD_LEVEL;
		healthLevel = HEALTH_LEVEL;
	}

	public void displayStats() {
		System.out.println("Patient " + name + " has a bloodLevel of " + bloodLevel + " and a healthLevel of " + healthLevel);
	}

	public void lowerBloodLevel(int amount) {
		bloodLevel -= amount;
	}
	
	public void lowerHealthLevel(int amount) {
		healthLevel -= amount;
	}

	public void raiseBloodLevel(int amount) {
		bloodLevel += amount;
	}
	
	public void raiseHealthLevel(int amount) {
		healthLevel += amount;
	}








}
