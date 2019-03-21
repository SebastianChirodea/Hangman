package ui;

import game.GameInfo;

import java.util.List;
import java.util.ArrayList;

public class GameDrawer 
{
	private static final int HORIZONTAL_LIMIT = 26;
	private static final int VERTICAL_LIMIT = 7;
	
	private static final int EMPTY_SPACE_UNTIL_GALLOW = 5;
	private static final int BEAM_LENGTH = 7;
	
	private static final int SPACE_BETWEEN_IMAGE_AND_WORD = 5;
	
	private static final int SPACE_BETWEEN_LETTERS = 2;
	
	private static final int VERTICAL_POSITION_FOR_USED_LETTERS = 2;
	private static final char CHAR_PREVENTING_UPDATING_USED_LETTERS = '\0';
	
	private int lives;
	private char image[][] = new char[VERTICAL_LIMIT][HORIZONTAL_LIMIT];
	private char words[];
	private List<Character> usedLetters;
	
	public void initializeDrawer(final GameInfo gameInfo)
	{
		char words[] = gameInfo.hiddenWords;
		this.words = new char[words.length * SPACE_BETWEEN_LETTERS];
		usedLetters = new ArrayList<Character>();
		
		for(int i = 0; i < words.length; i++)
		{
			this.words[i * 2] = '_';
			this.words[i * 2 + 1] = ' ';
		}
	}
	
	public void printGame(final GameInfo gameInfo)
	{
		updateImage(gameInfo.lives);
		updateWord(gameInfo);
		updateUsedLetters(gameInfo.lastLetter);
		printSeparator();
		printImage();
		printWord();
	}
	
	private void updateImage(int lives)
	{
		if(this.lives == lives)
			return;
		switch(lives)
		{
		case 5:
			drawSpace();
			drawTerrain();
			break;
		case 4:
			drawGallow();
			break;
		case 3:
			drawHead();
			break;
		case 2:
			drawBody();
			break;
		case 1:
			drawArms();
			break;
		case 0:
			drawLegs();
			break;
		}
		this.lives = lives;
	}
	
	private void drawSpace()
	{
		int i, j;
		for(i = 0; i < VERTICAL_LIMIT; i++)
			for(j = 0; j < HORIZONTAL_LIMIT; j++)
				image[i][j] = ' ';
	}
	
	private void drawTerrain()
	{
		for(int i = 0; i < HORIZONTAL_LIMIT - SPACE_BETWEEN_IMAGE_AND_WORD; i++)
			image[VERTICAL_LIMIT - 1][i] = '_';
	}
	
	private void drawGallow()
	{
		drawPole();
		drawBeam();
		drawNose();
	}
	
	private void drawPole()
	{
		for(int i = 1; i < VERTICAL_LIMIT; i++)
			image[i][EMPTY_SPACE_UNTIL_GALLOW] = '|';
	}
	
	private void drawBeam()
	{
		for(int i = EMPTY_SPACE_UNTIL_GALLOW;
				i < EMPTY_SPACE_UNTIL_GALLOW + BEAM_LENGTH; i++)
			image[0][i] = '_';
	}
	
	private void drawNose() 
	{ 
		image[1][EMPTY_SPACE_UNTIL_GALLOW + BEAM_LENGTH - 1] = '|'; 
	}
	
	private void drawHead()
	{
		image[2][EMPTY_SPACE_UNTIL_GALLOW + BEAM_LENGTH - 1] = 'O';
	}
	
	private void drawBody()
	{
		image[3][EMPTY_SPACE_UNTIL_GALLOW + BEAM_LENGTH - 1] = '|';
		image[4][EMPTY_SPACE_UNTIL_GALLOW + BEAM_LENGTH - 1] = '|';
	}
	
	private void drawArms() { drawMembers(3); }
	
	private void drawLegs() { drawMembers(5); }
	
	private void drawMembers(int membersPosition)
	{
		image[membersPosition][EMPTY_SPACE_UNTIL_GALLOW + BEAM_LENGTH - 2] = '/';
		image[membersPosition][EMPTY_SPACE_UNTIL_GALLOW + BEAM_LENGTH] = '\\';
	}
	
	private void updateWord(final GameInfo gameInfo)
	{
		if(lives != 0)
			updateWord(gameInfo.hiddenWords);
		else
			updateWord(gameInfo.shownWords);
	}
	
	private void updateWord(final char words[]) 
	{ 
		for(int i = 0; i < words.length; i++)
			revealLetterIfDiscovered(words, i);
	}
	
	private void revealLetterIfDiscovered(char words[], int index)
	{
		if(words[index] != '_')
			this.words[index * SPACE_BETWEEN_LETTERS] = words[index];
	}
	
	private void updateUsedLetters(char lastLetter)
	{
		if(lastLetter == CHAR_PREVENTING_UPDATING_USED_LETTERS)
			return;
		if(usedLetters.contains(lastLetter))
			return;
		usedLetters.add(lastLetter);
	}
	
	private void printSeparator()
	{
		System.out.print("\n\n---------------------------------\n");
	}
	
	private void printImage()
	{
		int i, j;
		for(i = 0; i < VERTICAL_LIMIT; i++)
		{
			System.out.println();
			for(j = 0; j < HORIZONTAL_LIMIT; j++)
				System.out.print(image[i][j]);
			
			if(i == VERTICAL_POSITION_FOR_USED_LETTERS)
				printUsedLetters();
		}
	}
	
	private void printUsedLetters()
	{
		System.out.print("Used letters: ");
		for(char letter : usedLetters)
			System.out.print(letter);
	}
	
	private void printWord()
	{
		int i;
		for(i = 0; i < words.length ; i++)
			System.out.print(words[i]);
		System.out.print("\n\n");
	}
}
