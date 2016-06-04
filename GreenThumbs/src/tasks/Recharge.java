package tasks;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Skill;

import framework.Node;
import data.Data;

public class Recharge implements Node {
	private Script s;
	
	public Recharge(Script s, Data data) {
		this.s = s;
	}
	
	@Override
	public boolean validate() {
		return s.objects.closest("Portal") != null;
	}

	@Override
	public void run() throws InterruptedException {
		//s.log("RECHARGE OPERATION");
		RS2Object portal = s.objects.closest("Portal");
      	RS2Object kharyrllPortal = s.objects.closest("Kharyrll Portal");
      	RS2Object altar = s.objects.closest("Altar");
      	
      	if (portal != null && kharyrllPortal != null) {
			if (s.skills.getDynamic(Skill.PRAYER) == s.skills.getStatic(Skill.PRAYER)) {
		        if (kharyrllPortal.isVisible()) {
		        	kharyrllPortal.interact("Enter");
		        	Script.sleep(Script.random(2000, 2500));
		        } else {
		        	s.getWalking().walk(kharyrllPortal.getPosition());
		        }
			} else {
		      	if(altar.isVisible()) {
		      		altar.interact("Pray");
		      		Script.sleep(Script.random(4000, 4500));
		      	} else {
		      		s.getWalking().walk(altar.getPosition());
		      	}
			}
      	}
	}
}
