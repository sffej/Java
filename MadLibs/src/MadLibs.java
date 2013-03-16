import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class MadLibs
{
	 //declare private static variables
	private String userInput;
	private boolean play = true;
	public PrintWriter fileWriter;
	private Scanner fileReader;
	private Scanner consoleReader;
	public String line;
	

    public static void main(String[] args)
    {
    	MadLibs ml = new MadLibs();
    	ml.play();
    }
    
	private void play() 
	{
        System.out.print("Welcome to the game of Mad Libs.\nYou will help create a story by providing words and phrases.");
        
        while (play == true)
        {
	        System.out.print("(C)reate, (V)iew, or (Q)uit: ");
	        userInput = consoleReader.nextLine();
	        
	        if(userInput.equalsIgnoreCase("q"))
	        {
	            System.out.print("Goodbye :(");
	            play = false;
	        }
	       
	        if(userInput.equalsIgnoreCase("v"))
	        {
	        	System.out.print("clothes, dance, simple, tarzan");
	        	System.out.print("Choose one of the files above: ");
	        	userInput  = consoleReader.nextLine().toLowerCase();
	            viewFile();
	        }
	       
	       
	        if(userInput.equalsIgnoreCase("c"))
	        {
	        	System.out.print("clothes, dance, simple, tarzan");
	            System.out.print("Choose one of the files above: ");
	            userInput  = consoleReader.nextLine().toLowerCase();
	            makeFile();
	        }
        }//end while loop
	}

	public MadLibs()
	{
		
		consoleReader = new Scanner(System.in);
	}
    
    public void makeFile()
    {
    	//read file
    	File file = new File("input/" + userInput + ".txt");
    	
        try
        {
        	fileReader = new Scanner(file);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Not a valid file name. Please try again");
            System.out.print("Enter File Name: ");
        }
     
       //write file
        File newFile = new File("output/" + userInput + ".txt");
        try
        {
        	//FileOutputStream outFile = new FileOutputStream(newFile);
        	fileWriter = new PrintWriter(newFile);
        }
        catch(Exception e)
        {
        	System.out.println("Not a valid file name. Please try again");
        	System.exit(0);
        }
        
        //fileWriter = null;
        int leftCarrot = 0;
        int rightCarrot = 0;
        while(fileReader.hasNextLine())
        {
        	line = fileReader.nextLine();
        	do
        	{
        		leftCarrot = -1;
	        	rightCarrot = -1;
	        	leftCarrot = line.indexOf("<");
	        	rightCarrot = line.indexOf(">");
	        	
	        	if(leftCarrot >= 0 && rightCarrot >= 0)
	        	{
	        		String wordToChange = line.substring(leftCarrot +1, rightCarrot);
	        		String newWord = getWord(wordToChange);
	        		line = line.replaceAll("<" + wordToChange + ">", newWord);
	        	}
        	}
        	while(leftCarrot != -1 && rightCarrot != -1);
        	System.out.println(line);
            fileWriter.println(line);
        }
        fileWriter.close();
        fileReader.close();
    }
    
    private String getWord(String wordToChange)
    {
		System.out.print("enter a " + wordToChange + ": ");
		String word = consoleReader.nextLine();
		return word;
	}

	public void viewFile()
    {
    	File file = new File("output/" + userInput + ".txt");
    	Scanner fileReader = null;
        try
        {
            fileReader = new Scanner(file);
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

