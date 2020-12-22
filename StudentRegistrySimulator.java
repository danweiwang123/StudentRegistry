//Name: Dan Wei Wang
//StudentID: 500956210
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentRegistrySimulator 
{
  public static void main(String[] args)
  {
	  Registry registry = null;
	  Scheduler scheduler = null;
	  try{
			  registry = new Registry();
			  //constructing scheduler object with the courses in registry
			  scheduler = new Scheduler(registry.getCourses());
		}
		catch(RuntimeException e1)
		{
			System.out.println(e1.getMessage());
			return;
			
		}catch(IOException e) { 
			System.out.println(e.getMessage());
			return;
		}


	  Scanner scanner = new Scanner(System.in);
	  System.out.print(">");
	  
	  while (scanner.hasNextLine())
	  {
		  String inputLine = scanner.nextLine();
		  if (inputLine == null || inputLine.equals("")) continue;
		  
		  Scanner commandLine = new Scanner(inputLine);
		  String command = commandLine.next();
		  
		  if (command == null || command.equals("")) continue;
		  
		  else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST"))
		  {
			  registry.printAllStudents();
		  }
		  else if (command.equals("Q") || command.equals("QUIT"))
			  return;
		  
		  else if (command.equalsIgnoreCase("REG"))
		  {
			  String name = null;
			  String id   = null;
			  if (commandLine.hasNext())
			  {
				 name = commandLine.next();
				 // check for all alphabetical
				 String lcase = name.toLowerCase();
				 if (!isStringOnlyAlphabet(lcase))
				 {
				   System.out.println("Invalid Characters in Name " + name);
				   continue;
				 }
			  }
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();
				 // check for all numeric
				 if (!isNumeric(id))
				 {
				   System.out.println("Invalid Characters in ID " + id);
				   continue;
				 }
				 if (!registry.addNewStudent(name,id))
					 System.out.println("Student " + name + " already registered");
			  }
			 
		  }
		  else if (command.equalsIgnoreCase("DEL"))
		  {
			  if (commandLine.hasNext())
			  {
				 String id = commandLine.next();
				 // check for all numeric
				 
				 if (!isNumeric(id))
				   System.out.println("Invalid Characters in student id " + id);
				 registry.removeStudent(id);
			  }
		  }
		  else if (command.equalsIgnoreCase("PAC"))
		  {
			  registry.printActiveCourses();
		  }		  
		  else if (command.equalsIgnoreCase("PCL"))
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
			     registry.printClassList(courseCode.toUpperCase());
			  }
		  }
		  else if (command.equalsIgnoreCase("PGR"))
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
			     registry.printGrades(courseCode.toUpperCase());
			  }
		  }
		  else if (command.equalsIgnoreCase("ADDC"))
		  {
			  String courseCode = null;
			  String id         = null;
			  
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();
			  }
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
				 registry.addCourse(id, courseCode.toUpperCase());
			  }
		  }
		  else if (command.equalsIgnoreCase("DROPC"))
		  {
			  String courseCode = null;
			  String id         = null;
			  
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();
			  }
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
				 registry.dropCourse(id, courseCode.toUpperCase());
			  }
			  
		  }
		  else if (command.equalsIgnoreCase("PSC"))
		  {
			  String studentId = null;
			  if (commandLine.hasNext())
			  {
				 studentId = commandLine.next();
			     registry.printStudentCourses(studentId);
			  }
		  }
		  else if (command.equalsIgnoreCase("PST"))
		  {
			  String studentId = null;
			  if (commandLine.hasNext())
			  {
				 studentId = commandLine.next();
			     registry.printStudentTranscript(studentId);
			  }
		  }
		  else if (command.equalsIgnoreCase("SFG"))
		  {
			  String courseCode = null;
			  String id         = null;
			  String grade      = null;
			  double numGrade = 0;
			  
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
			  }
			  if (commandLine.hasNext())
			  {
				 id = commandLine.next();
			  }
			  if (commandLine.hasNext())
			  {
				  grade = commandLine.next();
				  if (!isNumeric(grade)) continue;
				  numGrade = Integer.parseInt(grade);
				  registry.setFinalGrade(courseCode.toUpperCase(), id, numGrade);
			  }
			  
		  }
		  else if (command.equalsIgnoreCase("SCN")) //fix??
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
			     registry.sortCourseByName(courseCode.toUpperCase());
			  }
		  }
		  else if (command.equalsIgnoreCase("SCI"))
		  {
			  String courseCode = null;
			  if (commandLine.hasNext())
			  {
				 courseCode = commandLine.next();
				 registry.sortCourseById(courseCode.toUpperCase());
			  }
		  }
		  else if (command.equalsIgnoreCase("SCH"))
		  {
			String courseCode = null;
			String Day        = null;
			String startTime  = null;
			int numStart = 0;
			String Duration = null;
			int numDuration  = 0;
			
			if (commandLine.hasNext())
			{
			courseCode = commandLine.next();
			}
			if (commandLine.hasNext())
			{
			Day = commandLine.next();
			}
			if (commandLine.hasNext())
			{
				startTime = commandLine.next();
				if (!isNumeric(startTime)) continue;
				numStart = Integer.parseInt(startTime);
			}				
			if (commandLine.hasNext())
			{
				Duration = commandLine.next();
				if (!isNumeric(Duration)) continue;
				numDuration = Integer.parseInt(Duration);
				scheduler.setDayAndTime(courseCode.toUpperCase(), Day.toUpperCase(), numStart, numDuration);
			}
		  }
		  else if (command.equalsIgnoreCase("CSCH"))
		  {
			String courseCode = null;
			if (commandLine.hasNext())
			{
				courseCode = commandLine.next();
				scheduler.clearSchedule(courseCode.toUpperCase());
			}
		
		  }
		  else if (command.equalsIgnoreCase("PSCH"))
		  {
			scheduler.printSchedule();
		  }
		  else if (command.equalsIgnoreCase("Bonus"))
		  {
			String courseCode = null;
			String Duration = null;
			int numDuration  = 0;
			if (commandLine.hasNext())
			{
			courseCode = commandLine.next();
			}
			if (commandLine.hasNext())
			{

				Duration = commandLine.next();
				if (!isNumeric(Duration)) continue;
				numDuration = Integer.parseInt(Duration);
				//handle exception when timeslot unavailable
				try{
					scheduler.Bonus(courseCode.toUpperCase(), numDuration);
				}catch(Exception e)
				{
					System.out.println("Unable to find a timeslot");
				}
		  	}
		  }
		System.out.print("\n>");
	}

	  
  }
  
  private static boolean isStringOnlyAlphabet(String str) 
  { 
      return ((!str.equals("")) 
              && (str != null) 
              && (str.matches("^[a-zA-Z]*$"))); 
  } 
  
  public static boolean isNumeric(String str)
  {
      for (char c : str.toCharArray())
      {
          if (!Character.isDigit(c)) return false;
      }
      return true;
  }

}