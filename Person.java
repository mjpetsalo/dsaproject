import java.util.ArrayList;
import java.util.Arrays;

public class Person {

    public String[] data;

    /*private String id;
    private String name;
    private String lastname;
    private String birthdate;
    private String gender;
    private String birthplace;
    private String home;
    private String studiedAt;
    private String workplaces;
    private String films;
    private String groupCode;*/


    public Person(String input) {
       data= input.split(",");

    }
}
