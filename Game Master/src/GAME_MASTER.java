/**
 * CECS 277 Sec01
 * Project 4 
 * Game master menu
 * 
 * @author AnthonyLopez
 * e-mail: Anthony.Lopez@student.csulb.edu
 *
 */
public class GAME_MASTER extends ARepl {
	
	int menuChoice = 3;
	boolean runProgram = true;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GAME_MASTER g = new GAME_MASTER();
		g.repl();
	}
	
	/**
	 * no set up needed
	 */
	@Override
	public void setup(){
		
	}
	/**
	 * welcomes the user
	 */
	@Override
	public void hello(){
		System.out.println( "-Welcome to the Game Master-" );
	}
	/**
	 * displays menu and gets user input
	 */
	@Override
	public void listen(){
		System.out.println( "Please choose a game:" );
		System.out.println( "1. Number Guess" );
		System.out.println( "2. Nim" );
		System.out.println( "3. Mystery House" );
		System.out.println( "4. TBA" );
		System.out.println( "0. Quit" );
		
		menuChoice = CheckInput.checkInt( 0,4 );
		
	}
	/**
	 * does action based on user choice
	 */
	@Override
	public void respond(){
		
		if ( menuChoice == 1 ){
			GUESS_NUMBER newGame = new GUESS_NUMBER();
			newGame.repl();
		}
		else if ( menuChoice == 2 ){
			NIM_GAME newGame = new NIM_GAME();
			newGame.repl();
		}
		else if ( menuChoice == 3 ){
			MystHouse newGame = new MystHouse();
			newGame.repl();
		}
		else if ( menuChoice == 4 ){
			System.out.println( "To Be Announced" );
			System.out.println();
		}
		else if ( menuChoice == 0 ){
			System.out.println( "Thanks for using the Game Master" );
		}
		
	}
	/**
	 * checks if loop should end
	 * 
	 * @return boolean
	 */
	@Override
	public boolean endChk(){
		
		if ( menuChoice == 0 ){
			runProgram = false;
		}
		
		return runProgram;
	}
	/**
	 * says good bye to user
	 */
	@Override
	public void cleanup(){
		System.out.println( "Bye." );
	}

}
