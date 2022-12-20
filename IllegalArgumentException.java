/**
 * The InvalidCourseException class is used when the user attempts to put 
 * invalid information on a Course
 * 
 * @author Sean Erfan
 * ID:114763583
 * Recitation:02
 */
public class IllegalArgumentException extends Exception {
	/**
	 * Sets message of exception to state that user has typed invalid course
	 * information.
	 */

	public IllegalArgumentException(String msg) {
		super(msg);
	}
}
