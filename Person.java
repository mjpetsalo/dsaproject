import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
* The Person class gives the data so the object Person can be used in the main program, this object will work as an User
*
*@2023-10-20
*@version 1.0
*@author G612383
*/
public class Person implements Comparable<Person> {



    private String[] data;
    
    private ArrayList<String> list = new ArrayList<String>();

    /*private String id;
    private String name;
    private String lastname;
    private String birthdate
    private String gender;
    private String birthplace;
    private String home;
    private String studiedAt;
    private String workplaces;
    private String films;
    private String groupCode;*/

        /**
        *This method is used to separate and read each other of the  datas given in the parameter sent
	*
        *@param input is an String with different data separating each data by a "," 
	*it does not retun any information
	*/
    public Person(String input) {
       data= input.split(",");

    }
	public int getBirthYear(){
		String[] tmp=data[4].split("-");
		return Integer.parseInt(tmp[2]);
	}
        /**
	*This method is used to get the different data we got in the method above, so we can use it later
	*
	*@return data this returns a String[] with the different data we got in the method above
	*/
     public String[] getData(){
        return data;
    }
        /*
	*This methods objective is to get an ArrayList so we can use it later on
	*
        *@return list this returns a ArrayList<String> we may use in other methods
	*/
	public ArrayList<String> getList() {
		return list;
	}


	@Override

	public int compareTo(Person otherPerson) {
		if(CharSequence.compare(this.getData()[5], otherPerson.getData()[5])!=0){
				return CharSequence.compare(this.getData()[5], otherPerson.getData()[5]);
		} else if(CharSequence.compare(this.getData()[2], otherPerson.getData()[2])!=0){
			return CharSequence.compare(this.getData()[2], otherPerson.getData()[2]);
		}
		return (CharSequence.compare(this.getData()[1], otherPerson.getData()[1]));

	}
}
