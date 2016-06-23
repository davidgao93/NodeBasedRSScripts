package tasks;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.api.Inventory;
import org.osbot.rs07.api.model.Player;

import framework.Node;
import data.Data;

import org.osbot.rs07.api.ui.Skill;

public class Teleport implements Node {
	private Script s;
	private Inventory inv;
	private Player me;
	private Data data;
	
	public Teleport(Script s, Data data) {
		this.s = s;
		this.me = s.myPlayer();
		this.inv = s.getInventory();
		this.data = data;
	}
	
	@Override
	public boolean validate() {
		return data.bank.contains(me) 
				&& inv.isFull()
				&& s.skills.getDynamic(Skill.HITPOINTS) > data.getHealthThreshold()
				&& s.settings.getRunEnergy() > 60
				&& !s.getInventory().contains(data.GLORY);
	}

	@Override
	public void run() throws InterruptedException {
		s.log("TELEPORT OPERATION");
      	inv.interact("Teleport", data.QPC);
      	Script.sleep(Script.random(3000, 3500));
	}
}
