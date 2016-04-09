package ColonAi.Ants;

import ColonAi.WorldObjects.Tunnel;
import ColonAi.WorldObjects.WorldObjectAbstract;

public class AntQueen extends AntAbstract {

	private int energy = 100;
	
	@Override
	public void doAsYouWishMyChild() {
		if (this.energy > 60){
			this.move();
		}
		else
		{
			this.antFactory();
		}
		
	}
	
	
	protected void move()
	{
		int chance = this.rand.nextInt(100);
		
		int nextX = this.position.x;
		int nextY = this.position.y;
		
		if (chance < 10 && this.isPossible(nextX + 1, nextY))
		{
			nextX = this.position.x + 1 ;
		}
		else if (chance < 20 && this.isPossible(nextX - 1, nextY))
		{
			nextX = this.position.x - 1 ;
		}
		else if (this.isPossible(nextX, nextY + 1))
		{			
			nextY = this.position.y + 1 ;
		}
		else
		{
			return;
		}

	
		this.actualDimension.createObject(new Tunnel(), nextX, nextY);
		this.actualDimension.moveAnt(this, nextX, nextY, null);
		this.energy--;
	}
	
	protected void antFactory()
	{
		int chance = this.rand.nextInt(100);
		
		if (chance < 40){
			try {
				Tunnel lastPosObj;
				lastPosObj = (Tunnel)this.actualDimension.getObject(this.lastposition.x, this.lastposition.y);
				
				
				if (lastPosObj.foodtunnel == null)
				{
					Ant ant = new Ant();
					
					this.actualDimension.createObject(ant, lastPosObj.position.x, lastPosObj.position.y);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	protected Boolean isPossible(int x, int y)
	{
		if(super.isPossible(x, y))
		{
			WorldObjectAbstract objIn;
			try {
				objIn = this.actualDimension.getObject(x, y);
				
				return objIn ==null;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}
}
