package tasks;
import java.util.Random;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.api.model.Player;

import framework.Node;
import data.Data;

public class WorldHop implements Node {
	
	private Script s;
	private Player me;

	public int[] Worlds = {5, 6, 13, 14, 22, 30, 38, 46, 54, 53, 62, 69, 70, 77, 78, 86};
	Random rnd = new Random();
	public int hopToWorld; 
	
	public WorldHop(Script s, Data data) {
		this.s = s;
		this.me = s.myPlayer();
	}
	
	@Override
	public boolean validate() {
		if (me.getY() <= 3457) {
			for (Player p : s.getPlayers().getAll()) {
				if (p != null && !p.equals(me)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void run() throws InterruptedException {
		//s.log("WORLDHOP OPERATION");
		hopToWorld = getRandom(Worlds);
        
        if (Worlds != null) {
        	s.log("Trying to hop to " + hopToWorld);
        	s.worlds.hop(hopToWorld);
        }
		Script.sleep(Script.random(3200, 3500));
	}
	
	public static int getRandom(int[] array) {
	    int rnd = new Random().nextInt(array.length);
	    return array[rnd];
	}
}
