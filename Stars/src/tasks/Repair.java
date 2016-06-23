package tasks;

import java.awt.event.KeyEvent;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;
import org.osbot.rs07.api.Inventory;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Option;
import org.osbot.rs07.api.ui.Tab;

import framework.Node;
import data.Data;

public class Repair implements Node {

	private Script s;
	private Inventory inv;
	private Data data;
	
	public Repair(Script s, Data data) {
		this.s = s;
		this.inv = s.getInventory();
		this.data = data;
	}
	
	@Override
	public boolean validate() {
		return inv.contains(data.LP1) || inv.contains(data.MP1);
	}

	@Override
	public void run() throws InterruptedException {
		s.log("REPAIR OPERATION");
		RS2Object bankBooth = s.objects.closest("Bank booth");
		if (bankBooth != null) {
  			bankBooth.interact("Bank");
  	        new ConditionalSleep(1000, 100) {
  	            @Override
  	            public boolean condition() throws InterruptedException {
  	                return s.getBank().isOpen();
  	            }
  	        }.sleep();
	  		if (s.getBank().isOpen()) {
	  			if (Script.random(0, 50) < 25) {
	  				s.getBank().withdraw(data.AIR, 1);
	  				s.getBank().withdraw(data.AIR, 1);
	  			} else {
	  				s.getBank().withdraw(data.AIR, 2);
	  			}
	  			s.getBank().withdraw(data.ASTRAL, 1);
	  			s.getKeyboard().typeKey((char)KeyEvent.VK_ESCAPE);
	  	        new ConditionalSleep(1000, 100) {
	  	            @Override
	  	            public boolean condition() throws InterruptedException {
	  	                return !s.getBank().isOpen();
	  	            }
	  	        }.sleep();
				if (!s.tabs.getOpen().equals(Tab.MAGIC)) {
					s.tabs.open(Tab.MAGIC);
				}
				if (mouseToTalkArea()) {
	                leftClick();
	                Script.sleep(Script.random(2500, 4000));
				}
				talkToMage();
				if (Script.random(0,128) == 44) {
					s.log("Anti-ban hit, waiting for up to 2 minutes");
					Script.sleep(Script.random(25000, 120000));
				}
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
	
    private boolean mouseToTalkArea() throws InterruptedException {
		s.mouse.move(Script.random(678, 696), Script.random(216, 231));
        return isTalkOption();
    }
    
    private void talkToMage() throws InterruptedException {
    	s.mouse.move(Script.random(222, 278) , Script.random(92, 172));
    	leftClick();
    	Script.sleep(Script.random(5000, 7000));
    	s.dialogues.clickContinue();
    	Script.sleep(Script.random(1000, 2000));
    	s.dialogues.selectOption(2);
    	Script.sleep(Script.random(1000, 2000));
    	s.dialogues.clickContinue();
    	Script.sleep(Script.random(1000, 2000));
    	s.dialogues.clickContinue();
    }
    
    private boolean isTalkOption() {
    	for (Option option : s.menu.getMenu()) {
    		if (option != null && option.action.equals("Cast") && option.name.contains("NPC Contact")) {
					return true;
			}
		}
    	return false;
    }
    
    private void leftClick() {
        s.mouse.click(false);
    }
}
