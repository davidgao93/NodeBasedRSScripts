package tasks;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.api.Inventory;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.model.RS2Object;

import framework.Node;
import data.Data;

public class Stamina implements Node {

	public static int STAMINA_ID = 12631, DSTAMINA_ID = 229;
	private Script s;
	private Player me;
	private Inventory inv;
	private Data data;
	
	public Stamina(Script s, Data data) {
		this.s = s;
		this.me = s.myPlayer();
		this.inv = s.getInventory();
		this.data = data;
	}
	
	@Override
	public boolean validate() {
		return data.BankArea.contains(me) && data.getStaminaUsage() && s.settings.getRunEnergy() <= 60 && !inv.isFull();
	}

	@Override
	public void run() throws InterruptedException {
		//s.log("STAMINA OPERATION");
		RS2Object bankBooth = s.objects.closest("Bank booth");
		if (bankBooth != null) {
	  		if (s.getBank().isOpen()) {
	  			s.getBank().withdraw(STAMINA_ID, 1);
	  			s.getBank().close();
	  	  		s.getInventory().interact("Drink", STAMINA_ID);
	  	  		Script.sleep(Script.random(1200, 1500));
	  			inv.getItem(DSTAMINA_ID).interact("Drop");
	  		} else {
	  			bankBooth.interact("Bank");
	  			Script.sleep(Script.random(3500, 4500));
  			}
		}
	}
}
