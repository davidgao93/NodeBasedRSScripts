package tasks;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;
import org.osbot.rs07.api.Inventory;

import framework.Node;
import data.Data;

public class Restock implements Node {
	
	private Script s;
	private Data data;
	private Inventory inv;
	
	public Restock(Script s, Data data) {
		this.s = s;
		this.data = data;
		this.inv = s.getInventory();
	}
	
	@Override
	public boolean validate() {
		return s.getObjects().closest("Portal") != null && !inv.contains(data.ESSENCE);
	}

	@Override
	public void run() throws InterruptedException {
		s.log("RESTOCK OPERATION");
		if (inv.contains(data.GLORY1)) {
			inv.interact("Rub", data.GLORY1);
		} else if (inv.contains(data.GLORY2)) {
			inv.interact("Rub", data.GLORY2);
		} else if (inv.contains(data.GLORY3)) {
			inv.interact("Rub", data.GLORY3);
		} else if (inv.contains(data.GLORY4)) {
			inv.interact("Rub", data.GLORY4);
		} else if (inv.contains(data.GLORY5)) {
			inv.interact("Rub", data.GLORY5);
		} else if (inv.contains(data.GLORY6)) {
			inv.interact("Rub", data.GLORY6);
		}
		mouseToEdgeville();
		new ConditionalSleep(10000) {
			@Override
			public boolean condition() {
				return data.edgeville.contains(s.myPlayer());
			}
		}.sleep();
	}
	
    private void mouseToEdgeville() throws InterruptedException {
		s.mouse.move(Script.random(215, 301), Script.random(380, 391));
		Script.sleep(Script.random(500, 1000));
		s.mouse.click(false);
    }
}
