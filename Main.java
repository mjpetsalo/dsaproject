import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *Main class is created in order to do a menu for the application,
 *with the function of the application also inplemented.
 *
 *@author G612383
 *@version 1.0
 * @since   2023-10-20
 */
public class Main {

    public static ArrayList<Person> persons = new ArrayList<Person>();
    public static HashMap<HashSet<String>,HashSet<Person>> profiles = new HashMap<>();

    /**
     *This is the main function of the class to execute the menu
     */
    public static void main(String[] args) {


        int choice;

        do {

            choice = readTheMenuAndWrite();
            executeTheJob(choice);




        }while(choice != 0);


    }

    /**
     *The function readTheMenuAndWrite is used to write in the screen the
     *options of the menu and return whatever choice the user has chosen
     *@return the number of the case the user has chosen
     */
    public static int readTheMenuAndWrite() {

        int choice;

        Scanner esk = new Scanner(System.in);

        System.out.println("1-load into");
        System.out.println("2-load a relationship with someone");
        System.out.println("3-print out people");
        System.out.println("4-Get a list of friends by surname");
        System.out.println("5-Get the ID and Surname of the citizens of same city");
        System.out.println("6-Get people that their bithplace match with hometown of people decribed on residential file");
        System.out.println("7-Get people born between two years");
        System.out.println("8-Get people divided to groups by their favourite movies");
        System.out.println("9-Get the shortest chain of firends between two people");
        System.out.println("10-Get the cliques larger than 4 people");
        System.out.println("0-log out");

        choice = esk.nextInt();

        return choice;


    }

    /**
     *The function executeTheJob is the main body of the menu and executes each part of it
     *@param choice is an int value that determines which option the user has chosen.
     */
    public static void executeTheJob(int choice) {

        Scanner esk = new Scanner(System.in);

        switch (choice) {
            case 1:


                try {
                    System.out.println("Give the name of the file to read:  ");
                    FileInputStream in = new FileInputStream(esk.nextLine());
                    Scanner s = new Scanner(in);

                    while (s.hasNextLine()) {
                        persons.add(new Person(s.nextLine()));


                    }
                    System.out.println("Load succesful!");
                } catch (IOException e) {
                    System.out.println("File not found");
                }


                break;
            case 2:

                if (persons.isEmpty()) {
                    break;
                }
                try {
                    System.out.println("Give the name of the file to read: ");
                    FileInputStream in = new FileInputStream(esk.nextLine());
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
                            persons.get(position1).getPersonList().add(persons.get(position2));
                            persons.get(position2).getPersonList().add(persons.get(position1));
                        }


                    }

                    // System.out.println(persons.get(0).getList().toString());

                    System.out.println("Load succesful!");
                } catch (IOException e) {
                    System.out.println("File not found");
                }


                break;
            case 7:
                ArrayList<Person> qwer = bornBetween();
                String bornbetweenoutput = "People born between the years given are: ";
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

                System.out.println("Write down the city you want to search");

                String s = esk.next();


                for (int i = 0; i < persons.size(); i++) {

                    if ((persons.get(i).getData()[5]).equals(s)) {


                        System.out.println(i + " citizens ID is " + persons.get(i).getData()[0] + " and the surname is " + persons.get(i).getData()[2]);

                    }
                }
                break;
            case 6:

               BST_class bst = BST_load("residential");
                
                System.out.println(bst.inorder());
		
				int resMax = textLinesNum("residential");

                String[] inord = bst.inorder().split(",");
                
                String lag[] = inord[0].split("-");
                
                String lag2[]= inord[1].split("-");

                for(int i = 0; i < resMax; i++) {              	

                	
                	if(i<resMax-1) lag2= inord[i+1].split("-");
                	

                    if((i==resMax-1)||(i<resMax-1 && !lag[0].equals(lag2[0]))) {

                        for(int j = 0; j < persons.size(); j++) {


                            if(lag[0].equals(persons.get(j).getData()[5]) && !lag[1].equals(persons.get(j).getData()[0])) {

                                System.out.println("Name: "+persons.get(j).getData()[1]);
                                System.out.println("Surname: "+persons.get(j).getData()[2]);
                                System.out.println("Birthplace: "+persons.get(j).getData()[5]);
                                System.out.println("Studiedat: "+persons.get(j).getData()[7]); 
                            }

                        }


                    }                    
                    
                	lag[0] = lag2[0];
                	lag[1] = lag2[1];
                	
                }

                break;
            case 8:
                System.out.println("People in the network split to profiles based on their movies:");
                profiles.entrySet().stream().forEach(e->{
                    System.out.format("Favourite movies: %s, People : %s \n", e.getKey().toString(), e.getValue().toString());
                });
                break;
            case 9:
                System.out.println("Give an id of a person: ");
                String p1 = esk.nextLine();
                System.out.println("Give an id of the second person: ");
                String p2 = esk.nextLine();
                if(findPerson(p1)!=-1 && findPerson(p2)!=-1){

                    System.out.println(shortestChain(persons.get(findPerson(p1)),persons.get(findPerson(p2))));
                    break;
                } else {
                    System.out.println("Invalid input");
                    break;
                }

            case 10:

                Set<Set<Person>> cliques = findClicks(persons);
                for (Set<Person> clique : cliques) {

                    System.out.println(clique);

                }
                break;




            case 0:
                System.out.println("Byebye, come back soon.");
                break;

            default:
                System.out.println("Invalid input, please try again");

        }
    }



    /**
     *findPerson is a helper function that receives an ID and
     *returns the position of the object in the list persons
     *@param ID is an String to identificate a person.
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
    /**
     *textLinesNum is a function that receives a String text and then returns an int value for the amount of
     *lines
     *@param text
     *@return the
     */
 @SuppressWarnings("resource")
	public static int textLinesNum(String text)  {

    	try {
			BufferedReader br = new BufferedReader(new FileReader(text+".txt"));
			
			int contador = 0;
            String linea;
            
            
				try {
					while ( br.readLine() != null) {
					    contador++;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("error textlinesnum 1");
				}
				 return contador;
				
			
            
           
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("error textLinesNum 2");
		}
    	return 0;
    }

    /**
     *getPeopleBySurname is a function intended to create a list with the
     *people who shares surnames with the one introduced in the parameter
     *@param surname is the String we want to compare
     *@return the list of people with the same surname as the parameter
     */
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

    /**
     *bornBetween is a function intended to make a list with all the people who was born between
     *the first value given and the second
     *@return ret is the list with all the people described before
     */
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

    public static BST_class BST_load(String name) {

        FileInputStream in;
        try {

            BST_class bst = new BST_class();

            in = new FileInputStream(name+".txt");
            Scanner eska = new Scanner(in);
            String s;

            while(eska.hasNext()) {

                s = eska.next();
                bst.insert(persons.get(findPerson(s)).getData()[6]+"-"+s);

            }

            return bst;

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("error");
        }
        return null;

    }


    public static String shortestChain(Person arg1, Person arg2) {
        if (arg1.equals(arg2)) {
            return arg1.toString();
        }

        String ret = "";
        Queue<Person> que = new LinkedList<>();
        HashMap<Person, Person> parens = new HashMap<>();
        que.add(arg1);

        HashMap<Person, Boolean> visited = new HashMap<>();
        while (!que.isEmpty()) {
            Person temp = que.remove();

            for (Person a : temp.getPersonList()) {
                if (visited.get(a) == null) {
                    parens.put(a, temp);
                    visited.put(a, true);
                    que.add(a);


                    if (a.equals(arg2)) {

                        ret = a.toString();
                        while (!parens.get(a).equals(arg1) ) {

                            a = parens.get(a);
                            ret = a.toString() + "-" + ret;
                        }
                        return arg1.toString()+"-"+ret;
                    }
                }
            }
        }
        return null;
    }


    //Method returns a set of sets = a set of the cliques larger than 4 in size. Uses a worker method findCliquesWorker, due to the recursive nature of traversal.
    // Returning a set of sets to avoid duplicates that would be returned if using lists instead.
    //Method stillClique is used to check if the clique will still be a clique after adding a new member in findCliquesWorker


    public static Set<Set<Person>> findClicks(List<Person> g) {
        Set<Set<Person>> ret = new HashSet<>();
        Set<Person> temp = new HashSet<>();

        for (Person person : g) {
            temp.add(person);
            findCliquesWorker(g, person, temp, ret);
            temp.remove(person);
        }

        return ret;
    }

    public static void findCliquesWorker(List<Person> g, Person currentPerson, Set<Person> temp, Set<Set<Person>> ret) {
        if (temp.size() >= 5) {
            ret.add(new HashSet<>(temp));
        }

        for (Person a : currentPerson.getPersonList()) {
            if (!temp.contains(a) && stillClique(temp, a)) {
                temp.add(a);
                findCliquesWorker(g, a, temp, ret);
                temp.remove(a);
            }
        }
    }

    public static boolean stillClique(Set<Person> temp, Person a) {
        for (Person person : temp) {
            if (!person.getPersonList().contains(a)) {
                return false;
            }
        }
        return true;
    }

}
