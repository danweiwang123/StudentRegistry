//Name: Dan Wei Wang
//StudentID: 500956210
public class Course implements Comparable<Course>
{
   private String code;
   private String name;
   private String description;
   private String format;
   
   public Course()
   {
	  this.code        = "";
	  this.name        = "";
	  this.description = "";
	  this.format      = "";
   }
   
   public Course(String name, String code, String descr, String fmt)
   {
	 this.code        = code;
	 this.name        = name;
	 this.description = descr;
	 this.format      = fmt;
   }
   
   public String getCode()
   {
	   return code;
   }
   
   public String getName()
   {
	   return name;
   }
   
   public String getFormat()
   {
	   return format;
   }
   
   public String getDescription()
   {
	   return code +" - " + name + "\n" + description + "\n" + format;
   }
   
   public String getDescr()
   {
	   return description;
   }
   
   public String getInfo()
   {
	   return code +" - " + name;
   }
   
   public int compareTo(Course other)
   {
	   return this.code.compareTo(other.code);
   }
   
   public static String convertNumericGrade(double score)
   {
 	  if (score >= 90)
 	    return "A+";
 	  else if (score >= 85)
 		return "A";
 	  else if (score >= 80)
 		return "A-";
 	  else if (score >= 77)
 		return "B+";
 	  else if (score >= 73)
 		return "B";
 	  else if (score >= 70)
 	    return "B-";
 	  else if (score >= 67)
 	    return "C+";
 	 else if (score >= 63)
  	    return "C";
 	else if (score >= 60)
 	    return "C-";
 	else if (score >= 57)
 	    return "D+";
 	else if (score >= 53)
 	    return "D";
 	else if (score >= 50)
 	    return "D-";
 	  else return "F";
   }
   
   
}
