import java.awt.event.MouseEvent;
import acm.graphics.GObject;
import acm.io.IODialog;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class TheSetGame extends GraphicsProgram
{
	public static final int APP_WIDTH = 350;
    public static final int APP_HEIGHT = 300;
    public static final int RED = 1, PURPLE = 2, GREEN = 3; 
    public static final int SQUIGGLE = 1, DIAMOND = 2, OVAL = 3;
    public static final int SOLID = 1, STRIPED = 2, OPEN = 3;
	public static final int ROWS = 3;
    public static final int COLS = 3;
    public int xLocation, yLocation;
    public int color, shape, shading, number;
    public int cardColor, cardShape, cardShading, cardNumber;
    
    private RandomGenerator rgen = new RandomGenerator();
    IODialog dialog = new IODialog();
    Card[][] card = new Card[ROWS][COLS];
    String position;
    boolean set = false;
    int setCount = 0;
       
    public void run()
    {
    	setSize(APP_WIDTH, APP_HEIGHT);
    	addMouseListeners();
    	xLocation = 0;
    	yLocation = 0;
    	
   	
    	for(int row = 0; row < 3; row++)
    	{
    		for(int col = 0; col < 3; col++)
	    	{
    			color = genRandCardNumber();
    			shape = genRandCardNumber();
    			shading = genRandCardNumber();
    			number = genRandCardNumber();
	            
	        	card[row][col] = new Card(color, shape, shading, number, xLocation, yLocation);
	        	add(card[row][col]);    
	        	xLocation += 60;
	    	}
    		yLocation += 60;
    		xLocation = 0;
    	}
    }
    
    public int genRandCardNumber()
    {
    	number = rgen.nextInt(1, 3);
    	return number;
    }
    
    public void mouseClicked(MouseEvent e)
    {   	
    	GObject maybeCard = getElementAt(e.getX(), e.getY());
    	if (maybeCard == null) return;
    	Card card = (Card)maybeCard;
    	if(setCount < 3)
    	{
    		cardColor += card.getCardColor();
    		cardShape += card.getShape();
    		cardShading += card.getShading();
    		cardNumber += card.getNumber();
    		setCount++;
    	}
    	if(setCount == 3)
    	{
    		if((cardColor % 3 != 0) || (cardShape % 3 != 0) || (cardShading % 3 != 0) || (cardNumber % 3 != 0))
    		{
    			dialog.println("Try again");
    		} else {
    			dialog.println("Good job");
    		}
    		setCount = 0;
    		cardColor = 0;
    		cardShape = 0;
    		cardShading = 0;
    		cardNumber = 0;
    	}
    }
}































