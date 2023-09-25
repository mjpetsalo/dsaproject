import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	
	public static void main(String[] args) {
		
		int choize;
		
		do {
			
			choize = readTheMenuAndWrite();
			executeTheJob(choize);
			
			
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
	
	public static void executeTheJob(int choize) {
		
		switch(choize) {
		case 1:
		
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
