import java.util.Random;
/**
 * CECS 277 Sec01
 * Project 4
 * Guess number game
 * 
 * @author AnthonyLopez
 * e-mail: Anthony.Lopez@student.csulb.edu
 *
 */
public class GUESS_NUMBER extends ARepl{
	
	int menuChoice = 2;
	int userGuess;
	int randomNumber;
	int moves;
	int previousGuess;
	int warmCold1;
	int warmCold2;
	boolean runGame = true;

	/**
	 * Computer generates a random number
	 */
	@Override
	public void setup(){
		moves = 0;
		
		long seed = System.currentTimeMillis();
		Random num = new Random(seed);
		randomNumber = num.nextInt(1000)+1;
		
	}
	/**
	 * welcomes the user
	 */
	@Override
	public void hello(){
		System.out.println( "Welcome to the Number Guess game!" );
	}
	/**
	 * displays menu and gets user input
	 */
	@Override
	public void listen(){
		System.out.println( "Please choose an option:" );
		System.out.println( "1. Guess a number" );
		System.out.println( "0. Quit" );
		System.out.println();
		
		menuChoice = CheckInput.checkInt( 0,1 );
		
	}
	/**
	 * carries out action based on user choice
	 */
	@Override
	public void respond(){
		
		if ( menuChoice == 1 ){
			System.out.println( "Guess a number (1-1000): " );
			userGuess = CheckInput.checkInt( 1,1000 );
			warmCold1 = Math.abs( randomNumber - userGuess );
			moves++;
			
			if ( userGuess != randomNumber && moves == 1 ){
				System.out.println( "Not the right number!" );
				System.out.println();
				previousGuess = userGuess;
				warmCold2 = Math.abs( randomNumber - previousGuess );
			}
			else if ( userGuess != randomNumber && warmCold1 > warmCold2 ){
				System.out.println( "You're getting colder." );
				System.out.println();
				previousGuess = userGuess;
				warmCold2 = Math.abs( randomNumber - previousGuess );
			}
			else if ( userGuess != randomNumber && warmCold1 < warmCold2  ){
				System.out.println( "You're getting warmer." );
				System.out.println();
				previousGuess = userGuess;
				warmCold2 = Math.abs( randomNumber - previousGuess );
			}
			else if ( userGuess == randomNumber ){
				System.out.println( "You guessed " + randomNumber + " in " + moves + " move(s)." );
				moves = 0;
				long seed = System.currentTimeMillis();
				Random num = new Random(seed);
				randomNumber = num.nextInt(1000)+1;
				System.out.println( "Another random number has been created." );
				System.out.println();
			}
			
		}
		else if ( menuChoice == 0 ){
			System.out.println( "Thanks for playing." );
		}
		
	}
	/**
	 * checks to see if loop should end
	 * 
	 * @return	boolean
	 */
	@Override
	public boolean endChk(){
		
		if ( menuChoice == 0 ){
			runGame = false;
		}
		
		return runGame;
	}
	/**
	 * exits game and returns to game master
	 */
	@Override
	public void cleanup(){
		System.out.println( "-Exiting the Number Guess Game-");
		System.out.println();
	}
	

}
