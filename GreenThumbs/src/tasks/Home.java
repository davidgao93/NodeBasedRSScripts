package tasks;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.api.Inventory;
import org.osbot.rs07.api.model.Player;

import framework.Node;
import data.Data;
import org.osbot.rs07.api.ui.Spells;

public class Home implements Node {
	private Script s;
	private Inventory inv;
	private Player me;
	private Data data;
	
	public Home(Script s, Data data) {
		this.s = s;
		this.me = s.myPlayer();
		this.inv = s.getInventory();
		this.data = data;
	}
	
	@Override
	public boolean validate() {
		return data.LogArea.contains(me) && inv.isFull();
	}

	@Override
	public void run() throws InterruptedException {
		//s.log("HOME OPERATION");
      	s.magic.castSpell(Spells.NormalSpells.HOUSE_TELEPORT);
      	Script.sleep(Script.random(4100, 5500));
	}
}
