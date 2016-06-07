package tasks;
import java.util.Random;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;
import org.osbot.rs07.api.model.Player;

import framework.Node;
import data.Data;

public class WorldHop implements Node {
	
	private Script s;
	private Player me;
	private Data data;
	
	public int[] Worlds = {4, 10, 11, 12, 13, 18, 19, 20, 27, 28, 34, 36,
			41, 42, 46, 51, 52, 53, 54, 57, 58, 59, 60, 62, 65, 67,
			70, 75, 76, 77, 78, 86};
	Random rnd = new Random();
	public int hopToWorld; 
	
	public WorldHop(Script s, Data data) {
		this.s = s;
		this.me = s.myPlayer();
		this.data = data;
	}
	
	@Override
	public boolean validate() {
		if (data.SafeArea.contains(me) 
				&& data.getCurrentWorld() == s.worlds.getCurrentWorld()
				){
			s.log("Saved World and getCurrentWorld matches");
			s.log("Doesn't seem like hop was successful, trying again.");
			return true;
		} else if (me.getY() <= 3457) {
			for (Player p : s.getPlayers().getAll()) {
				if (p != null && !p.equals(me)) {
					s.log("Other player detected, should hop");
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void run() throws InterruptedException {
		s.log("WORLDHOP OPERATION");
		if (!data.SafeArea.contains(me)) {
			s.log("Getting to safe area to World Hop");
			s.getWalking().webWalk(data.SafeArea);
			new ConditionalSleep(1000, 500) {
				@Override
				public boolean condition() {
					return !me.isMoving();
				}
			}.sleep();
		}
        
        if (Worlds != null) {
        	while(s.worlds.getCurrentWorld() == data.getCurrentWorld()) {
        		hopToWorld = getRandom(Worlds);
				s.log("Trying to hop to " + hopToWorld);
				if (s.worlds != null) {
					s.worlds.hop(hopToWorld);
				}
				Script.sleep(Script.random(3200, 3500));
        	}
        	
        	if (s.worlds.getCurrentWorld() != data.getCurrentWorld()) {
    			s.log("Hop successful, old world: " + data.getCurrentWorld() 
    					+ " New World: " + s.worlds.getCurrentWorld());
    			s.getWalking().webWalk(data.LogArea);
    			Script.sleep(Script.random(3200, 3500));
    			data.setCurrentWorld(s.worlds.getCurrentWorld());
    		}
        }
	}
	
	public static int getRandom(int[] array) {
	    int rnd = new Random().nextInt(array.length);
	    return array[rnd];
	}
}
