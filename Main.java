import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
*Main class is created in order to do a menu for the application, 
*with the function of the application inplemented also.
*
*@author G612383
*@version 1.0
* @since   2023-10-20
*/
public class Main {

    public static ArrayList<Person> persons = new ArrayList<Person>();

    /**
    *This is the main function of the class to execute the menu
    */
    public static void main(String[] args) {


        int choize;

        do {

            choize = readTheMenuAndWrite();
            executeTheJob(choize);




        }while(choize != 0);


    }

    /**
    *The function readTheMenuAndWrite is to write in the screen the 
    *options of the menu and return what have choozen the user
    *@return int deciding what they want the users.
    */
    public static int readTheMenuAndWrite() {

        int choize;

        Scanner esk = new Scanner(System.in);

        System.out.println("1-load into");
        System.out.println("2-load a relationship with someone");
        System.out.println("3-print out people");
        System.out.println("4-Get a list of friends by surname");
        System.out.println("5-Get the ID and Surname of the citizens of same city");
        System.out.println("0-log out");

        choize = esk.nextInt();

        return choize;


    }

    /**
    * The function executeTheJob is the main body of the menu and execute each part of it
    *@atribute choize is a int the determines what option chose the user.
    */
    public static void executeTheJob(int choize) {

        Scanner esk = new Scanner(System.in);

        switch (choize) {
            case 1:


                try {
                    FileInputStream in = new FileInputStream("people.txt");
                    Scanner s = new Scanner(in);

                    while (s.hasNextLine()) {
                        persons.add(new Person(s.nextLine()));


                    }
                } catch (IOException e) {
                    System.out.println("File not found");
                }


                break;
            case 2:

                if (persons.isEmpty()) {
                    break;
                }
                try {
                    FileInputStream in = new FileInputStream("friends.txt");
                    Scanner s = new Scanner(in);
                    String[] relation;
                    int position1;
                    int position2;


                    while (s.hasNextLine()) {

                        int i = 1;

                        relation = s.nextLine().split(",");


                        position1 = findPerson(relation[i]);
                        position2 = findPerson(relation[i - 1]);

                        if (position1 != -1 && position2 != -1) {
                            persons.get(position1).getList().add(relation[i - 1]);
                            persons.get(position2).getList().add(relation[i]);
                        }


                    }

                    // System.out.println(persons.get(0).getList().toString());


                } catch (IOException e) {
                    System.out.println("File not found");
                }


                break;
            case 7:
                ArrayList<Person> qwer = bornBetween();
                String bornbetweenoutput = "People born between given years are: ";
                for (int a = 0; a < qwer.size(); a++) {

                    bornbetweenoutput += qwer.get(a).getData()[0]+" ";
                }
                System.out.println(bornbetweenoutput);
                break;
            case 3:

                for (int i = 0; i < persons.size(); i++) {

                    System.out.println(Arrays.toString(persons.get(i).getData()));
                }
                break;

            case 4:
                System.out.println("Give surname");
                Scanner sc = new Scanner(System.in);
                String surname = sc.nextLine();
                List<Person> temp = getPeopleBySurname(surname);

                String ou = "";
                for (int a = 0; a < temp.size(); a++) {
                    System.out.println("Friends of " + temp.get(a).getData()[0] + " are ");
                    ou += temp.get(a).getList().toString();
                }
                System.out.println(ou);
                break;

            case 5:

                System.out.println("Write down the city you want to search?");

                String s = esk.next();


                for (int i = 0; i < persons.size(); i++) {

                    if ((persons.get(i).getData()[5]).equals(s)) {
                    

                        System.out.println(i + " citizens ID is " + persons.get(i).getData()[0] + " and the surname is " + persons.get(i).getData()[2]);

                    }
                }
                break;
            case 6:
            	
                try {
                    FileInputStream in = new FileInputStream("residential.txt");
                    Scanner eska = new Scanner(in);
                    
                    int j = textLinesNum("residential.txt");
                    
                    int k = 0;

                    while (eska.hasNextLine()) {
                       
                    	int i = k+1;
                    	
                    	boolean b = true;
                    	
                    	while(i<j && !b) { if( persons.get(i).getData()[6].equals( persons.get(k).getData()[6])) {b=false;} i++;}
                    	
                    	if(b == true) {
                    		
                    		
                    		
                    	}
                    	
                    	k++;
                    	

                    }
                } catch (IOException e) {
                    System.out.println("File not found");
                }

            	
            	
            	break;
            case 0:
                System.out.println("Byebye, come back soon.");
                break;

        }
    }



    /**
    *findPerson is a helper function that recceive a ID and
    *returns the position of the object in the list persons
    *@atribute ID is an String to identificate a person.
    *@return  returns the position in the list of the ID person.
    */
    public static int findPerson(String ID) {

        boolean found = false;

        int i = 0;

        while(!found && i < persons.size()) {

            if(persons.get(i).getData()[0].equals(ID)) {

                found = true;

                return i;


            }

            i++;

        }

        return -1;

    }
    
    public static int textLinesNum(String text) {
    	
    	int i = 0;
    	
    	try {
            FileInputStream in = new FileInputStream("residential.txt");
            Scanner eska = new Scanner(in);

            while (eska.hasNextLine()) i++;
        } catch (IOException e) {
            System.out.println("File not found");
        }
    	
    	return i;
    	
    }


    public static ArrayList<Person> getPeopleBySurname(String surname){
        ArrayList<Person> list = new ArrayList<Person>();
        int i=0;
        while(i<persons.size()){
            if(persons.get(i).getData()[2].equals(surname)){
                list.add(persons.get(i));
            }
            i++;
        }
        return list;
    }

       public static ArrayList<Person> bornBetween(){

        ArrayList<Person> ret = new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        System.out.println("Give the year from which search");
        int y1=sc.nextInt();
        System.out.println("Give the year until which to search");
        int y2=sc.nextInt();
        for(int i=0;i<persons.size();i++){
            if((persons.get(i).getBirthYear()>=y1 )&&(persons.get(i).getBirthYear()<=y2)){
                ret.add(persons.get(i));
            }
        }
       Collections.sort(ret);
        return ret;

    }

}
