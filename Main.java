import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public ArrayList<Person> persons = new ArrayList<Person>();
	
	public void main(String[] args) {


		int choize;
		
		do {
			
			choize = readTheMenuAndWrite();
			executeTheJob(choize);
			
			System.out.println(persons.get(0));
		}while(choize != 0);
		

	}
	
	public static int readTheMenuAndWrite() {
		
		int choize;
		
		Scanner esk = new Scanner(System.in);
		
		System.out.println("1-load into");
		System.out.println("2-load a relationship with someone");
		System.out.println("3-print out someone");
		//...
		System.out.println("0-log out");
		
		choize = esk.nextInt();
		
		return choize;

		
	}
	
	public void executeTheJob(int choize) {
		
		switch(choize) {
		case 1:







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





		
		break;
		case 2:
			
		break;
		case 3:
			
		break;
		case 0:
			System.out.println("Byebye, come back soon.");
		break;
		}
		
	}

}
