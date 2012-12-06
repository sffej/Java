import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GRect;

public class Card extends GCompound
{
	public int color, shape, shading, number;
    private int cardID;
    private String cardImage;
    private GRect cardLocation;
    private int cardS = 59;
    
	//Card
	public Card(int color, int shape, int shading, int number, int xLocation, int yLocation)
	{
		this.color = color;
		this.shape = shape;
		this.shading = shading;
		this.number = number;
		this.setLocation(xLocation, yLocation);
		cardLocation = new GRect(xLocation, yLocation, cardS, cardS);
		add(cardLocation);
		
		cardID =  (color * 3) + (shape * 9) + (shading * 27) + number - 39;
		cardImage = cardID + ".gif";
		GImage cImage = new GImage(cardImage, xLocation, yLocation);
		add(cImage);		
	}
	
    public int getCardColor() {
        return color;
    }

    public int getShape() {
        return shape;
    }

    public int getShading() {
        return shading;
    }

    public int getNumber() {
        return number;
    }
}

