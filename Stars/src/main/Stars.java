package main;

import java.awt.Font;
import java.awt.Graphics2D;
import java.text.NumberFormat;
import java.util.Locale;

import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import data.Data;
import framework.Node;
import tasks.*;
import api.*;
import gui.GUI;


@ScriptManifest(author = "iMKitty", info = "Makes cosmics", name ="Stars", version = 1.0, logo = "http://www.vfkinsider.com/wiki/images/1/15/Constellation_Star_Panel_-_Small_Dipper.png")
public class Stars extends Script {
	public static Data data;
	private Timer timer;
	private Mouse m;
	private GUI gui;
	int rcXP, rcxph;
	public void onStart() throws InterruptedException {
		log("Script initiated");
		timer = new Timer(System.currentTimeMillis());
		m = new Mouse(this);
		gui = new GUI();
		experienceTracker.start(Skill.RUNECRAFTING);
		while (gui.running) {
			sleep(10);
		}
		
	}
	
	@Override
	public int onLoop() throws InterruptedException {
		runTasks(buildTasks());
		return 100;
	}

	private Node[] buildTasks() {
		Node[] tasks = {
				new Glory(this, data),
				new Stamina(this, data),
				new Eat(this, data),
				new Bank(this, data), 
				new Teleport(this, data),
				new Walk(this, data),
				new Make(this, data),
				new Restock(this, data),
				new Repair(this, data),
				};
		return tasks;
	}

	private void runTasks(Node[] tasks) throws InterruptedException {
		if (getCamera().getPitchAngle() < 59 || getCamera().getYawAngle() < 350) {
    		getCamera().movePitch(random(64, 67));
    		getCamera().moveYaw(random(350, 359));
    	}
		for (int i = 0; i < tasks.length; i++) {
			if (tasks[i].validate()) {
				tasks[i].run();
			}
		}
	}

	public void onPaint(Graphics2D g) {
		m.draw(g);
		g.setFont(new Font("Myriad Pro", Font.PLAIN, 12));
		g.drawString("Stars", 10, 300);
		g.drawString("Timer: " + timer.parse(timer.getElapsed()), 10, 315);
		rcXP = experienceTracker.getGainedXP(Skill.RUNECRAFTING);
		rcxph =  experienceTracker.getGainedXPPerHour(Skill.RUNECRAFTING);
		g.drawString("Runecrafting XP Gained: " + rcXP + " (" + NumberFormat.getNumberInstance(Locale.US).format(rcxph) + "xp/h)", 10, 330);
	
	}

	public void onExit() {
	}
}
