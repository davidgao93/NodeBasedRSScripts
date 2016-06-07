package tasks;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.api.Inventory;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Tab;

import framework.Node;
import data.Data;

public class Bloom implements Node {
	
	private Script s;
	private Player me;
	private Inventory inv;
	private Data data;
	
	public Bloom(Script s, Data data) {
		this.s = s;
		this.me = s.myPlayer();
		this.inv = s.getInventory();
		this.data = data;
	}
	
	@Override
	public boolean validate() {
		return data.LogArea.contains(me) && !s.getInventory().isFull() && onlyMe();
	}

	@Override
	public void run() throws InterruptedException {
		s.log("BLOOM OPERATION");
		if (inv.contains(2972)) {
			inv.drop(2972);
		}
		
		RS2Object logs = s.objects.closest("Rotting log");
		if (logs != null) {
			if (MethodProvider.gRandom(0,1) == 0) {
				data.CurrentLog = data.AlphaLog;
			} else {
				data.CurrentLog = data.BetaLog;
			}
			if (!me.getPosition().equals(data.CurrentLog)) {
				data.CurrentLog.interact(s.getBot(), "Walk here");
				Script.sleep(Script.random(1000, 1500));
			}
	      	s.getTabs().open(Tab.INVENTORY);
	     	inv.getItem(2963).interact("Cast Bloom");
	     	Script.sleep(Script.random(2000, 2500));
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
