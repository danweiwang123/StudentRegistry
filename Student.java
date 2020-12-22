//Name: Dan Wei Wang
//StudentID: 500956210
import java.util.ArrayList;

public class Student implements Comparable<Student>
{
  private String name;
  private String id;
  private ArrayList<CreditCourse> courses;
  
  public Student(String name, String id)
  {
	 this.name = name;
	 this.id   = id;
	 courses = new ArrayList<CreditCourse>();
  }
  
  public String getId()
  {
	  return id;
  }
  
  public String getName()
  {
	  return name;
  }
  
  public boolean takenCourse(String courseCode)
  {
    for (int j = 0; j < courses.size(); j++)
    {
      if (courses.get(j).getCode().equalsIgnoreCase(courseCode))
        return true;
	}
    return false;
  }
  
  public void addCourse(String courseName, String courseCode, String descr, String format,String sem, double grade)
  {
	  CreditCourse cc = new CreditCourse(courseName,courseCode,descr,format,sem, grade);
	  cc.setActive();
	  courses.add(cc);
  }
  
  public double getGrade(String courseCode)
  {
	  for (int i = 0; i < courses.size(); i++)
	  {
		if (courses.get(i).getCode().equals(courseCode))
		{
			return courses.get(i).grade; 
		}
	  }
	  return 0;
  }
  
  public void setGrade(String courseCode, double grade)
  {
    for (int k = 0; k < courses.size(); k++)
    {
	   if (courses.get(k).getCode().equalsIgnoreCase(courseCode))
	   {
		  courses.get(k).grade = grade;
		  courses.get(k).setInactive();
	   }
    }
  }
  
  public void printTranscript()
  {
	  for (int i = 0; i < courses.size(); i++)
	  { 
		  if (!courses.get(i).active) 
			  System.out.println(courses.get(i).displayGrade());
	  }
  }
  
  public void printActiveCourses()
  {
	 for (int i = 0; i < courses.size(); i++)
	 {
		 if (courses.get(i).active)
		   System.out.println(courses.get(i).getDescription());
	 } 
  }
  
  public void printCompletedCourses()
  {
	 for (int i = 0; i < courses.size(); i++)
	 {
		 if (!courses.get(i).active)
		   System.out.println(courses.get(i).getDescription());
	 }
  }
  
  // Student has dropped course
  public void removeActiveCourse(String courseCode)
  {
	  for (int i = 0; i < courses.size(); i++)
	 {
		 if (courses.get(i).getCode().equals(courseCode) && courses.get(i).active) 
		 {
            courses.remove(i);
            return;
		 }
	 }
  }
  
  public String toString()
  {
	  return "Student ID: " + id + " Name: " + name;
  }
  
  public int compareTo(Student other)
  {
	  return this.name.compareTo(other.name);
  }
  
  public boolean equals(Object other)
  {
	  Student s = (Student) other;
	  return this.name.equals(s.name) && this.id.equals(s.id);
  }
  
}
