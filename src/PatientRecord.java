
package avltree;
public class PatientRecord 
{
	private String fname;
	private String lname;
	private int age;
	
	public PatientRecord(String fname, String lname, int age)
	{
		this.fname = fname;
		this.lname = lname;
		this.age = age;
	}
	
	public void display()
	{
		System.out.println("Patient: " + this.fname + " " + this.lname + " age: " + this.age);
	}

	public int getAge() 
	{
		return age;
	}
	
	
}
