package tasks;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.model.RS2Object;

import framework.Node;
import data.Data;

public class OpenGate implements Node {
	
	private Script s;
	private Player me;
	private Data data;
	
	public OpenGate(Script s, Data data) {
		this.s = s;
		this.me = s.myPlayer();
		this.data = data;
	}
	
	@Override
	public boolean validate() {
		return data.GateArea.contains(me);
	}

	@Override
	public void run() throws InterruptedException {
		//s.log("OPENGATE OPERATION");
		RS2Object gate = s.objects.closest("Gate");
     	if (gate != null) {
	     	s.getWalking().walk(gate.getPosition());
	     	gate.interact("Open");
	     	Script.sleep(Script.random(1200, 1500));
     	}
	}
}
