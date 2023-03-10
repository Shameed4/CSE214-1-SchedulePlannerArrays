/**
 * The InvalidCourseException class is used when the user attempts to put
 * invalid information on a Course
 * 
 * @author Sean Erfan
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
