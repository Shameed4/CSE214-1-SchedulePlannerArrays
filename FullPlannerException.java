/**
 * The FullPlannerException class is used to create an Exception when the
 * user attempts to add a course to a full planner.
 * 
 * @author Sean Erfan
 * ID:114763583
 * Recitation:02
 */
public class FullPlannerException extends Exception {
	/**
	 * Sets the message of the exception to indicate that the Planner is full.
	 */
	public FullPlannerException() {
		super("There is no room in the Planner! "
				+ "Please remove a Course if you wish to add a new Course.");
	}
}
