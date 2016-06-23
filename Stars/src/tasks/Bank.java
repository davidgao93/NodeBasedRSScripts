package tasks;

import java.awt.event.KeyEvent;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;
import org.osbot.rs07.api.Inventory;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Skill;

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
		return data.bank.contains(me) 
				&& !inv.isFull() 
				&& s.getObjects().closest("Bank booth") != null
				&& !inv.contains(data.GLORY)
				&& !inv.contains(data.LP1)
				&& !inv.contains(data.MP1)
				&& s.settings.getRunEnergy() > 60 
				&& s.skills.getDynamic(Skill.HITPOINTS) > data.getHealthThreshold();
	}

	@Override
	public void run() throws InterruptedException {
		s.log("BANK OPERATION");
		RS2Object bankBooth = s.objects.closest("Bank booth");
    	if (bankBooth != null) {
    		bankBooth.interact("Bank");
            new ConditionalSleep(10000) {
                @Override
                public boolean condition() throws InterruptedException {
                    return s.getBank().isOpen();
                }
            }.sleep();
			if (s.getBank().isOpen()) {
				withdrawItem(data.ESSENCE);
				s.getKeyboard().typeKey((char)KeyEvent.VK_ESCAPE);
				inv.interact("Fill", data.SP);
				inv.interact("Fill", data.MP);
				inv.interact("Fill", data.LP);
				bankBooth.interact("Bank");
                new ConditionalSleep(10000) {
                    @Override
                    public boolean condition() throws InterruptedException {
                        return s.getBank().isOpen();
                    }
                }.sleep();
                withdrawItem(data.ESSENCE);
                s.getKeyboard().typeKey((char)KeyEvent.VK_ESCAPE);
                new ConditionalSleep(10000) {
                    @Override
                    public boolean condition() throws InterruptedException {
                        return !s.getBank().isOpen();
                    }
                }.sleep();
			}
    	}
	}
	
    private void withdrawItem(int itemid) throws InterruptedException {
    	while (!inv.isFull() && s.getBank().isOpen()) {
	    	switch (Script.random(0, 50)) {
	    	case 10:
	    		s.getBank().withdraw(itemid, 1);
	    		break;
	    	case 20:
	    		s.getBank().withdraw(itemid, Script.random(30, 33));
	    		break;
	    	case 30:
	    		s.getBank().withdraw(itemid, 5);
	    		break;
	    	case 40:
	    		s.getBank().withdraw(itemid, 10);
	    		break;
	    	default:
	    		s.getBank().withdrawAll(itemid);
	    		break;
	    	}
	    	Script.sleep(Script.random(500, 1500));
    	}
    }
}
