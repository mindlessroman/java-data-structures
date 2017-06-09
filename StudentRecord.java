/**
* #4
* @author: Hannah K @mindlessroman
* @duedate: Spr 2017
*/
import java.util.*;
public class StudentRecord {
	public static Scanner input = new Scanner(System.in);

	private static GDList<Student> studentCollection = new GDList<Student>();
	private static final double GRADE_A = 4.0;
	private static final double GRADE_B = 3.0;
	private static final double GRADE_C = 2.0;
	private static final double GRADE_D = 1.0;
	private static final double GRADE_F = 0.0;

	/**
	 * Student Class
	 *
	 * subclass for Student Object
	 */
	public static class Student {
		private String name;
		private GDList<Course> courseList = new GDList<Course>();


		public Student(String inName) {
			name = inName;
		}

		public  void setName(String inName) { name = inName; }
		public  String getName() { return name; }

		/**
		 * printStudent()
		 *
		 * Print the Student's course list and GPA
		 */
		public void printStudent(){
			System.out.println("------ G P A ----------------------------------");
			System.out.println(this.getName() + ":");
			StudentRecord.GDList.GNode pointer = this.courseList.head;

			double totalCreditHrs = 0.0;
			double creditPoints = 0.0;
			int totalCourses = 0;

			if (pointer == null){
				System.out.println("No courses to print");
			} else {
				do {
					//traverse across list
					//print the course information
					Course current = (Course) pointer.getData();
					System.out.print("Course: " + current.courseName + ", ");
					System.out.print("Grade: " + current.courseGrade + ", ");
					System.out.println("Credit Hrs: " + current.courseCredit);
					//calculations for GPA
					creditPoints += (current.courseGrade * current.courseCredit);
					totalCreditHrs += current.courseCredit;
					totalCourses++;
					//advance pointer
					pointer = pointer.getNext();
				} while (pointer != null);
				double gpa = creditPoints / totalCreditHrs;
				System.out.println("..............................");
				System.out.println("Total GPA: " + gpa + " across " + totalCourses + " course(s)");
			}
			System.out.println("-----------------------------------------------");
		}

		/**
		 * deleteCourseByNum
		 *
		 * @param courseNumString
		 *
		 * using a number from a list of courses, deletes that many nodes into the list
		 */
		public void deleteCourseByNum(String courseNumString) {
			int courseNum = Integer.parseInt(courseNumString);
			GDList.GNode pointer = courseList.head;
			int counter = 0;
			do {
				if ((counter+1) == courseNum) {
					//if counter = searchnum, then delete that node
					Course c = (Course) pointer.getData();
					GDList.GNode deleted = courseList.deleteNode(c);
				}
				counter++;
				pointer = pointer.getNext();
			} while (pointer != null && counter < courseList.size);

		}
	}

	/**
	 * Course class
	 *
	 * subclass for a course object
	 */
	public static class Course {
		private String courseName;
		private double courseGrade;
		private int courseCredit;

		/**
		 * Initialize a course
		 *
		 * @param String name		Name of the course
		 * @param double grade		Grade received
		 * @param int credit		Credit hours
		 */
		public Course(String name, double grade, int credit) {
			courseName = name;
			courseGrade = grade;
			courseCredit = credit;
		}
	}

	/**
	 * addStudent()
	 *
	 * Adds a student to the class's studentCollection object
	 */
	public static void addStudent() {
		System.out.println("");
		System.out.println("Enter the Student's name");
		System.out.print("> ");
		String newStudent = input.nextLine();
		Student inputStudent = new Student(newStudent);
		studentCollection.addToTail(inputStudent);
	}

	/**
	 * findStudent()
	 *
	 * @param String studentName	the searched-for Student
	 * @return Student found 		the found student
	 *
	 * find a student in the studentCollection by the name put in as an argument
	 */
	public static Student findStudent(String studentName) {
		StudentRecord.GDList.GNode pointer = studentCollection.head;
		Student found = null;
		if (pointer == null) {
			System.out.println("There are no students records to search.");
		} else {
			do {
				//traverse across list
				Student current = (Student) pointer.getData();
				if (current.getName().equals(studentName)) {
					//if the current pointer's name is equivalent to the searched for name
					//Found it!
					found = current;
				}
				//advance pointer
				pointer = pointer.getNext();
			} while (pointer != null);
		}
		if (found == null) {
			System.out.println("There is either a typo or no student exists with that name.");
		}
		return found;
	}

	/**
	 * addCourse()
	 *
	 * First lists available students to add the course, then from selected student, adds a course
	 *
	 * @note if a person enters a user who is not listed, it defaults to add to the most recently
	 * 	entered student
	 *
	 */
	public static void addCourse() {
		//Add a course for the most recent student
		System.out.println("");
		System.out.println("..............................");
		StudentRecord.GDList.GNode pointer = studentCollection.head;
		Student searchStudent = null;
		int currentNum = 1;
		if (pointer == null) {
			//there are no records to print
			System.out.println("There are no student records");
		} else {
			System.out.println("Select a student to add a course to their record.");
			do {
				//traverse across list
				Student current = (Student) pointer.getData();
				System.out.println(currentNum + ". " + current.getName() + " ");
				//advance pointer
				pointer = pointer.getNext();
				currentNum++;
			} while (pointer != null);
			System.out.println("..............................");
			System.out.println("");
			System.out.print("Enter the student's name > ");
			String searchName = input.nextLine();
			searchStudent = findStudent(searchName);
		}
		//if the student is not found use most recent
		if (searchStudent == null) {
			searchStudent = (Student) studentCollection.tail.getData();
			System.out.println("Searched student not found.");
			System.out.println("Using most recent student:");
		}
		System.out.println("> " + searchStudent.getName());

		//now that the student has been selected, get the coure information
		System.out.println("Enter the course name");
		System.out.print("> ");
		String newCourse = input.nextLine();
		System.out.println("Enter the course letter grade (A, B, C, D, F)");
		System.out.print("> ");
		String letterGrade_string = input.nextLine().toUpperCase();
		char letterGrade = letterGrade_string.charAt(0);
		double numberGrade = 0.0;
		switch(letterGrade) {
			case 'A':
				numberGrade = GRADE_A;
				break;
			case 'B':
				numberGrade = GRADE_B;
				break;
			case 'C':
				numberGrade = GRADE_C;
				break;
			case 'D':
				numberGrade = GRADE_D;
				break;
			case 'F':
			default:
				//if person enters a letter not listed, default to F
				numberGrade = GRADE_F;
				break;
		}
		System.out.println("Enter the course credit hours (as an int)");
		System.out.print("> ");
		int newCredits = Integer.parseInt(input.nextLine());
		Course addCourse = new Course(newCourse, numberGrade, newCredits);
		searchStudent.courseList.addToTail(addCourse);

	}


	/**
	 * printStudentRecord()
	 *
	 * From the main menu, provides a list of available students. User then can choose which student's record
	 *  is printed
	 *
	 */
	public static void printStudentRecord() {
		System.out.println("");
		System.out.println("-- P R I N T   R E C O R D --------------------");
		StudentRecord.GDList.GNode pointer = studentCollection.head;
		int currentNum = 1;
		if (pointer == null) {
			//there are no records to print
			System.out.println("There are no student records");
		} else {
			System.out.println("Select a student to print their record.");
			do {
				//traverse across list
				Student current = (Student) pointer.getData();
				System.out.println(currentNum + ". " + current.getName() + " ");
				//advance pointer
				pointer = pointer.getNext();
				currentNum++;
			} while (pointer != null);
			System.out.println("");
			System.out.print("Enter the student's name > ");
			String searchName = input.nextLine();
			Student searchStudent = findStudent(searchName);
			if (searchStudent != null) {
				searchStudent.printStudent();
			}
		}
		System.out.println("-----------------------------------------------");
	}

	/**
	 * deleteSingleCourse()
	 *
	 * From main menu, opens a 'delete single course' menu. Select from a list of students. Once a student
	 * 	is selected, then the user chooses a course to delete from the available courses in the list (uses
	 * 	deleteCourseByNum()
	 *
	 * 	@note the menu is selected by the number on the list, not the name itself to prevent issues with
	 * 		typos
	 *
	 */
	public static void deleteSingleCourse() {
		System.out.println("");
		StudentRecord.GDList.GNode pointer = studentCollection.head;
		System.out.println("-- D E L E T E   C O U R S E ------------------");
		Student searchStudent = null;
		int currentNum = 1;
		if (pointer == null) {
			//there are no records to print
			System.out.println("There are no student records");
		} else {
			System.out.println("Select a student to delete a course from their record.");
			do {
				//traverse across list
				Student current = (Student) pointer.getData();
				System.out.println(currentNum + ". " + current.getName() + " ");
				//advance pointer
				pointer = pointer.getNext();
				currentNum++;
			} while (pointer != null);
			System.out.println("");
			System.out.print("Enter the student's name > ");
			String searchName = input.nextLine();
			searchStudent = findStudent(searchName);
		}
		if (searchStudent != null) {
			System.out.println("Which course would you like to remove from " + searchStudent.getName() +"'s record?");
			pointer = searchStudent.courseList.head;
			currentNum = 1;
			do {
				//traverse across list
				Course c = (Course) pointer.getData();
				System.out.println(currentNum + ". " + c.courseName);
				//advance pointer
				pointer = pointer.getNext();
				currentNum++;
			} while (pointer != null);
			System.out.println("");
			System.out.print("Enter the number beside the course to delete > ");
			String searchNum = input.nextLine();
			searchStudent.deleteCourseByNum(searchNum);
			//print newest edition of student record
			searchStudent.printStudent();
			System.out.println("-----------------------------------------------");
		}
	}

	/**
	 * Main method
	 *
	 * @param args
	 *
	 * The main menu
	 */
	public static void main(String[] args) {
		String response = "0";
		System.out.println("Welcome! Select an option:");
		do {
			System.out.println("");
			System.out.println("=========================================================");
			System.out.println("== M E N U ==============================================");
			System.out.println("=========================================================");
			System.out.println("1. Add a Student Record");
			System.out.println("2. Add a single course record");
			System.out.println("3. Delete a course record");
			System.out.println("4. Print a student record");
			System.out.println("=========================================================");

			System.out.println("0. Exit");
			System.out.print("> ");
			response = input.nextLine();

			if(response.equals("1")){
				addStudent();
			}
			if(response.equals("2")){
				addCourse();
			}
			if(response.equals("3")){
				deleteSingleCourse();
			}
			if(response.equals("4")){
				printStudentRecord();
			}
		} while (!response.equals("0"));
		if(response.equals("0")){
			System.out.println("Thank you. Goodbye!");
		}

	}

	/**
	 * GDList -- imported from previous homework
	 *
	 * @param <E>
	 */
	public static class GDList<E> implements Cloneable {
		/**
		 * Nested class
		 * GNode is a generic class representing a node in a list
		 * E is generic type parameter of data
		 * Has both previous and next pointers
		 **/
		private class GNode<E> {
			private E data;
			private GNode<E> previous;
			private GNode<E> next;

			// constructor
			public GNode(E e) {
				data = e;
				previous = null;
				next = null;
			}

			public E getData() { return data; }
			public GNode getPrevious() { return previous; }
			public GNode getNext() { return next; }
			public void setData(E e) { data = e; }
			public void setPrevious(GNode p) { previous = p; }
			public void setNext(GNode p) { next = p; }
		}

		private GNode<E> head;
		private GNode<E> tail;
		private int size;       // number of nodes in a list

		/**
		 * no-arg constructor creates an empty list
		 **/

		public GDList() {
			head = null;
			tail = null;
			size = 0;
		}

		/**
		 * add a new node with data e to the tail
		 * if a node with e already exists, return 1
		 * if not, creatre and add a new node with e to the tail (this new node is the tail now), and return 0
		 * increment the size
		 **/
		public int addToTail(E e) {

			GNode temp = new GNode(e);
			if (head == null) {
				head = temp;
				tail = temp;
			} else {
				if (findNode(head, e) == null) {
					temp.setPrevious(tail);
					tail.setNext(temp);
					tail = temp;
				} else return 1;
			}
			size++;
			return 0;
		}


		/**
		 * delete the node with data e
		 * if a node with e does not exist, return null
		 * if exists, delete the node and return the pointer to the deleted node
		 * decrement the size
		 **/
		public GNode deleteNode(E e) {
			GNode nodeToFind = findNode(head, e);
			if (nodeToFind != null) {
				GNode beforeNode = nodeToFind.getPrevious();
				GNode afterNode = nodeToFind.getNext();
				if (afterNode == null && beforeNode == null) {
					head = null;
					tail = null;
				}
				if (afterNode == null) {
					beforeNode.setNext(null);
					tail = beforeNode;
				} else {
					beforeNode.setNext(afterNode);
				}
				if (beforeNode == null) {
					afterNode.setPrevious(null);
					head = afterNode;
				} else {
					beforeNode.setNext(afterNode);
				}
				size--;
				return nodeToFind;
			} else {
				return null;
			}
		}

		/**
		 * find a node with element e
		 * start the search beginning at node p
		 * if node with e does not exist, return null
		 * if node with e exists, return the pointer to the node
		 **/
		public GNode findNode(GNode p, E e) {
			GNode current = p;
			while (current != null && current.data != e)
				current = current.getNext();
			return current;
		}
	}

}
