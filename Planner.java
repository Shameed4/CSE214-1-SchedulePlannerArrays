/**
 * The Planner class implements an abstract data type for a list of courses 
 * supporting some common operations on such lists
 * @author Sean Erfan
 */
public class Planner {
	private static final int MAX_COURSES = 5;
	private Course[] courseList;
	private int itemCount;

	/**
	 * Constructs an instance of the planner with no Course objects in it.
	 */
	public Planner() {
		courseList = new Course[MAX_COURSES + 1];
		itemCount = 0;
	}

	/** 
	 * @return Number of courses currently in the list
	 */
	public int size() {
		return itemCount;
	}
	
	/**
	 * @param list
	 * 		The list of courses to add.
	 */
	public void setCourseList(Course[] list) {
		courseList = list;
	}
	
	/**
	 * @param size
	 * 		The number of items in the course list.
	 */
	public void setSize(int size) {
		itemCount = size;
	}
	

	/**
	 * Inserts a course to the planner at the specified position. If there is
	 * already a course in this position, that course (and all courses ahead of
	 * it) will be shifted.
	 * 
	 * @param newCourse
	 * 		The course to be added
	 * 
	 * @param position
	 * 		The position to add the course to	
	 * 
	 * @throws FullPlannerException
	 * 		The Planner is full
	 * 
	 * @throws IllegalArgumentException
	 * 		The position is invalid because it is not between 1 and size()+1
	 */
	public void addCourse(Course newCourse, int position) {
		try {
			if (size() == MAX_COURSES)
			{
				throw new FullPlannerException();
			}
			else if (position == size() + 1)
			{
				courseList[position] = newCourse;
				itemCount++;
				System.out.println(getShortCourseInfo(position) 
						+ " successfully added to planner.");
			}
			else if (0 < position && position < size() + 1) 
			{
				for (int i = size(); i >= position; i--) {
					courseList[i+1] = courseList[i];
				}
				courseList[position] = newCourse;
				itemCount++;
				System.out.println(getShortCourseInfo(position) 
						+ " successfully added to planner.");
			}
			else {
				throw new IllegalArgumentException("Position must be "
						+ betweenMessage(1, size() + 1));
			}
		}
		catch (FullPlannerException e) {
			System.out.println(e.getMessage());
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Adds a course to the end of the list.
	 * 
	 * @param newCourse
	 * 		The course to be added
	 */
	public void addCourse(Course newCourse) {
		addCourse(newCourse, size() + 1);
	}

	/**
	 * Removes a course from the list at the specified position, shifting all
	 * elements that were after it.
	 * 
	 * @param position
	 * 		The position of the element that will be removed
	 * 
	 * @throws IllegalArgumentException
	 * 		The position is invalid since it is not between 1 and size()
	 */
	public void removeCourse(int position) {
		try {
			if (0 < position && position <= itemCount) {
				System.out.println(getShortCourseInfo(position) 
						+ " has been successfully removed from the planner.");
				for (int i = position; i < itemCount; i++) {
					courseList[i] = courseList[i+1];
				}
				courseList[itemCount] = null;
				itemCount--;

			}
			else {
				if (size() == 0)
					throw new IllegalArgumentException("There are no courses "
							+ "to remove!");
				throw new IllegalArgumentException("Position must be " 
						+ betweenMessage(Math.min(size(), 1), size()));
			}
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Returns a course at a specified position.
	 * @param position
	 * 		The position of the course to be returned.
	 * 
	 * @return
	 * 		The course in the specified position.
	 * 
	 * @throws IllegalArgumentException
	 * 		Position is invalid since it is not between 1 and size().
	 */
	public Course getCourse(int position) {
		try {
			if (0 < position && position <= size())
				return courseList[position];
			else {
				if (size() == 0)
					throw new IllegalArgumentException("There are no courses!");
				throw new IllegalArgumentException("Position must be " 
						+ betweenMessage(1, size()));
			}
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Prints a table of all courses in a Planner in the specified department
	 * 
	 * @param planner
	 * 		The planner to filter
	 * 
	 * @param department
	 * 		The department to search
	 */
	public static void filter(Planner planner, String department) {
		System.out.println(planner.getCourseListHeader());
		for (int i = 1; i <= MAX_COURSES; i++)
		{
			if (planner.courseList[i] == null)
				break;
			if (planner.getCourse(i).getDepartment().equals(department)) {
				System.out.println(planner.getCourseTableInfo(i));
			}
		}
	}

	/**
	 * Checks if a course is on the Planner
	 * 
	 * @param course
	 * 		The course to search for
	 * 
	 * @return
	 * 		True if the course is on the planner, false if it is not
	 */
	public boolean exists(Course course) {
		for (int i = 1; i <= MAX_COURSES; i++) {
			if (courseList[i] == null) {
				System.out.println("Course not found");
				return false;
			}
			else if (course.equals(courseList[i])) {
				System.out.println(getShortCourseInfo(i) 
						+ " is found in the planner at position " + i + ".");
				return true;
			}
		}
		return false;
	}

	/**
	 * Creates a Planner containing the same courses as this one.
	 * 
	 * @return
	 * 		The new Planner
	 */
	public Object clone() {
		Planner copy = new Planner();
		Course[] courseListCopy = new Course[MAX_COURSES + 1];
		for (int i = 1; i <= size(); i++) {
			courseListCopy[i] = courseList[i];
		}

		copy.setCourseList(courseListCopy);
		copy.itemCount = size();

		return copy;
	}

	/**
	 * Returns a shortened version of the course's information that will be
	 * printed when a course is added or removed.
	 * 
	 * @param index
	 * 		The index of the course
	 * @return
	 * 		A string containing the course's department, code, and section
	 */
	private String getShortCourseInfo(int index) {
		return courseList[index].getDepartment() + " " 
				+ courseList[index].getCode() + ".0" 
				+ courseList[index].getSection();
	}

	/**
	 * Prints all courses in this planner.
	 */
	public void printAllCourses() {
		System.out.println(toString());
	}

	/**
	 * Returns a table header for when multiple courses
	 * need to be printed (when filtering, printing entire table, etc)
	 * 
	 * @return The table header   
	 */
	public String getCourseListHeader() {
		return String.format("%-4s%-26s%-11s%4s%8s%11s", "No.", 
				"Course Name", "Department", "Code", "Section", "Instructor") 
				+ "\n---------------------------------------"
				+ "----------------------------------------";
	}

	/**
	 * Returns details about a course
	 * 
	 * @param index
	 * 		The index of the course to return info for
	 * 
	 * @return
	 * 		A string containing the course name, 
	 * 		department, code, section, and instructor
	 */
	public String getCourseTableInfo(int index) {
		return String.format("%4s%-26s%-11s%4s%8s%-26s", index + " ", 
				courseList[index].getCourseName(), 
				courseList[index].getDepartment(), 
				courseList[index].getCode()+"", 
				"0" + courseList[index].getSection(), 
				" " + courseList[index].getInstructor());
	}

	/**
	 * Returns a String stating either an exact number or a range depending
	 * on num1 and num2, used for exceptions
	 * 
	 * @param num1
	 * 		The first number
	 * 
	 * @param num2
	 * 		The second number
	 * 
	 * @return
	 * 		An exact number if num1 == num2 and a range otherwise.
	 */
	private String betweenMessage(int num1, int num2) {
		if (num1 == num2)
			return "exactly " + num1 + ".";
		else
			return "between " + num1 + " and " + num2 + ".";
	}

	/**
	 * @return
	 * 		The table of courses
	 */
	public String toString() {
		String str = getCourseListHeader();
		for (int i = 1; i <= MAX_COURSES; i++) {
			if (courseList[i] == null)
				break;
			str += "\n" + getCourseTableInfo(i);
		}
		return str;
	}
	
	/**
	 * @param obj
	 * 		The object to compare to this one
	 * 
	 * @return true if obj is a Planner and has identical courses to this,
	 * false otherwise
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Planner) {
			Planner candidate = (Planner) obj;
			if (size() == candidate.size()) {
				for (int i = 1; i <= size(); i++) {
					if (!getCourse(i).equals(candidate.getCourse(i))) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
}
