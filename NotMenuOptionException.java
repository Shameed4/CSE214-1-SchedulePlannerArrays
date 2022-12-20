/**
 * The NotMenuOptionException class is used when the user inputs something
 * that is not in the menu
 * 
 * @author Sean Erfan
 */
public class NotMenuOptionException extends Exception {
	/**
	 * Sets message of exception to state that the user didn't select
	 * a menu option
	 */
	public NotMenuOptionException() {
		super("Input was not a menu option. Please enter one of the following: "
				+ "A, G, R, P, F, L, S, B, PB, RB, or Q");
	}
}