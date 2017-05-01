import javax.swing.JOptionPane;

/**
 * This class allows for actoins between the
 * user and a computer player and gives specific 
 * methods that should be used to randomize the 
 * actions of the computer.
 * 
 * Note: Eventually add additional methods to allow
 * computer to behave smarter
 * @author P. Cavan Gary-O'Toole
 *
 */
public class AI {
	
	/**
	 * Generates a random number between 0 (inclusive) and 
	 * the designated max number (exclusive) then rounds
	 * the decimal number down.
	 * @param max
	 * 		The max value that the random number could get close
	 * 		to, but never reach
	 * @return
	 * 		Returns a random number between 0 (inclusive) and the max 
	 *		 given (exclusive) as an integer
	 */
	public static int randomNumber(int max)
	{
		//the double produced from the random number generator
		double number = Math.random() * max;
		
		//casts the double to an integer so it can be used as an index
		int actualNumber = (int) number;
		
		return actualNumber;
	}
	
	

}
