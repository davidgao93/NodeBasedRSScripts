package tasks;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;
import org.osbot.rs07.api.model.Player;

import framework.Node;
import data.Data;

public class Walk implements Node {
	
	private Script s;
	private Player me;
	private Data data;
	
	public Walk(Script s, Data data) {
		this.s = s;
		this.me = s.myPlayer();
		this.data = data;
	}
	
	@Override
	public boolean validate() {
		return data.BankArea.contains(me) && !s.getInventory().isFull();
	}

	@Override
	public void run() throws InterruptedException {
		//s.log("WALK OPERATION");
		s.getWalking().walk(data.SwampArea);
		new ConditionalSleep(500, 1500) {
			@Override
			public boolean condition() {
				return !me.isMoving();
			}
		}.sleep();
	}
}
