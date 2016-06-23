package data;

import org.osbot.rs07.api.map.Area;

public class Data {
	public int ESSENCE = 7936, SP = 5509, MP = 5510, LP = 5512, 
			LP1 = 5513, MP1 = 5511,
			AIR = 556, ASTRAL = 9075, COSMIC = 564, QPC = 9813,
			STAM = 12631, STAM3 = 12627, STAM2=  12629, STAM4 = 12625,
			VIAL = 229, GLORY6 = 11978, 
			GLORY = 1704, GLORY1 = 1706, GLORY2 = 1708, 
			GLORY3 = 1710, GLORY4 = 1712, GLORY5 = 11976;
	private String foodName;
	private boolean staminaUsage;
	private int healthThreshold;
	
	public Area edgeville = new Area(3085, 3499, 3089, 3489);
	public Area bank = new Area(3092, 3493, 3094, 3489);
	public Area legend = new Area(2726, 3349, 2734, 3345);
	public Area fairy = new Area(2739, 3352, 2741, 3350);
	public Area zanaris = new Area(2411, 4435, 2413, 4433);
	public Area zanaris1 = new Area(2418, 4425, 2421, 4421);
	public Area zanaris2 = new Area(2419, 4416, 2420, 4414);
	public Area aGate = new Area(2410, 4412, 2421, 4403); // Jutting Wall (Squeeze-past)
	public Area aGateb = new Area(2413, 4406, 2415, 4403);
	public Area aGatef = new Area(2406, 4401, 2417, 4396);
	public Area bGatef = new Area(2407, 4394, 2408, 4393);
	public Area bGate = new Area(2408, 4397, 2408, 4396);
	public Area ruins = new Area(2400, 4388, 2415, 4372); // Enter Mysterious ruins, success if nearest object is Portal, walk to Altar (Craft-rune)
	public Area altar = new Area(2140, 4835, 2144, 4831);				
	
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

