//Name: Dan Wei Wang
//StudentID: 500956210
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
public class Registry
{
	//Map with the keys being the studentID and value being the student object
	TreeMap<String,Student> students = new TreeMap<String, Student>();
	//Map with the keys being the coursecode and value being the active course object
	TreeMap<String,ActiveCourse> courses = new TreeMap<String, ActiveCourse>();

  	// constructor method using File I/O
	public Registry()  throws IOException {
		//adds the students from a txt file and store into students
		AddStudents("students.txt");
		ArrayList<Student> list = new ArrayList<Student>();
		
		// iterate student objects from students
		Set<String> keySet = students.keySet();
		Iterator<String> iterator = keySet.iterator();
		Student s1,s2,s3,s4,s5,s6;
		s1 = students.get(iterator.next());
		s2 = students.get(iterator.next());
		s3 = students.get(iterator.next());
		s4 = students.get(iterator.next());
		s5 = students.get(iterator.next());
		s6 = students.get(iterator.next());

		// Add some active courses with students
		// CPS209
		String courseName = "Computer Science II";
		String courseCode = "CPS209";
		String descr = "Learn how to write complex programs!";
		String format = "3Lec 2Lab";

		list.add(s2); list.add(s3); list.add(s4);
		//add ActiveCourse objects in courses list
		ActiveCourse ac = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
		courses.put(courseCode, ac);
		// Add course to student list of courses
		s2.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		s3.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		s4.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
	   
		// CPS511
		list.clear();
		courseName = "Computer Graphics";
		courseCode = "CPS511";
		descr = "Learn how to write cool graphics programs";
		format = "3Lec";
		list.add(s1); list.add(s5); list.add(s6);
		ac = new ActiveCourse(courseName,courseCode,descr,format,"F2020",list);
		courses.put(courseCode, ac);

		s1.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		s5.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		s6.addCourse(courseName,courseCode,descr,format,"W2020", 0);

		// CPS643
		list.clear();
		courseName = "Virtual Reality";
		courseCode = "CPS643";	
		descr = "Learn how to write extremely cool virtual reality programs";
		format = "3Lec 2Lab";
		list.add(s1); list.add(s2); list.add(s4); list.add(s6);
		ac = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
		courses.put(courseCode, ac);
		s1.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		s2.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		s4.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		s6.addCourse(courseName,courseCode,descr,format,"W2020", 0); 
		
		// CPS706
		list.clear();
		courseName = "Computer Networks";
		courseCode = "CPS706";
		descr = "Learn about Computer Networking";
		format = "3Lec 1Lab";
		ac = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
		courses.put(courseCode, ac);
			 
		// CPS616
		courseName = "Algorithms";
		courseCode = "CPS616";
		descr = "Learn about Algorithms";
		format = "3Lec 1Lab";
		ac = new ActiveCourse(courseName,courseCode,descr,format,"W2020",list);
		courses.put(courseCode, ac);
	}

	
	//add students objects from file into registry
	public void AddStudents(String filename) throws IOException, BadDataException
	{
		File StudentList = new File(filename);
		Scanner StudentIn = new Scanner(StudentList);
		//check if file is empty
		if (!StudentIn.hasNext())
			throw new BadDataException("File empty");
		// The first number in the file indicates how many Students in the file

		String name= "";
		String id ="";

		//read file input
		while(StudentIn.hasNext())
		{

			//retrieve name and id from file
			if (StudentIn.hasNext())
			{
			name = StudentIn.next();

			// check if name is alphabetical
			String lcase = name.toLowerCase();
			if (!isStringOnlyAlphabet(lcase))
				throw new BadDataException("Bad Data Format: " + filename);
			}

			if (StudentIn.hasNext())
			{
			id = StudentIn.next();
			// check if id is numerical
			if (!isNumeric(id))
				throw new BadDataException("Bad Data Format: " + filename);
			}

			//create student object and store into map
			Student s = new Student(name, id);
			students.put(id, s);
		}

	}

	public TreeMap<String,ActiveCourse> getCourses(){
		return courses;
	}
   public boolean addNewStudent(String name, String id)
   {
	   if (students.get(id)!= null) return false;
	   	   
	   Student s =(new Student(name, id));
	   students.put(id,s);
	   return true;
   }
   
   public boolean removeStudent(String studentId)
   {
	   if (students.get(studentId)!= null)
	   {
	      students.remove(studentId);
		  return true;
	   }
	 return false;
   }
   
   public void printAllStudents()
   {
		Set<String> studentIDs = students.keySet();
		//iterate keys to get all values in students
		for (String key : studentIDs) {
			Student s = students.get(key);
			System.out.println("ID: " + key + " Name: " + s.getName() );   
		}
   }
   

   
   public void addCourse(String studentId, String courseCode)
   {
	   Student s = students.get(studentId);
	   if (s == null) return;
	   
	   if (s.takenCourse(courseCode)) return;
	   
	   ActiveCourse ac = courses.get(courseCode);
	   if (ac == null) return;
	   
	   if (ac.enrolled(studentId)) return;
	
	   //adding student to courses list of students
	   ac.students.add(s);
	   //adding course to students list of courses
	   s.addCourse(ac.getName(),ac.getCode(),ac.getCourseDescription(),ac.getFormat(),ac.getSemester(),0);
   }
   
  
   public void dropCourse(String studentId, String courseCode)
   {
	   Student s = students.get(studentId);
	   if (s == null) return;
	   
	   ActiveCourse ac = courses.get(courseCode);
	   if (ac == null) return;
	   
	   ac.remove(studentId);
	   s.removeActiveCourse(courseCode);
   }
   
   public void printActiveCourses()
   {
	Set<String> courseCode = courses.keySet();
	for (String key : courseCode) {
		ActiveCourse ac = courses.get(key);
		System.out.println(ac.getDescription());  
	}
   }
   
   public void printClassList(String courseCode)
   {
		ActiveCourse ac = courses.get(courseCode);
	    if (ac == null) 
			return; 
	    ac.printClassList();
   }
   
   public void sortCourseByName(String courseCode)
   {
		ActiveCourse ac = courses.get(courseCode);
		if (ac == null) 
		  return;
		ac.sortByName();
   }
   
   public void sortCourseById(String courseCode)
   {
		ActiveCourse ac = courses.get(courseCode);
		if (ac == null) 
			return;
	    ac.sortById();	   
   }
   
   public void printGrades(String courseCode)
   {
	   ActiveCourse ac = courses.get(courseCode);
	   if (ac == null) return;
	   
	   ac.printGrades();
   }
   
   public void printStudentCourses(String studentId)
   {
	   Student s = students.get(studentId);
	   if (s == null) return;
	   
	   s.printActiveCourses();
   }
   
   public void printStudentTranscript(String studentId)
   {
	   Student s = students.get(studentId);
	   if (s == null) return;
	   s.printTranscript();
   }
   
   public void setFinalGrade(String courseCode, String studentId, double grade)
   {
	   Student s = students.get(studentId);
	   if (s == null) return;
	   s.setGrade(courseCode, grade);
	   
	//  ActiveCourse ac = courses.get(courseCode);
	//   if (ac == null) return;
	//   ac.remove(studentId);
   }
   	//check if string is alphabet
	private static boolean isStringOnlyAlphabet(String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (!(Character.isLetter(c))) {
				return false;
			}
		}
		return true;
	}

	//check if string is all numbers
	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (!(Character.isDigit(c))) {
				return false;
			}
		}
		return true;
	
	}
	//custom exception for bad data
	static class BadDataException extends IOException
	{
    public BadDataException() {}
    public BadDataException(String message) 
    {
	super(message) ;
    }
	}

}
