/**
 * The PlannerManager class creates a main Planner and backup Planner and
 * handles input from the user to be 
 * 
 * @author Sean Erfan
 */
import java.util.Scanner;

public class PlannerManager {
	static Scanner s;
	static Planner p;
	static Planner bp;

	/**
	 * Initializes Scanner and both Planners, and runs the entire program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		s = new Scanner(System.in);
		p = new Planner();
		bp = (Planner) (p.clone());

		showMenu();

		String input = s.nextLine().toUpperCase();
		while (!input.equals("Q")) {
			useInput(input);
			showMenu();
			input = s.nextLine().toUpperCase();
		}
		System.out.println("Program terminating successfully...");
	}

	/**
	 * Prints menu listing every input option to the user.
	 */
	public static void showMenu() {
		System.out.print("(A) Add Course" + "\n(G) Get Course" 
				+ "\n(R) Remove Course" + "\n(P) Print Courses in Planner" 
				+ "\n(F) Filter by Department Code" + "\n(L) Look For Course"
		        + "\n(S) Size" + "\n(B) Backup" 
				+ "\n(PB) Print Courses in Backup" + "\n(RB) Revert to Backup"
		        + "\n(Q) Quit" + "\n\nEnter a selection: ");
	}

	/**
	 * Calls methods depending on the option that the user selected.
	 * 
	 * @param input The input given by the user
	 * 
	 * @return false if the user entered 'Q', true otherwise
	 */
	public static boolean useInput(String input) {
		try {
			System.out.println();
			if (input.equals("A")) {
				Course c = createCourse();

				System.out.print("Enter position: ");
				int position = s.nextInt();
				s.nextLine();

				System.out.println();
				p.addCourse(c, position);
			}

			else if (input.equals("G")) {
				System.out.print("Enter position: ");
				int position = s.nextInt();
				s.nextLine();
				System.out.println();
				if (p.getCourse(position) != null) {
					System.out.println(p.getCourseListHeader());
					System.out.println(p.getCourseTableInfo(position));
				}
			}

			else if (input.equals("R")) {
				System.out.print("Enter position: ");
				int position = s.nextInt();
				System.out.println();
				p.removeCourse(position);
				s.nextLine();
			}

			else if (input.equals("P")) {
				System.out.println("Planner:");
				p.printAllCourses();
			}

			else if (input.equals("F")) {
				System.out.print("Enter department code: ");
				String filter = s.nextLine();
				System.out.println();
				Planner.filter(p, filter);
			}

			else if (input.equals("L")) {
				Course c = createCourse();
				p.exists(c);
			}

			else if (input.equals("S")) {
				int size = p.size();
				if (size == 1)
					System.out.println("There is 1 course in the planner.");
				else
					System.out.println("There are " + size 
							+ " courses in the planner.");
			}

			else if (input.equals("B")) {
				bp = (Planner) (p.clone());
				System.out.println("Created a backup of the current planner.");
			}

			else if (input.equals("PB")) {
				System.out.println("Planner (Backup):");
				bp.printAllCourses();
			}

			else if (input.equals("RB")) {
				p = (Planner) (bp.clone());
				System.out.println("Planner successfully reverted " 
						+ "to the backup copy.");
			}

			else if (input.equals("Q")) {
				return false;
			}

			else
				throw new NotMenuOptionException();
		}

		catch (NotMenuOptionException e) {
			System.out.println(e.getMessage());
		}

		System.out.println();
		return true;
	}

	/**
	 * Prompts input from the user to give the necessary 
	 * information that is needed 3-\frac{n}{4^{n}}to create a Course object.
	 * 
	 * @return The course created using the user's input.
	 */
	public static Course createCourse() {
		Course c = new Course();
		do {
			System.out.print("Enter course name: ");
			c.setCourseName(s.nextLine());
		} while (c.getCourseName() == null);

		do {
			System.out.print("Enter department: ");
			c.setDepartment(s.nextLine());
		} while (c.getDepartment() == null);

		do {
			System.out.print("Enter course code: ");
			c.setCode(s.nextInt());
			s.nextLine();
		} while (c.getCode() == -1);

		do {
			System.out.print("Enter course section: ");
			c.setSection(s.nextByte());
			s.nextLine();
		} while (c.getSection() == -1);

		do {
			System.out.print("Enter instructor: ");
			c.setInstructor(s.nextLine());
		} while (c.getInstructor() == null);

		return c;
	}
}