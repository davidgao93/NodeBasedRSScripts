package tasks;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;
import org.osbot.rs07.api.Inventory;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Skill;

import framework.Node;
import data.Data;

public class Eat implements Node {

	private Script s;
	private Player me;
	private Inventory inv;
	private Data data;
	
	public Eat(Script s, Data data) {
		this.s = s;
		this.me = s.myPlayer();
		this.inv = s.getInventory();
		this.data = data;
	}
	
	@Override
	public boolean validate() {
		return data.bank.contains(me) 
				&& s.skills.getDynamic(Skill.HITPOINTS) <= data.getHealthThreshold() 
				&& !inv.isFull();
	}

	@Override
	public void run() throws InterruptedException {
		s.log("EAT OPERATION");
		RS2Object bankBooth = s.objects.closest("Bank booth");
		if (bankBooth != null) {
	  		if (s.getBank().isOpen()) {
	  			s.getBank().withdraw(data.getFoodName(), 1);
	  			s.getBank().close();
	  	  		s.getInventory().interact("Eat", data.getFoodName());
	  	  		Script.sleep(Script.random(1200, 1500));
	  		} else {
	  			bankBooth.interact("Bank");
	  	        new ConditionalSleep(1000, 100) {
	  	            @Override
	  	            public boolean condition() throws InterruptedException {
	  	                return s.getBank().isOpen();
	  	            }
	  	        }.sleep();
  			}
		}
	}
}
