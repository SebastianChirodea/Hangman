package game;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller 
{
	private Hangman hangman;
	
	public Controller(Hangman hangman) { this.hangman = hangman; }
	
	public void generateWords() throws FileNotFoundException, IOException 
	{	
		WordGenerator generator = new WordGenerator();
		
		String noun = generator.generateNoun();
		String adjective = generator.generateAdjective();
		
		String words = adjective + " " + noun;
		hangman.initializeWords(words);
	}
	
	public boolean isNotGameOver() { return hangman.isNotGameOver(); }
	
	public final GameInfo getGameInfo() { return hangman.getGameInfo(); }
	
	public void checkIfLetterMatches(char letter) 
	{ 
		hangman.checkIfLetterMatches(letter); 
	}
	
	public boolean isPlayerWinner() { return hangman.hasPlayerWon(); }
}
