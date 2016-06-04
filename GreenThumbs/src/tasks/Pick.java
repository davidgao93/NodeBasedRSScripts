package tasks;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;
import org.osbot.rs07.api.Inventory;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.model.RS2Object;

import framework.Node;
import data.Data;

public class Pick implements Node {
	private Script s;
	private Inventory inv;
	private Player me;
	
	public Pick(Script s, Data data) {
		this.s = s;
		this.inv = s.getInventory();
		this.me = s.myPlayer();
	}
	
	@Override
	public boolean validate() {
		return s.objects.closest("Fungi on log") != null && !inv.isFull() && onlyMe();
	}

	@Override
	public void run() throws InterruptedException {
		//s.log("PICK OPERATION");
		while(s.objects.closest("Fungi on log") != null && !inv.isFull()) {
			RS2Object fungiObject = s.objects.closest("Fungi on log");
			if (fungiObject != null) {
				fungiObject.interact("Pick");
				new ConditionalSleep(500, 100) {
					@Override
					public boolean condition() {
						return !me.isMoving();
					}
				}.sleep();
			}
		}
	}
	
	boolean onlyMe() {
		for (Player p : s.getPlayers().getAll()) {
			if (p != null && !p.equals(me)) {
				return false;
			}
		}
		return true;
	}
}
