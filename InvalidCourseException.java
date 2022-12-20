/**
 * The InvalidCourseException class is used when the user attempts to put 
 * invalid information on a Course
 * 
 * @author Sean Erfan
 * ID:114763583
 * Recitation:02
 */
public class InvalidCourseException extends Exception {
	/**
	 * Sets message of exception to state that user has typed invalid course
	 * information.
	 */
	public InvalidCourseException() {
		super("Invalid course information. "
				+ "Course name and instructor should have at least one letter. "
				+ "Department should be three letters. "
				+ "Code should be between 100 and 999. " 
				+ "Section should be greater than 0. ");
	}
}
