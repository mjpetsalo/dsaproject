import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Person> persons = new ArrayList<Person>();

        try {
            FileInputStream in = new FileInputStream("friends.txt");
            Scanner s =new Scanner(in);

            while(s.hasNextLine()){
                persons.add(new Person(s.nextLine()));

            }
        }


        catch(IOException e){
            System.out.println("File not found");
        }


    }
}