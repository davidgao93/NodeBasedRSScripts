package tasks;

import org.osbot.rs07.script.Script;

import framework.Node;
import data.Data;

public class Make implements Node {
	
	private Script s;
	private Data data;
	
	public Make(Script s, Data data) {
		this.s = s;
		this.data = data;
	}
	
	@Override
	public boolean validate() {
		return s.getObjects().closest("Altar") != null 
				&& s.getInventory().contains(data.ESSENCE)
				&& data.altar.contains(s.myPlayer());
	}

	@Override
	public void run() throws InterruptedException {
		s.log("MAKE OPERATION");
		s.getObjects().closest("Altar").interact("Craft-rune");
		Script.sleep(Script.random(3500, 4000));
		if (Script.random(0, 10) < 8) {
			s.getInventory().interact("Empty", data.SP); 
			s.getInventory().interact("Empty", data.MP); 
			s.getInventory().interact("Empty", data.LP); 
		} else {
			s.getInventory().interact("Empty", data.LP); 
			s.getInventory().interact("Empty", data.MP);
			s.getInventory().interact("Empty", data.SP); 
		}

		s.getObjects().closest("Altar").interact("Craft-rune");
		Script.sleep(Script.random(3500, 4000));
	}
}
