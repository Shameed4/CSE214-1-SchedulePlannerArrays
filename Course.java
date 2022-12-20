/**
 * The Course object contains information about a course, including its
 * name, department, code, section, and instructor.
 * 
 * @author Sean Erfan
 * ID:114763583
 * Recitation:02
 */
public class Course {
	private String courseName;
	private String department;
	private int code;
	private byte section;
	private String instructor;
	
	/**
	 * Creates a Course object and initializes section and code to invalid
	 * values
	 */
	public Course() {
		section = -1;
		code = -1;
	}
	
	/**
	 * Creates a Course object and initializes its variables
	 * @param courseName 
	 * 		name of the course
	 * 
	 * @param department  
	 * 		department of the course
	 * 
	 * @param code  
	 * 		code of the course
	 * 
	 * @param section  
	 * 		section of the course
	 * 
	 * @param instructor  
	 * 		instructor of the course
	 */
	public Course(String courseName, String department, int code, byte section,
			String instructor) {
		this.courseName = courseName;
		this.department = department;
		this.code = code;
		this.section = section;
		this.instructor = instructor;
	}

	/**
	 * @return the name of the course
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @param courseName 
	 * 		course name to change to
	 */
	public void setCourseName(String courseName) {
		try {
			if (courseName.length() != 0)
				this.courseName = courseName;
			else 
				throw new InvalidCourseException();
		}
		catch (InvalidCourseException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * @return course's department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department 
	 * 		course department to change to
	 */
	public void setDepartment(String department) {
		try {
			if (department.length() == 3)
				this.department = department;
			else 
				throw new InvalidCourseException();
		}
		catch (InvalidCourseException e) {
			System.out.println(e.getMessage());
		}
		
	}

	/**
	 * @return course code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code 
	 * 		course code to change to
	 */
	public void setCode(int code) {
		try {
			if (100 <= code && code < 1000)
				this.code = code;
			else
				throw new InvalidCourseException();
		}
		catch (InvalidCourseException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @return course section
	 */
	public byte getSection() {
		return section;
	}

	/**
	 * @param section 
	 * 		course section to change to
	 */
	public void setSection(byte section) {
		try {
			if (section > 0 )
				this.section = section;
			else
				throw new InvalidCourseException();
		}
		catch (InvalidCourseException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @return course instructor's name
	 */
	public String getInstructor() {
		return instructor;
	}

	/**
	 * @param instructor 
	 * 		course instructor's name to change to
	 */
	public void setInstructor(String instructor) {
		try {
			if (instructor.length() != 0)
				this.instructor = instructor;
			else
				throw new InvalidCourseException();
		}
		catch (InvalidCourseException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @return 
	 * 		A deep copy of this object, containing the same information.
	 */
	public Object clone() {
		return new Course(courseName, department, code, section, instructor);
	}


	/**
	 * @param obj 
	 * 		Object to compare this to
	 * 
	 * @return true if this and obj are both instances of Course and have
	 * identical fields, false otherwise
	 */
	public boolean equals(Object obj) {
		return obj instanceof Course
				&& courseName.equals(((Course)obj).getCourseName()) 
				&& department.equals(((Course)obj).getDepartment()) 
				&& code == ((Course)obj).getCode() 
				&& section == ((Course)obj).getSection() 
				&& instructor.equals(((Course)obj).getInstructor());
	}
}
