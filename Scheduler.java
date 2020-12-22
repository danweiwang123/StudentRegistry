//Name: Dan Wei Wang
//StudentID: 500956210
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeMap;

public class Scheduler
{
    // In main() after you create a Registry object, create a Scheduler object and pass in the students ArrayList/TreeMap
	// If you do not want to try using a Map then uncomment
	// the line below and comment out the TreeMap line
	
	//ArrayList<Student> students;
	
	TreeMap<String,ActiveCourse> schedule;
	
	public Scheduler(TreeMap<String,ActiveCourse> courses)
	{
	  schedule = courses;
	}
	
	/**
	 * adds a block for lecture time in the schedule and will throw exceptions if there are bad inputs
	 * @param courseCode
	 * @param day days of the week
	 * @param startTime 
	 * @param duration how many hours the class last
	 */
	public void setDayAndTime(String courseCode, String day, int startTime, int duration)
	{
		//get active course object with the coursecode
		ActiveCourse ac = schedule.get(courseCode);

		//check for invalid inputs and throws appropriate exceptions
		try{
			if(ac == null)
				throw new UnknownCourseException("Uknown Course: " + courseCode);
			if (!day.equals("MON") && !day.equals("TUE") && !day.equals("WED") && !day.equals("THU") && !day.equals("FRI"))
				throw new InvalidDayException("Invalid Lecture Day");
			if(startTime< 800 || startTime+duration*100>1700) 
				throw new InvalidTimeException("Invalid Lecture Start Time");
			if(!(startTime%100==0))
				throw new InvalidTimeException("Invalid Lecture Start Time");
			if(duration<1 || duration > 3)
				throw new InvalidDurationException("Invalid Lecture Duration");
		}
		catch( Exception e)
		{
			System.out.println(e.getMessage());
			return;
		}

			//checking for time collision
			String[][] calendar = MakeSchedule();
			//convert date to index
			TreeMap<String,Integer> date =new TreeMap<String,Integer>() ;
			date.put("MON",1);
			date.put("TUE",2);
			date.put("WED",3);
			date.put("THU",4);
			date.put("FRI",5);

			int dayIndex = date.get(day);
			//checking for time collision
			try{
				for (int i = 0; i <duration; i++)
				{
					//convert time to index
					int time = (startTime-800)/100 + i;
					//throw exception when there is collision
					if(!calendar[time][dayIndex].equals(""))
						throw new LectureTimeCollisionException("Lecture Time Collision");

				}
			}
			catch( Exception e)
			{
				System.out.println(e.getMessage());
				return;
			}
			//adding the new block into the active course
			ac.setLectureDay(day);
			ac.setLectureDuration(duration);
			ac.setLectureStart(startTime);

	}
	/**
	 * empties the schedule of a course
	 * @param courseCode
	 */
	public void clearSchedule(String courseCode)
	{
		//get course object and clear it
		ActiveCourse ac = schedule.get(courseCode.toUpperCase());
		if(ac == null)
			return;
		ac.ClearSch();
	}
	
	
	public void printSchedule()
	{
		//getting the schedule from MakeSchedule()
		String[][] calendar = MakeSchedule();
		//printing the weekday label
		System.out.println("\t Mon\t Tue\t Wed\t Thu\t Fri");
		
		//Printing the schedule
		for(int i = 0; i < calendar.length ;i++)
		{
			for(int j = 0;j<calendar[0].length;j++)
			{
				System.out.print(calendar[i][j]+"\t");
			}
			System.out.println("");
		}

	}
		
	public void Bonus(String courseCode, int duration)throws NoBlockException
	{
		//can get date from index
		TreeMap<Integer,String> date =new TreeMap<Integer,String>() ;
		date.put(1, "MON");
		date.put(2, "TUE");
		date.put(3, "WED");
		date.put(4, "THU");
		date.put(5, "FRI");

		ActiveCourse ac = schedule.get(courseCode);
		String[][] calendar = MakeSchedule();
		ArrayList<Integer> dur = ac.getLecutreDuration();

		Boolean sameDay;
		//checks to make sure that there are only 3 hours of lectures per week
		int total = 0;
		for(int i : dur){
			total += i;
		}
		try{
			if (total+duration > 3)
				throw new InvalidDurationException("lectures must not exceed 3 hours per week");
		}catch( Exception e)
		{
				System.out.println(e.getMessage());
				return;
		}

		//iterate days in week
		for(int j=1; j < calendar[0].length;j++){
			sameDay = false;
			//check if course is already happening today
			for(int i=0; i < calendar.length;i++)
			{
				String e = calendar[i][j];
				e.replaceAll("\t","");
				if(calendar[i][j].equalsIgnoreCase(courseCode)){
					//true if that is the case
					sameDay= true;
				}
			}
			
			//check if timeslot is available 
			if(!sameDay){

				//check all days
				for(int start = 0; start<calendar.length;start++)
				{
					Boolean available = true;
					for(int end = 0; end < duration;end++)
					{
						//if duration of course goes over time 
						int temp = start+end;
						if(temp >8)
							throw new NoBlockException("");

						//check if there is already a course here
						if(!(calendar[temp][j].equals("")))
						{
							// class is time is not available in current loop
							available = false;
						}
					}
					if(available)
					{
						//create a new block for the course
						int startTime = 800+start*100;
						String day = date.get(j);
						setDayAndTime(courseCode, day, startTime, duration);
						return;
					}
				}
			}
		}
		throw new NoBlockException("");
	}
	
	//getting information of all courses and putting it into 2d array
	public String[][] MakeSchedule()
	{
		//initializing array (9 horizontal and 6 vertical)
		String[][] calendar = new String[9][6];

		//allows for converting index to weekdate
		TreeMap<Integer,String> date =new TreeMap<Integer,String>() ;
		date.put(1, "MON");
		date.put(2, "TUE");
		date.put(3, "WED");
		date.put(4, "THU");
		date.put(5, "FRI");

		// putting the time of each class on left of 
		int time = 800;
		for(int i = 0; i < 9 ;i++)
		{
			calendar[i][0] = String.format("%04d", time); 
			time += 100;
		}

		//Printing the sch
		//row
		for(int i = 0; i < calendar.length ;i++)
		{
			//col
			for(int j = 1;j<calendar[0].length;j++)
			{
				//getting time from checking the row
				time = Integer.parseInt(calendar[i][0]);
				//iterate through all courses
				Set<String> coursesSet = schedule.keySet();
				for(String courses: coursesSet)
				{
					//getting all infomation to make table
					ActiveCourse Course = schedule.get(courses);
					ArrayList<String> Day = Course.getLectureDay();
					ArrayList<Integer> Start = Course.getLectureStart();
					ArrayList<Integer> Duration = Course.getLecutreDuration();
					//iterate ArrayList of Day,Start,Duration
					for(int k = 0; k<Day.size();k++){
						//calculating the end of the class
						int classEnd = Start.get(k) + Duration.get(k)*100;
						//checks if date matches
						if(date.get(j).equals(Day.get(k).toUpperCase()) && !Day.get(k).equals(""))
						{
							//checks if timeslot allows for duration of class
							if(time >= Start.get(k) && time < classEnd)
							{
								//storing coursecode into array
								if(calendar[i][j]==null)
									calendar[i][j] =(Course.getCode());
							}
						}
						
					}
				}
				//storing nothing if there is no class at that time
				if(calendar[i][j]==null){
					calendar[i][j]=("");
				}
			}
		}
		return calendar;
	}
	//custom exceptions
	static class InvalidDayException extends RuntimeException
	{
    public InvalidDayException() {}
    public InvalidDayException(String message) 
    {
	super(message) ;
    }
	}

	static class UnknownCourseException extends RuntimeException
	{
	public UnknownCourseException() {}
	public UnknownCourseException(String message) 
	{
	super(message) ;
	}
	}

	static class InvalidTimeException extends RuntimeException
	{
	public InvalidTimeException() {}
	public InvalidTimeException(String message) 
	{
	super(message) ;
	}
	}

	static class InvalidDurationException extends RuntimeException
	{
	public InvalidDurationException() {}
	public InvalidDurationException(String message) 
	{
	super( message) ;
	}
	}

	static class LectureTimeCollisionException extends RuntimeException
	{
	public LectureTimeCollisionException() {}
	public LectureTimeCollisionException(String message) 
	{
	super(message) ;
	}
	}

	static class NoBlockException extends RuntimeException
	{
	public NoBlockException() {}
	public NoBlockException(String message) 
	{
	super( message) ;
	}
	}
}

