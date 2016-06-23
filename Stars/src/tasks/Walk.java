package tasks;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.model.RS2Object;

import framework.Node;
import data.Data;

public class Walk implements Node {
	
	private Script s;
	private Player me;
	private Data data;
	
	public Walk(Script s, Data data) {
		this.s = s;
		this.me = s.myPlayer();
		this.data = data;
	}
	
	@Override
	public boolean validate() {
		return data.edgeville.contains(me)
				|| data.legend.contains(me) 
				|| data.zanaris.contains(me) 
				|| data.aGate.contains(me)
				|| data.bGate.contains(me)
				|| (s.getObjects().closest("Mysterious glow") != null && !data.altar.contains(me))
				|| (s.getInventory().isFull() && s.getObjects().closest("Altar") == null)
				|| (s.getObjects().closest("Bank booth") != null 
				&& !s.getInventory().isFull()
				&& !data.bank.contains(me));
	}

	@Override
	public void run() throws InterruptedException {
		s.log("WALK OPERATION");
		if (data.edgeville.contains(me)) { // 0
			s.log("CASE 0");
			s.getWalking().webWalk(data.bank);
		} else if (data.legend.contains(me)) { // 1
			s.log("CASE 1");
			RS2Object f = s.getObjects().closest("Fairy ring");
			if (f != null) {
				if (f.isVisible()) {
					f.interact("Use");
				} else {
					s.getWalking().webWalk(data.fairy);
					f.interact("Use");
				}
			}
			new ConditionalSleep(10000) {
				@Override
				public boolean condition() {
					return data.zanaris.contains(me);
				}
			}.sleep();
			Script.sleep(Script.random(2500, 3500));
		} else if (data.zanaris.contains(me)) { // 2
			s.log("CASE 2"); 
			s.getWalking().walk(data.zanaris1);
			new ConditionalSleep(10000) {
				@Override
				public boolean condition() {
					return data.zanaris1.contains(me);
				}
			}.sleep();
		} else if (data.zanaris1.contains(me)) { // 2
			s.log("CASE 3"); 
			s.getWalking().walk(data.zanaris2);
			new ConditionalSleep(10000) {
				@Override
				public boolean condition() {
					return data.zanaris2.contains(me);
				}
			}.sleep();
		} else if (data.zanaris2.contains(me)) { // 2
			s.log("CASE 4"); 
			s.getWalking().walk(data.aGate);
			new ConditionalSleep(10000) {
				@Override
				public boolean condition() {
					return data.aGate.contains(me);
				}
			}.sleep();
		} else if (data.aGate.contains(me)) { // 3
			s.log("CASE 5"); 
			RS2Object trump = s.getObjects().closest("Jutting wall");
			if (trump != null) {
				if (trump.isVisible()) {
					trump.interact("Squeeze-past");
				} else {
					s.getWalking().walk(data.aGateb);
					new ConditionalSleep(10000) {
						@Override
						public boolean condition() {
							return data.aGateb.contains(me);
						}
					}.sleep();
					trump.interact("Squeeze-past");
				}
			}
			new ConditionalSleep(10000) {
				@Override
				public boolean condition() {
					return data.aGatef.contains(me) && !me.isAnimating();
				}
			}.sleep();
		} else if (data.aGatef.contains(me)) { // 4
			s.log("CASE 6"); 
			s.getWalking().walk(data.bGate);
			new ConditionalSleep(10000) {
				@Override
				public boolean condition() {
					return data.bGate.contains(me) || !me.isMoving();
				}
			}.sleep();
			RS2Object trump = s.getObjects().closest("Jutting wall");
			if (trump != null) {
				trump.interact("Squeeze-past");
			}
			new ConditionalSleep(10000) {
				@Override
				public boolean condition() {
					return data.bGatef.contains(me) && !me.isAnimating();
				}
			}.sleep();
		} else if (data.bGatef.contains(me)) { // 6
			s.log("CASE 7"); 
			s.getWalking().walk(data.ruins);
			new ConditionalSleep(10000) {
				@Override
				public boolean condition() {
					return data.ruins.contains(me);
				}
			}.sleep();
		} else if (data.ruins.contains(me)) { // 7
			s.log("CASE 8"); 
			if (s.getObjects().closest("Mysterious ruins") != null) {
				s.getObjects().closest("Mysterious ruins").interact("Enter");
			}
			new ConditionalSleep(10000) {
				@Override
				public boolean condition() {
					return s.getObjects().closest("Mysterious glow") != null;
				}
			}.sleep();
		} else if (s.getObjects().closest("Mysterious glow") != null) { // 8
			s.log("CASE 9");
			s.getWalking().webWalk(data.altar);
		} else { 
			if (s.getObjects().closest("Jutting wall") != null) { // 9
				s.log("CASE 10");
				s.getObjects().closest("Jutting wall").interact("Squeeze-past");
				Script.sleep(Script.random(5000, 6000));
			} else if (s.getObjects().closest("Mysterious ruins") != null) { // 10
				s.log("CASE 11");
				s.getObjects().closest("Mysterious ruins").interact("Enter");
				Script.sleep(Script.random(2000, 3500));
			} else if (s.getObjects().closest("Bank booth") != null) { // 11
				s.log("CASE 12");
				s.getWalking().webWalk(data.bank);
				Script.sleep(Script.random(1000, 3500));
			} else if (s.getObjects().closest("Fairy ring") != null) {
				s.log("CASE 13");
				s.getObjects().closest("Fairy ring").interact("Use");
				new ConditionalSleep(10000) {
					@Override
					public boolean condition() {
						return data.zanaris.contains(me);
					}
				}.sleep();
		      	Script.sleep(Script.random(1500, 3000));
			} else {
				s.log("CASE 14");
				Script.sleep(Script.random(1000, 3000));
			}
		}
	}
}
