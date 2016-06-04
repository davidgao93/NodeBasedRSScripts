package tasks;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;
import org.osbot.rs07.api.Inventory;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.model.RS2Object;

import framework.Node;
import data.Data;

public class Bank implements Node {

	public static int STAMINA_ID = 12627, DSTAMINA_ID = 12629;
	private Script s;
	private Player me;
	private Inventory inv;
	private Data data;
	
	public Bank(Script s, Data data) {
		this.s = s;
		this.me = s.myPlayer();
		this.inv = s.getInventory();
		this.data = data;
	}
	
	@Override
	public boolean validate() {
		return (data.BarArea.contains(me) || data.BankArea.contains(me)) && inv.isFull();
	}

	@Override
	public void run() throws InterruptedException {
		//s.log("BANK OPERATION");
		RS2Object door = s.objects.closest(24369);
        if (door != null) {
			door.interact("Open");
        }
        s.getWalking().walk(data.BankArea);
		new ConditionalSleep(500, 1500) {
			@Override
			public boolean condition() {
				return !me.isMoving();
			}
		}.sleep();
		RS2Object bankBooth = s.objects.closest("Bank booth");
    	s.getWalking().walk(bankBooth.getPosition());
    	if (bankBooth != null) {
			if (s.getBank().isOpen()) {
		      	s.getInventory().getItem(2970).interact("Deposit-All");
		      	Script.sleep(Script.random(1500, 2500));
		      	data.mushroomCounter += 25;
			} else {
				bankBooth.interact("Bank");
				Script.sleep(Script.random(3500, 4500));
			}
    	}
	}
}
