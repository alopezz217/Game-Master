import java.util.Scanner;

/**
 * CECS 277 Sec 01
 * Project 4
 * Checks input
 * 
 * @author AnthonyLopez
 * e-mail: Anthony.Lopez@student.csulb.edu
 *
 */
public class CheckInput {
	/**
	 * Checks validity
	 * 
	 * @return valid number
	 */
	public static int checkInt(){
		Scanner in = new Scanner( System.in) ; 
		boolean valid = false;
		int validNum = 0;
		
		while( !valid ) {
			if( in.hasNextInt() ) { 
				validNum = in.nextInt(); 
				valid = true;
			} else {
				//clear buffer of junk input
				in.next();
				System.out.print("Invalid input - Retry: ");
			} 
		}
		return validNum;
	}
	/**
	 * Checks user's input
	 * 
	 * @param low	lowest value 
	 * @param high	high value
	 * @return	valid number
	 */
	public static int checkInt( int low, int high ) {
		Scanner in = new Scanner( System.in );
		boolean valid = false;
		int validNum = 0;
		
		while( !valid ) {
			if( in.hasNextInt() ) {
				validNum = in.nextInt();
				if( validNum >= low && validNum <= high ){
					valid = true; 
				} 
				else {
						System.out.print( "Invalid - Retry: " ); }
			} else {
				//clear buffer of junk input
				in.next();
				System.out.print( "Invalid input - Retry: " );
			} 
		}
		return validNum;
	}
}