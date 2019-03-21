package main;

import ui.UI;
import ui.GameDrawer;

import game.Controller;
import game.Hangman;

public class Main {
	public static void main(String args[])
	{
		Hangman hangman = new Hangman();
		Controller ctrl = new Controller(hangman);
		GameDrawer gameDrawer = new GameDrawer();
		UI ui = new UI(ctrl, gameDrawer);
		
		ui.start();
	}
}
