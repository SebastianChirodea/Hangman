package game;

public class Hangman 
{
	private static final char SPACE = ' ';
	private static final char HIDDEN = '_';
	
	static final int INITIAL_NUMBER_OF_LIVES = 5;
	
	private static final char CHAR_PREVENTING_UPDATING_USED_LETTERS = '\0';
	
	private char shownWords[];
	private char hiddenWords[];
	private int lives = INITIAL_NUMBER_OF_LIVES;
	private char lastLetter = CHAR_PREVENTING_UPDATING_USED_LETTERS;
	
	public void initializeWords(final String words)
	{
		shownWords = words.toCharArray();
		hiddenWords = new char[shownWords.length];
		
		for(int i = 0; i < hiddenWords.length; i++)
			hideCharacterIfNotSpace(i);
	}
	
	private void hideCharacterIfNotSpace(final int index)
	{
		if(shownWords[index] != SPACE)
			hiddenWords[index] = HIDDEN;
		else
			hiddenWords[index] = SPACE;
	}
	
	public boolean isNotGameOver()
	{
		if(lives == 0)
			return false;
		
		for(int i = 0; i < hiddenWords.length; i++)
			if(hiddenWords[i] == HIDDEN)
				return true;
		return false;
	}
	
	public final GameInfo getGameInfo()
	{
		GameInfo gameInfo = new GameInfo();
		
		gameInfo.shownWords = shownWords;
		gameInfo.hiddenWords = hiddenWords;
		gameInfo.lives = lives;
		gameInfo.lastLetter = lastLetter;
		
		return gameInfo;
	}
	
	public void checkIfLetterMatches(char letter)
	{
		boolean letterMatched = false;
		
		lastLetter = letter;
		
		for(int i = 0; i < hiddenWords.length; i++)
			if(shownWords[i] == letter && hiddenWords[i] == HIDDEN)
			{
				letterMatched = true;
				hiddenWords[i] = letter;
			}
		
		if(letterMatched == false)
			lives--;
	}
	
	public boolean hasPlayerWon() { return lives != 0; }
}
