import acm.util.RandomGenerator;

public abstract class Organism
{
	protected World world;
	protected int x;
	protected int y;
	protected boolean simulated;//move or breed

	protected int timeStep = 1;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	public static final int CHECK_LEFT = 0;
	public static final int CHECK_RIGHT = 1;
	public static final int CHECK_UP = 2;
	public static final int CHECK_DOWN = 3;
	private int myDirection = -1;
	
	public Organism(World world, int x, int y)
	{
		this.world = world;
		this.x = x;
		this.y = y;
		simulated = true;
	}
	
	//returns the string representation of the organism
	public abstract String toString();
	
	public void simulate()
	{
		//don't simulate twice in a round
		if(simulated) return;
		simulated = true;
		
		//now move, breed, ....
		myDirection = rgen.nextInt(0,3);
		
		int fX = x, fY = y;
		
		switch(myDirection)
		{
		case CHECK_LEFT: fX--;
		break;
		case CHECK_RIGHT: fX++;
		break;
		case CHECK_UP: fY--;
		break;
		case CHECK_DOWN: fY++;
		break;
		}
		//make sure the point to the right is in the grid
		// this needs to be update to a random direction
		if(world.pointInGrid(fX, fY))
		{
			move(fX,fY);
		}
		timeStep++;
	}
	
	//indicate that the organism hasn't simulated this round
	public void resetSimulation()
	{
		simulated = false;
	}
	
	protected abstract void move(int x, int y);
	

	protected Organism getOnSide(int side)
	{
		Organism org = null;
		switch(side)
		{
		case CHECK_LEFT: org = world.getAt(x-1, y);
		break;
		case CHECK_RIGHT: org = world.getAt(x+1, y);
		break;
		case CHECK_UP: org = world.getAt(x, y-1);
		break;
		case CHECK_DOWN: org = world.getAt(x, y+1);
		break;
		}
		return org;
	}

	protected boolean sideInPlace(int side)
	{
		boolean result = false;
		switch(side)
		{
		case CHECK_LEFT: result = world.pointInGrid(x-1, y);
		break;
		case CHECK_RIGHT: result = world.pointInGrid(x+1, y);
		break;
		case CHECK_UP: result = world.pointInGrid(x, y-1);
		break;
		case CHECK_DOWN: result = world.pointInGrid(x, y+1);
		break;
		}
		return result;
	}

	protected abstract void breedHere(int side);
	
	protected void maybeBreed()
	{
		if(getOnSide(CHECK_LEFT) == null && sideInPlace(CHECK_LEFT))
		{
			breedHere(CHECK_LEFT);
		}else if(getOnSide(CHECK_RIGHT) == null && sideInPlace(CHECK_RIGHT))
		{
			breedHere(CHECK_RIGHT);
		}else if(getOnSide(CHECK_UP) == null && sideInPlace(CHECK_UP))
		{
			breedHere(CHECK_UP);
		}else if(getOnSide(CHECK_DOWN) == null && sideInPlace(CHECK_DOWN))
		{
			breedHere(CHECK_DOWN);
		}else{
			System.out.println(this + ": I Can't breed.");
		}
	}
}