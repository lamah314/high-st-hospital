package hospital;

public abstract class Employee {

	private boolean oneTurn = true;
	private String name;
	private int ID;
	private int pay;
	
	private String formatName = "%-16s%s%n";
	
	public boolean getOneTurn() {
		return oneTurn;
	}
	
	public void doOneTurn() {
		oneTurn = false;
	}
	
	public void renewOneTurn() {
		oneTurn = true;
	}
	
	public String oneTurnIndicator() {
		if (getOneTurn()) {
			return "*";
		} else {
			return "";
		}
	}
	
	public String getName() {
		return name;
	}

	public int getID() {
		return ID;
	}
	
	public int calculatePay() {
		return pay;
	}

	public Employee(String name, int ID) {
		this.name = name;
		this.ID = ID;
	}

	public void displayBasicStats() {
		System.out.println(getName()+"\t\t" + getID());;
	}
	
	public void displayPay() {
		System.out.printf(formatName, getName(),  getID() + "\t" + calculatePay());
	}
	
	@Override
	public String toString() {
		return (ID + ": " + name + " is an employee at High St. Hospital");
	}
	
	
	public void payForDoctor() {
		pay = 90000;
	}

	public void payForNurse() {
		pay = 50000;
	}

	public void payForSurgeon() {
		pay = 120000;
	}

	public void payForReceptionist() {
		pay = 45000;
	}

	public void payForJanitor() {
		pay = 40000;
	}

}
