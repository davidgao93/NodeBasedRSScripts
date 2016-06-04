package data;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

public class Data {
	private String foodName;
	private boolean staminaUsage;
	private int healthThreshold;
	public int mushroomCounter = 0;
	
	public Area BarArea = new Area(new Position(3488, 3480, 0), new Position(3501, 3470, 0));
	public Area BankArea = new Area(new Position(3509, 3483, 0), new Position(3513, 3477, 0));
	public Area SwampArea = new Area(new Position(3465, 3476, 0), new Position(3469, 3472, 0));
	public Area GateArea = new Area(new Position(3440, 3465, 0), new Position(3451, 3458, 0)); 
	public Area LogArea = new Area(new Position(3419, 3441, 0), new Position(3424, 3435, 0));
	public Area GateCheck = new Area(new Position(3442, 3457, 0), new Position(3445, 3455, 0));
    
	public Position AlphaLog = new Position(3421, 3439, 0);
	public Position BetaLog = new Position(3421, 3437, 0);
	public Position CurrentLog;
	
	public Data(boolean staminaUsage, String foodName, int healthThreshold) {
		this.staminaUsage = staminaUsage;
		this.foodName = foodName;
		this.healthThreshold = healthThreshold;
	}

	public String getFoodName() {
		return foodName;
	}
	
	public boolean getStaminaUsage() {
		return staminaUsage;
	}
	
	public int getHealthThreshold() {
		return healthThreshold;
	}

}
