public class DoodleBug extends Organism
{
	private int deathClock = 0;
	
	public DoodleBug(World world, int x, int y)
	{
		super(world, x, y);
	}
	
	//string representation of doodlebug
	public String toString()
	{
		return "doodlebug";
	}

	
	@Override
	public void simulate()
	{
		super.simulate();
		if(timeStep == 8)
		{
			timeStep = 1;
			maybeBreed();
		}
		if(isStarve())
		{
			System.out.println(this + ": I died of hunger" + "countSteps = " + timeStep);
			this.resetSimulation();
			world.setAt(this.x, this.y, null);
		}
		deathClock++;
	}
	
	//@Override
	protected void move(int x, int y)
	{
		Organism org = world.getAt(x, y);
		if(org == null)
		{
			world.setAt(this.x, this.y, null);
			this.x = x;
			this.y = y;
			world.setAt(this.x, this.y, this);
		}else{
			if(org.toString() == "ant")
			{
				eat(org);
				world.setAt(this.x, this.y, null);
				this.x = x;
				this.y = y;
				world.setAt(this.x, this.y, this);
			}
		}
	}
	
	@Override
	protected void breedHere(int side)
	{
		switch(side)
		{
		case CHECK_LEFT: world.setAt(x-1, y, new DoodleBug(world, x-1, y));
		break;
		case CHECK_RIGHT: world.setAt(x+1, y, new DoodleBug(world, x+1, y));
		break;
		case CHECK_UP: world.setAt(x, y-1, new DoodleBug(world, x, y-1));
		break;
		case CHECK_DOWN: world.setAt(x, y+1, new DoodleBug(world, x, y+1));
		break;
		
		}
	}
	
	private boolean isStarve()
	{
		return deathClock >= 3;
	}
	
	private void eat(Organism org)
	{
		System.out.println(this + " eats " + org + "timeStep = " + timeStep);
		deathClock = 1;
		org.resetSimulation();
	}
}