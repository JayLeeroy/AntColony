package ColonAi.Ants;

import java.util.Random;

import ColonAi.WorldDimension.Vector2D;
import ColonAi.WorldObjects.WorldObjectAbstract;

public abstract class AntAbstract extends WorldObjectAbstract {
	protected Random rand = new Random();
	
	abstract public void doAsYouWishMyChild();
	
	protected Boolean isPossible(int x, int y)
	{
		
		if (x < 0 || x > this.actualDimension.getMatrix().length -1 )
		{
			return false;
		}
		if (y < 0 || y > this.actualDimension.getMatrix()[0].length -1 )
		{
			return false;
		}
		
		return true;
	}
}
