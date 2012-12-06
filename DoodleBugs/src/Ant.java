public class Ant extends Organism
{
	public Ant(World world, int x, int y)
	{
		super(world, x, y);
	}
	
	//returns a string representation of the ant
	public String toString()
	{
		return "ant";
	}
	
	@Override
	public void simulate()
	{
		super.simulate();
		if(timeStep == 3)
		{
			timeStep = 1;
			maybeBreed();
		}
	}
	
	@Override
	protected void move(int x, int y)
	{
		Organism org = world.getAt(x, y);
		if(org ==null)
		{
			world.setAt(this.x, this.y, null);
			this.x = x;
			this.y = y;
			world.setAt(this.x, this.y, this);
		}
	}
	

	@Override
	protected void breedHere(int side)
	{
		switch(side)
		{
		case CHECK_LEFT: world.setAt(x-1, y, new Ant(world, x-1, y));
		break;
		case CHECK_RIGHT: world.setAt(x+1, y, new Ant(world, x+1, y));
		break;
		case CHECK_UP: world.setAt(x, y-1, new Ant(world, x, y-1));
		break;
		case CHECK_DOWN: world.setAt(x, y+1, new Ant(world, x, y+1));
		break;
		}
	}
}