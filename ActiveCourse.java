//Name: Dan Wei Wang
//StudentID: 500956210
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Active University Course
 
public class ActiveCourse extends Course
{
   public  ArrayList<Student> students; // map id to name
   private String             semester;
   private ArrayList<Integer> lectureStart; //arraylist to store multiple blocks
   private ArrayList<Integer> lectureDuration;
   private ArrayList<String> lectureDay;

    
   public ActiveCourse(String name, String code, String descr, String fmt,String semester,ArrayList<Student> students)
   {
	   super(name, code, descr, fmt);
	   this.semester = semester;
	   this.students = new ArrayList<Student>(students);
	   lectureStart = new ArrayList<Integer>();
	   lectureDuration= new ArrayList<Integer>();
	   lectureDay = new ArrayList<String>();
   }
   
 
   public String getSemester()
   {
	   return semester;
   }
   
   public void printClassList()
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   System.out.println(students.get(i).toString());
	   }
   }
   
   public void printGrades()
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   Student s = students.get(i);
		   System.out.println(s.getId() + " " + s.getName() + " " + s.getGrade(getCode()));
	   }
   }
   
   public String getDescription()
   {
	   return super.getDescription() + " " + semester + " Enrolment: " + students.size() +  "\n";
   }
   
   public String getCourseDescription()
   {
	   return getDescr();
   }
    
   public double getGrade(String studentId)
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   if (studentId.equals(students.get(i).getId()))
		   {
			   return students.get(i).getGrade(getCode());
		   }
	   }
	   return 0;
   }
   
   public boolean enrolled(String studentId)
   {
	   for (int i = 0; i < students.size(); i++)
	   {
		   if (studentId.equals(students.get(i).getId()))
		     return true;
	   }
	   return false;
   }
   
   public void remove(String id)
   {
	   for (int j = 0; j < students.size(); j++)
	   {
   		   Student s = students.get(j);
   		   if (s.getId().equals(id))
   		   {
   		     students.remove(j);
   		     return;
   		   }
 	   }
    }
   
      
   public void sortByName()
   {
 	  Collections.sort(students, new NameComparator());
   }
   
   private class NameComparator implements Comparator<Student>
   {
   	public int compare(Student a, Student b)
   	{
   	  return a.getName().compareTo(b.getName());	  
   	}
   }
   
   public void sortById()
   {
 	  Collections.sort(students, new IdComparator());
   }
   
   private class IdComparator implements Comparator<Student>
   {
   	public int compare(Student a, Student b)
   	{
   	  return a.getId().compareTo(b.getId());	  
   	}
	}
	public void setLectureStart(int lectureStart)
	{
			this.lectureStart.add(lectureStart);
	}
	public void setLectureDuration(int lectureDuration)
	{
		this.lectureDuration.add(lectureDuration);
	}
	public void setLectureDay(String lectureDay)
	{
		this.lectureDay.add(lectureDay);
	}
	public ArrayList<Integer> getLectureStart()
	{
		return lectureStart;
	}
	public ArrayList<Integer> getLecutreDuration()
	{
		return lectureDuration;
	}
	public ArrayList<String> getLectureDay()
	{
		return lectureDay;
	}
	public void ClearSch()
	{
		//emptys list and replace them with "" and 0
		lectureDay.clear();
		lectureDuration.clear();
		lectureStart.clear();
		lectureDay.add("");
		lectureDuration.add(0);
		lectureStart.add(0);
	}
}
