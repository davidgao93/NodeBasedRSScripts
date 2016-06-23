package tasks;

import java.awt.event.KeyEvent;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;
import org.osbot.rs07.api.Inventory;
import org.osbot.rs07.api.model.RS2Object;

import framework.Node;
import data.Data;

public class Glory implements Node {

	private Script s;
	private Inventory inv;
	private Data data;
	
	public Glory(Script s, Data data) {
		this.s = s;
		this.inv = s.getInventory();
		this.data = data;
	}
	
	@Override
	public boolean validate() {
		return inv.contains(data.GLORY) 
				&& data.bank.contains(s.myPlayer());
	}

	@Override
	public void run() throws InterruptedException {
		s.log("GLORY OPERATION");
		RS2Object bankBooth = s.objects.closest("Bank booth");
		if (bankBooth != null) {
	  		if (s.getBank().isOpen()) {
	  			if (inv.contains(data.GLORY)){
	  				s.getBank().deposit(data.GLORY, 1);
	  			}
	  			if (s.getBank().contains(data.GLORY6)) {
	  				s.getBank().withdraw(data.GLORY6, 1);
	  			}
	  			s.getKeyboard().typeKey((char)KeyEvent.VK_ESCAPE);
	  	        new ConditionalSleep(10000) {
	  	            @Override
	  	            public boolean condition() throws InterruptedException {
	  	                return !s.getBank().isOpen();
	  	            }
	  	        }.sleep();
	  		} else {
	  			bankBooth.interact("Bank");
	  	        new ConditionalSleep(10000) {
	  	            @Override
	  	            public boolean condition() throws InterruptedException {
	  	                return s.getBank().isOpen();
	  	            }
	  	        }.sleep();
  			}
		}
	}
}
