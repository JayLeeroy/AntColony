package ColonAi.Ants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import ColonAi.PackageManager.Packages.ReliablePackage;
import ColonAi.WorldDimension.Vector2D;
import ColonAi.WorldObjects.Tunnel;
import ColonAi.WorldObjects.WorldObjectAbstract;

public class Ant extends AntAbstract{
	
	public AntTask task = AntTask.food();
	AntTask secondarytask; 
	public Tunnel freeWay;
	public int freeWayTunnelNumber;
	
	public int life = this.rand.nextInt(500)+ 500;
	
	@Override
	public void doAsYouWishMyChild() {
		HashMap<Vector2D, WorldObjectAbstract> surroundings = this.actualDimension.getSurrondings(this, 1);
		//System.out.println(this.hashCode() + " thinkin" );
		if (this.task.eq(AntTask.food()))
		{
			//System.out.println(this.hashCode() + " thinked " + AntTask.food);
			surroundings.remove(this.lastposition);
			this.doFoodBehaviour(surroundings);
		}
		
		this.life--;
		
	}
	
	protected void doFoodBehaviour(HashMap<Vector2D, WorldObjectAbstract> surroundings)
	{
		List<Vector2D> emptyFields = new ArrayList<Vector2D>();
		List<Tunnel> tunnels       = new ArrayList<Tunnel>();
		
		for(Entry<Vector2D, WorldObjectAbstract> entry : surroundings.entrySet()) {
			WorldObjectAbstract value = entry.getValue();
			//System.out.println(value);
			if (value == null)
			{
				//if (entry.getKey().y <= this.position.y)
				//{
					emptyFields.add(entry.getKey());	
					//System.out.println("empty found" + entry.getKey().x + " " +entry.getKey().y);
				//}
			}
			else if (Tunnel.class.isAssignableFrom(value.getClass()))
			{
				tunnels.add((Tunnel)value);
			}
		}
		
		if (this.hasEmptyTunnel(tunnels))
		{
			this.actualDimension.moveAnt(this, this.freeWay.position.x, this.freeWay.position.y, this.task);
		}
		else
		{
			emptyFields = this.correctEmptyFieldList(emptyFields);
			if(!emptyFields.isEmpty())
			{
				int index = this.rand.nextInt(emptyFields.size());
				this.actualDimension.createObject(new Tunnel(), emptyFields.get(index).x, emptyFields.get(index).y);
				this.actualDimension.moveAnt(this, emptyFields.get(index).x, emptyFields.get(index).y, null);
				//System.out.println(this.hashCode() + " created a tunnel at" + emptyFields.get(index).x + " " + emptyFields.get(index).y);
				
				//try {
					//Thread.sleep(1000);
				//} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
			//	}
			}
		}
		
	}
	
	protected Boolean hasEmptyTunnel(List<Tunnel> tunnels)
	{
		this.freeWay = null;
		
		tunnels.forEach((tunnel)->{
    		try {
				if (this.task.eq(AntTask.feed()) && tunnel.feedtunnel == null)
				{
					this.freeWay = tunnel;
				}
				
				if (this.task.eq(AntTask.food()) && tunnel.foodtunnel == null )
				{
					this.freeWay = tunnel;
				}
			

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    			
    	});
		
		if (this.freeWay != null)
		{
			//System.out.println(this.hashCode() + " found a route");
			
			return true;
		}
		//System.out.println(this.hashCode() + " not found a route");
		return false;
	}
	
	protected List<Vector2D> correctEmptyFieldList(List<Vector2D> emptyFields)
	{
		List<Vector2D> correctemptyFields = new ArrayList<Vector2D>();
		
		emptyFields.forEach((field)->{
			int deltaX = this.position.x - field.x;
			int deltaY = this.position.y - field.y;
			
			
			if(deltaX != 0)
			{
				try {
					
					if (
					(this.actualDimension.getObject(field.x, field.y +1) == null &&
					this.actualDimension.getObject(field.x, field.y -1) == null))
					//!Tunnel.class.isAssignableFrom(this.actualDimension.getObject(field.x, field.y +1).getClass()) &&
					//!Tunnel.class.isAssignableFrom(this.actualDimension.getObject(field.x, field.y -1).getClass()))
					{
						correctemptyFields.add(field);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
			
			if(deltaY != 0)
			{
				if((this.task.eq(AntTask.food()) && deltaY > 0) || (this.task.eq(AntTask.feed()) && deltaY < 0)){
				try {
					
					if (
						this.actualDimension.getObject(field.x+1, field.y) != null &&
						this.actualDimension.getObject(field.x-1, field.y) != null)		
						//!Tunnel.class.isAssignableFrom(this.actualDimension.getObject(field.x +1 , field.y).getClass()) &&
						//!Tunnel.class.isAssignableFrom(this.actualDimension.getObject(field.x -1, field.y).getClass()))
					{
						correctemptyFields.add(field);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}}
		});
		
		if (correctemptyFields.isEmpty())
		{
			//System.out.println("Wattafuuuu");System.out.println(emptyFields);
		}
		
		return correctemptyFields;
	}

}
