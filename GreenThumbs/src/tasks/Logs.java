package tasks;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;
import org.osbot.rs07.api.model.Player;

import framework.Node;
import data.Data;

public class Logs implements Node {
	
	private Script s;
	private Player me;
	private Data data;
	
	public Logs(Script s, Data data) {
		this.s = s;
		this.me = s.myPlayer();
		this.data = data;
	}
	
	@Override
	public boolean validate() {
		return data.GateCheck.contains(me) && onlyMe();
	}

	@Override
	public void run() throws InterruptedException {
		//s.log("LOGS OPERATION");
		s.getWalking().walk(data.LogArea);
		new ConditionalSleep(500, 1500) {
			@Override
			public boolean condition() {
				return !me.isMoving();
			}
		}.sleep();
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
