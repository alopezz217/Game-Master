/**
 * CECS 277 Sec01
 * Project 4
 * Abstract class
 * 
 * @author AnthonyLopez
 * e-mail: Anthony.Lopez@student.csulb.edu
 *
 */
public abstract class ARepl implements IRepl{
	
	public void repl(){
		
		setup();
		hello();
		
		while ( endChk() ) {
			
			listen();
			
			respond();
			
			endChk();
			
		}
		
		cleanup();
		
	}

}
