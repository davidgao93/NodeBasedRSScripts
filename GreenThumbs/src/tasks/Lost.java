package tasks;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;
import org.osbot.rs07.api.model.Player;

import framework.Node;
import data.Data;

public class Lost implements Node {
	
	private Script s;
	private Player me;
	private Data data;
	
	public Lost(Script s, Data data) {
		this.s = s;
		this.me = s.myPlayer();
		this.data = data;
	}
	
	@Override
	public boolean validate() {
		return !data.GateCheck.contains(me) && !data.BarArea.contains(me) && !data.BankArea.contains(me) && !data.SwampArea.contains(me)
				&& !data.LogArea.contains(me) && !data.GateArea.contains(me) && s.objects.closest("Portal") == null;
	}

	@Override
	public void run() throws InterruptedException {
		//s.log("IDLE");
		if (s.getInventory().isFull()) {
			s.getWalking().walk(data.BankArea);
		} else if (me.getY() >= 3457){
			s.getWalking().walk(data.GateArea);
		} else {
			s.getWalking().walk(data.LogArea);
		}
		new ConditionalSleep(500, 1500) {
			@Override
			public boolean condition() {
				return !me.isMoving();
			}
		}.sleep();
	}
}
