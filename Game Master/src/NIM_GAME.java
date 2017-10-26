import java.util.ArrayList;
/**
 * CECS 277 Sec01
 * Project 4
 * Nim Game
 * 
 * @author AnthonyLopez
 *e-mail: Anthony.Lopez@student.csulb.edu
 *
 */

public class NIM_GAME extends ARepl {
	
	ArrayList <Integer> rows = new ArrayList <Integer> ();
	int randomRows;
	int randomPebbles;
	int rowChoice = 99;
	int pebbleChoice;
	int totalPebbles;
	NIM_GAME NIM_BRAIN;
	boolean checkRow = true;
	boolean runGame = true;

	/**
	 * Board is initialized 
	 */
	@Override
	public void setup() {
		// TODO Auto-generated method stub
		NIM_BRAIN = new NIM_GAME();
		randomRows = (int) (Math.random()*4)+3;		
		
		for ( int i = 0; i < randomRows; i++ ){
			randomPebbles = (int) (Math.random()*6)+3;
			totalPebbles += randomPebbles;
			//System.out.println(totalPebbles);
			rows.add(i, randomPebbles );
		}
			
	}

	/**
	 * Welcomes user
	 */
	@Override
	public void hello() {
		// TODO Auto-generated method stub
		System.out.println( "Welcome to Nim" );
	}

	/**
	 * Displays board and asks user which row they want to choose
	 */
	@Override
	public void listen() {
		// TODO Auto-generated method stub
		for ( int i = 0; i < rows.size(); i++ ){
			System.out.print( i+1 + ": " );
			if ( rows.get(i) != 0 ){
				for ( int j = 0; j < rows.get(i); j++ ){
					System.out.print( "o" );
					if( (j+1)%3 == 0 ){
						System.out.print( " " );
					}
				}
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println( "Please enter which row you would like to take pebbles from (0 to quit): " );
		rowChoice = CheckInput.checkInt( 0,randomRows );
	}

	/**
	 * checks user's choice to see if it is valid, does user move, and proceeds to do brain's move
	 */
	@Override
	public void respond() {
		// TODO Auto-generated method stub
		
		if ( rowChoice != 0 ){
			while ( checkRow ){
				
				if ( rows.get( rowChoice - 1  ) != 0 ){
				System.out.println( "Please enter the amount of pebbles you would like to take from row "+rowChoice+":");
				pebbleChoice = CheckInput.checkInt();
				checkRow = false;
				}
				
				else if ( rows.get( rowChoice - 1  ) == 0 ){
					System.out.println( "No more pebbles in this row!" );
					System.out.println();
					System.out.println( "Please enter which row you would like to take pebbles from: " );
					rowChoice = CheckInput.checkInt( 1,randomRows );
				}
			}
			
			checkRow = true;
			
			
			if ( pebbleChoice >= rows.get( rowChoice - 1 ) ){
				totalPebbles -= rows.get( rowChoice - 1 );
				rows.set( rowChoice - 1, 0 );
				System.out.println( "User removed all pebbles from row "+rowChoice );
				System.out.println();
			}
			else{
				int tempNum = rows.get( rowChoice - 1 );
				int tempPebbles = tempNum - pebbleChoice;
				totalPebbles -= pebbleChoice;
				rows.set( rowChoice - 1, tempPebbles);
				System.out.println( "User removes "+pebbleChoice+" pebble(s) from row "+rowChoice );
				System.out.println();
			}
			//System.out.println( totalPebbles );
			
			if ( totalPebbles == 0 ){
				System.out.println( "You win!" );
				System.out.println( "Thanks for playing." );
			}
			
			
			
			if ( totalPebbles != 0 ){
				
				for ( int i = 0; i < rows.size(); i++ ){
					System.out.print( i+1 + ": " );
					if ( rows.get(i) != 0 ){
						for ( int j = 0; j < rows.get(i); j++ ){
							System.out.print( "o" );
							if( (j+1)%3 == 0 ){
								System.out.print( " " );
							}
						}
					}
					System.out.println();
				}
				
				System.out.println();
				System.out.println( "BRAIN is making choices..." );
				
				while ( checkRow ){
					
					NIM_BRAIN.rowChoice =  (int) (Math.random()*randomRows)+1;
					NIM_BRAIN.pebbleChoice = (int) (Math.random()*6)+3;
					
					if ( rows.get(NIM_BRAIN.rowChoice - 1) == 0 ){
						System.out.println( "..." );
					}
					else{
						checkRow = false;
					}
				}
				
				checkRow = true;
				
				if ( NIM_BRAIN.pebbleChoice >= rows.get( NIM_BRAIN.rowChoice - 1 ) ) {
					totalPebbles -= rows.get( NIM_BRAIN.rowChoice - 1 );
					rows.set( NIM_BRAIN.rowChoice - 1, 0 );
					System.out.println( "BRAIN removed all pebbles from row "+NIM_BRAIN.rowChoice );
					System.out.println();
				}
				else{
					int tempNum = rows.get( NIM_BRAIN.rowChoice - 1 );
					int tempPebbles = tempNum - NIM_BRAIN.pebbleChoice;
					totalPebbles -= NIM_BRAIN.pebbleChoice;
					rows.set( NIM_BRAIN.rowChoice - 1 , tempPebbles);
					System.out.println( "BRAIN removes "+NIM_BRAIN.pebbleChoice+" pebble(s) from row "+NIM_BRAIN.rowChoice );
					System.out.println();
				}
				//System.out.println( totalPebbles );
				if ( totalPebbles == 0 ){
					System.out.println( "BRAIN wins!" );
					System.out.println( "Thanks for playing." );
				}
			}	
			
		}
		else if ( rowChoice == 0 ){
			System.out.println( "Thanks for playing." );
		}
		
	}

	/**
	 * Checks if program will end
	 */
	@Override
	public boolean endChk() {
		// TODO Auto-generated method stub
		
		if ( rowChoice == 0 || totalPebbles == 0 ){
			runGame = false;
		}
		return runGame;
	}

	/**
	 * Exit message
	 */
	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		System.out.println( "-Exiting Nim-");
		System.out.println();
	}

}
