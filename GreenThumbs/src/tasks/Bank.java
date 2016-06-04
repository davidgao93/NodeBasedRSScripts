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
		return s.objects.closest("Portal") == null && inv.isFull();
	}

	@Override
	public void run() throws InterruptedException {
		//s.log("BANK OPERATION");
        s.getWalking().webWalk(data.BankArea);
		new ConditionalSleep(1000, 500) {
			@Override
			public boolean condition() {
				return !me.isMoving();
			}
		}.sleep();
		RS2Object bankBooth = s.objects.closest("Bank booth");
    	if (bankBooth != null) {
			if (s.getBank().isOpen()) {
		      	s.getInventory().getItem(2970).interact("Deposit-All");
                new ConditionalSleep(1000, 100) {
                    @Override
                    public boolean condition() throws InterruptedException {
                        return !s.getInventory().isFull();
                    }
                }.sleep();
		      	data.mushroomCounter += 25;
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
