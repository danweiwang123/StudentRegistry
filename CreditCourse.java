import java.util.ArrayList;

public class CreditCourse extends Course 
{
	private String  semester;
	public  double  grade;
	public  boolean active;
	
	public CreditCourse(String name, String code, String descr, String fmt,String semester, double grade)
	{
		// redundant 
		super(name, code, descr, fmt);
		this.semester = semester;
		this.grade    = grade;
		active = false;
	}
	
	public void setActive()
	{
		active = true;
	}
	
	public void setInactive()
	{
		active = false;
	}
	
	public String displayGrade()
	{
		return getCode() + " " + getName() + " " + semester + " Grade " + convertNumericGrade(grade); 
	}
	
}
