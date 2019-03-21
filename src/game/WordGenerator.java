package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Random;

public class WordGenerator 
{
	private static final String adjectivesPath = "D:\\Side Projects\\Java\\Hangman\\adjectives.txt";
	private static final String nounsPath = "D:\\Side Projects\\Java\\Hangman\\nouns.txt";
	
	private FileReader reader;
	private BufferedReader buffReader;
	
	public String generateNoun() throws FileNotFoundException, IOException
	{
		return generatedWord(nounsPath);
	}
	
	public String generateAdjective() throws FileNotFoundException, IOException
	{
		return generatedWord(adjectivesPath);
	}
	
	private String generatedWord(String filePath) throws FileNotFoundException, IOException
	{
		int randomPosition;
		
		initializeReader(filePath);
		randomPosition = generateRandomPosition();
		return wordFromFileAtPosition(randomPosition);
	}
	
	private void initializeReader(String filePath) throws FileNotFoundException
	{
		reader = new FileReader(filePath);
		buffReader = new BufferedReader(reader);
	}
	
	private int generateRandomPosition() throws IOException
	{
		int wordsTotal = readWordsTotalFromFirstLine();
		Random random = new Random();
		return random.nextInt(wordsTotal) + 1;
	}
	
	private int readWordsTotalFromFirstLine() throws IOException
	{
		String wordsTotal = buffReader.readLine();
		return Integer.parseInt(wordsTotal);
	}
	
	private String wordFromFileAtPosition(int randomPosition) throws IOException
	{
		int currentPosition = 0;
		String word = "";
		
		while(currentPosition < randomPosition)
		{
			word = buffReader.readLine();
			currentPosition++;
		}
		
		return word;
	}
}
