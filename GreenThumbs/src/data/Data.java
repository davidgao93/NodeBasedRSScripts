package data;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

public class Data {
	private String foodName;
	private boolean staminaUsage;
	private int healthThreshold;
	public int mushroomCounter = 0;
	private int currentWorld = 0;
	
	public Area BankArea = new Area(new Position(3509, 3483, 0), new Position(3513, 3477, 0));
	public Area LogArea = new Area(new Position(3419, 3441, 0), new Position(3424, 3435, 0));
    public Area SafeArea = new Area(new Position(3415, 3456, 0), new Position(3419, 3453, 0));
	
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
	
	public void setCurrentWorld(int c) {
		currentWorld = c;
	}
	
	public int getCurrentWorld() {
		return currentWorld;
	}

}
