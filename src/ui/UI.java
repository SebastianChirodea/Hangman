package ui;

import game.Controller;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UI
{
	private Controller ctrl;
	private GameDrawer drawer;
	
	public UI(Controller ctrl, GameDrawer drawer) 
	{
		this.ctrl = ctrl;
		this.drawer = drawer;
	}
	
	public void start()
	{
		generateWords();
		drawer.initializeDrawer(ctrl.getGameInfo());
		
		while(ctrl.isNotGameOver())
		{
			drawer.printGame(ctrl.getGameInfo());
			printChoiceMessage();
			continueRunningGame();
		}
		
		printEndGame();
	}
	
	private void printChoiceMessage()
	{
		System.out.print("Your choice: ");
	}
		
	private void generateWords()
	{
		try
		{
			ctrl.generateWords();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private void continueRunningGame()
	{
		char letter;
		
		try
		{
			letter = readPlayersLetter();
			ctrl.checkIfLetterMatches(letter);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private char readPlayersLetter() throws IOException
	{
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
		String input;
		char character;
		
		input = buffReader.readLine();
		checkIfInputIsOneLetter(input);
		character = Character.toLowerCase(input.charAt(0));
		return character;
	}
	
	private void checkIfInputIsOneLetter(String input) throws IOException
	{
		if(input.length() != 1)
			throw new IOException("Warning: You must enter a SINGLE letter!");
		if(!Character.isLetter(input.charAt(0)))
			throw new IOException("Warning: Character must be a LETTER!");
	}
	
	private void printEndGame()
	{
		drawer.printGame(ctrl.getGameInfo());
		if(ctrl.isPlayerWinner())
			System.out.print("You won!");
		else
			System.out.print("You lost!");
	}
}