import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class MadLibs
{
	 //declare private static variables
	private static String userinput;
	//consoleReader
	//ltcarrot = "<"
	//rtcarrot = ">"
	//file
	//String line
	private static Scanner consoleReader;
	
    public static void main(String[] args)
    {
    	MadLibs ml = new MadLibs();
    	
        consoleReader = new Scanner(System.in);
        System.out.print("Welcome to the game of Mad Libs.\nYou will help create a story by providing words and phrases.");
        System.out.print("(C)reate, (V)iew, or (Q)iut: ");
        String choiceCVQ = consoleReader.nextLine().toLowerCase();
        
        
       
        if(choiceCVQ.equals("q"))
        {
            System.out.print("Goodbye :(");
            System.exit(0);
        }
       
        if(choiceCVQ.equals("v"))
        {
            System.out.print("Enter File Name: ");
            ml.viewFile();
        }
       
       
        if(choiceCVQ.equals("c"))
        {
            System.out.print("Enter File Name: ");
            ml.makeFile();
        }
    }
    
    public void makeFile()
    {
    	File file = new File("input/" + userInput + ".txt");
    	PrintWriter fileWriter = null;
        try
        {
            fileWriter = new PrintWriter(madLibs1a);
        }
       
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
       
        while(fileReader.hasNextLine())
        {
            String line = fileReader.nextLine();
            line = replace("noun", nNoun);
            line = replace("adjective", nAdective);
        }
    }
    
    public void viewFile()
    {
    	Scanner fileReader = null;
        try
        {
            fileReader = new Scanner(madLibs1a);
        }
       
        catch(FileNotFoundException e)
        {
            System.out.println("Not a valid file name. Please try again");
            System.out.print("Enter File Name: ");
        }
       
        while(fileReader.hasNextLine())
        {
            String line = fileReader.nextLine();
            System.out.println(line);
        }
    }
}

