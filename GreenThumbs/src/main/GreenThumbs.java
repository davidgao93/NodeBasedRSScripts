package main;

import java.awt.Font;
import java.awt.Graphics2D;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import data.Data;
import framework.Node;
import tasks.*;
import api.*;
import gui.GUI;


@ScriptManifest(author = "iMKitty", info = "Collects Mort Myre Fungus", name ="Green Thumbs", version = 1.0, logo = "https://i.gyazo.com/e5836ac024140109ff8db39a5f0334d4.png")
public class GreenThumbs extends Script {
	public static Data data;
	private Timer timer;
	private Mouse m;
	private GUI gui;
	
	public void onStart() throws InterruptedException {
		log("Script initiated");
		timer = new Timer(System.currentTimeMillis());
		m = new Mouse(this);
		gui = new GUI();
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
				new Bank(this, data), 
				new Stamina(this, data),
				new Walk(this, data),
				new Gate(this, data),
				new OpenGate(this, data),
				new WorldHop(this, data),
				new Logs(this, data),
				new Bloom(this, data), 
				new Pick(this, data),
				new Home(this, data),
				new Recharge(this, data),
				new Lost(this, data)
				};
		return tasks;
	}

	private void runTasks(Node[] tasks) throws InterruptedException {
		if (getCamera().getPitchAngle() < 59) {
    		getCamera().movePitch(random(59, 62) + 5);
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
		g.drawString("Green Thumbs", 10, 300);
		g.drawString("Timer: " + timer.parse(timer.getElapsed()), 10, 315);
		g.drawString("Mushrooms Picked: " + data.mushroomCounter, 10, 330);
	}

	public void onExit() {
	}
}
