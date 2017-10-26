/**
 * CECS 277 Sec01
 * Project 4 
 * Zombie Object
 * 
 * @author AnthonyLopez
 * e-mail: Anthony.Lopez@student.csulb.edu
 *
 */
public class Zombie {

	Room location;
	
	public int zombieBiteRoutine(){
		int coin = (int) (Math.random()*2)+1;
		
		if (coin == 1){
			return 1;
		}
		return 0;
	}
}
