package game;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

class GameTests {
	
	private Controller ctrl;
	private Hangman hangman;

	@BeforeEach
	void setUpBeforeEach() 
	{
		hangman = new Hangman();
		ctrl = new Controller(hangman);
		hangman.initializeWords("successful test");
	}

	@Test
	void generateWordTest() 
	{
		try
		{
			for(int i = 0; i <= 1000; i++)
				ctrl.generateWords();
		}
		catch(Exception e)
		{
			fail(e.toString());
		}
	}
	
	@Test
	void checkIfLetterMatches_MatchingTest()
	{
		char letters[] = new char[] {'s', 'u', 't', 'e', 'c', 'f', 'l'};
		int expectedLives[] = new int[7];
		Arrays.fill(expectedLives, Hangman.INITIAL_NUMBER_OF_LIVES);
		
		iterateAllLettersAndTestCorrectness(letters, expectedLives);
		Assert.assertEquals(false, hangman.isNotGameOver());
		Assert.assertEquals(true, hangman.hasPlayerWon());
	}
	
	@Test
	void checkIfLetterMatches_NotMatchingTest()
	{
		char letters[] = new char[] {'q', 'a', 'r', 'd', 'p'};
		int expectedLives[] = new int[] {4, 3, 2, 1, 0};
		
		iterateAllLettersAndTestCorrectness(letters, expectedLives);
		Assert.assertEquals(false, hangman.isNotGameOver());
		Assert.assertEquals(false, hangman.hasPlayerWon());
	}
	
	@Test
	void checkIfLetterMatches_MatchingDiscoveredLettersTest()
	{
		char letters[] = new char[] {'s', 'u', 's', 't', 'u'};
		int expectedLives[] = new int[] {5, 5, 4, 4, 3};
		
		iterateAllLettersAndTestCorrectness(letters, expectedLives);
		Assert.assertEquals(true, hangman.isNotGameOver());
	}
	
	@Test
	void checkIfLetterMatches_MixedTest()
	{
		char letters[] = new char[] {'a', 'e', 'u', 'e', 't', 'd', 'r'};
		int expectedLives[] = new int[] {4, 4, 4, 3, 3, 2, 1};
		
		iterateAllLettersAndTestCorrectness(letters, expectedLives);
		Assert.assertEquals(true, hangman.isNotGameOver());
	}
	
	private void iterateAllLettersAndTestCorrectness(char letters[], int expectedLives[])
	{
		for(int i = 0; i < letters.length; i++)
			testIfMatchingLetterReflectsRemainingLives(letters[i], expectedLives[i]);
	}
	
	private void testIfMatchingLetterReflectsRemainingLives(char letter, int expectedLives)
	{
		hangman.checkIfLetterMatches(letter);
		assertLivesAreEqual(expectedLives);
	}
	
	private void assertLivesAreEqual(int expectedLives)
	{
		GameInfo gameInfo = hangman.getGameInfo();
		Assert.assertEquals(expectedLives, gameInfo.lives);
	}

}
